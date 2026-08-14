package br.com.biblioteca.dao;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Operacoes de circulacao. Emprestar e devolver sao transacoes atomicas. */
public class EmprestimoDAO {

  public void registrar(Emprestimo emprestimo) throws SQLException {
    if (
      emprestimo
        .getDataPrevistaDevolucao()
        .before(emprestimo.getDataEmprestimo())
    ) throw new SQLException(
      "A previsao nao pode ser anterior a data do emprestimo."
    );

    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      c.setAutoCommit(false);

      p = c.prepareStatement("SELECT ativo FROM cliente WHERE id=? FOR UPDATE");
      p.setInt(1, emprestimo.getCliente().getId());
      r = p.executeQuery();
      if (!r.next() || !r.getBoolean("ativo")) throw new SQLException(
        "Cliente inexistente ou inativo."
      );
      Conexao.fechar(r);
      r = null;
      Conexao.fechar(p);
      p = null;

      p = c.prepareStatement(
        "SELECT status FROM exemplar WHERE id=? FOR UPDATE"
      );
      p.setInt(1, emprestimo.getExemplar().getId());
      r = p.executeQuery();
      if (!r.next()) throw new SQLException("Exemplar nao encontrado.");
      if (
        !Exemplar.DISPONIVEL.equals(r.getString("status"))
      ) throw new SQLException("Este exemplar nao esta disponivel.");
      Conexao.fechar(r);
      r = null;
      Conexao.fechar(p);
      p = null;

      p = c.prepareStatement(
        "SELECT COUNT(*) FROM emprestimo WHERE id_exemplar=? AND status IN ('ABERTO','ATRASADO')"
      );
      p.setInt(1, emprestimo.getExemplar().getId());
      r = p.executeQuery();
      r.next();
      if (r.getInt(1) > 0) throw new SQLException(
        "Ja existe um emprestimo aberto para este exemplar."
      );
      Conexao.fechar(r);
      r = null;
      Conexao.fechar(p);
      p = null;

      p = c.prepareStatement(
        "INSERT INTO emprestimo (id_cliente,id_exemplar,data_emprestimo,data_prevista_devolucao,status) VALUES (?,?,?,?, 'ABERTO')",
        Statement.RETURN_GENERATED_KEYS
      );
      p.setInt(1, emprestimo.getCliente().getId());
      p.setInt(2, emprestimo.getExemplar().getId());
      p.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
      p.setDate(
        4,
        new java.sql.Date(emprestimo.getDataPrevistaDevolucao().getTime())
      );
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (r.next()) emprestimo.setId(r.getInt(1));
      Conexao.fechar(r);
      r = null;
      Conexao.fechar(p);
      p = null;

      p = c.prepareStatement(
        "UPDATE exemplar SET status='EMPRESTADO' WHERE id=? AND status='DISPONIVEL'"
      );
      p.setInt(1, emprestimo.getExemplar().getId());
      if (p.executeUpdate() != 1) throw new SQLException(
        "Nao foi possivel reservar o exemplar."
      );
      c.commit();
    } catch (SQLException e) {
      if (c != null) try {
        c.rollback();
      } catch (SQLException re) {
        e.setNextException(re);
      }
      throw e;
    } finally {
      if (c != null) try {
        c.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      Conexao.fechar(c, p, r);
    }
  }

  public void devolver(int idEmprestimo, Date dataDevolucao)
    throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    int idExemplar;
    try {
      c = Conexao.abrir();
      c.setAutoCommit(false);
      p = c.prepareStatement(
        "SELECT id_exemplar,status FROM emprestimo WHERE id=? FOR UPDATE"
      );
      p.setInt(1, idEmprestimo);
      r = p.executeQuery();
      if (!r.next()) throw new SQLException("Emprestimo nao encontrado.");
      if (
        Emprestimo.DEVOLVIDO.equals(r.getString("status"))
      ) throw new SQLException("Este emprestimo ja foi devolvido.");
      idExemplar = r.getInt("id_exemplar");
      Conexao.fechar(r);
      r = null;
      Conexao.fechar(p);
      p = null;

      p = c.prepareStatement(
        "UPDATE emprestimo SET data_devolucao=?,status='DEVOLVIDO' WHERE id=? AND status IN ('ABERTO','ATRASADO')"
      );
      p.setDate(1, new java.sql.Date(dataDevolucao.getTime()));
      p.setInt(2, idEmprestimo);
      if (p.executeUpdate() != 1) throw new SQLException(
        "Nao foi possivel registrar a devolucao."
      );
      Conexao.fechar(p);
      p = null;
      p = c.prepareStatement(
        "UPDATE exemplar SET status='DISPONIVEL' WHERE id=? AND status='EMPRESTADO'"
      );
      p.setInt(1, idExemplar);
      if (p.executeUpdate() != 1) throw new SQLException(
        "O estado do exemplar e inconsistente."
      );
      c.commit();
    } catch (SQLException e) {
      if (c != null) try {
        c.rollback();
      } catch (SQLException re) {
        e.setNextException(re);
      }
      throw e;
    } finally {
      if (c != null) try {
        c.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      Conexao.fechar(c, p, r);
    }
  }

  public List<Emprestimo> listarAbertos() throws SQLException {
    atualizarAtrasados();
    return consultar(
      "WHERE em.status IN ('ABERTO','ATRASADO') ORDER BY em.data_prevista_devolucao",
      null
    );
  }

  public List<Emprestimo> listarTodos() throws SQLException {
    atualizarAtrasados();
    return consultar("ORDER BY em.id DESC", null);
  }

  public List<Emprestimo> buscarPorCliente(String nome) throws SQLException {
    atualizarAtrasados();
    return consultar(
      "WHERE TRIM(c.nome) LIKE ? ORDER BY em.id DESC",
      "%" + nome.trim() + "%"
    );
  }

  private void atualizarAtrasados() throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "UPDATE emprestimo SET status='ATRASADO' WHERE status='ABERTO' AND data_prevista_devolucao<CURDATE()"
      );
      p.executeUpdate();
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  private List<Emprestimo> consultar(String filtro, Object v)
    throws SQLException {
    String sql =
      "SELECT em.id,em.data_emprestimo,em.data_prevista_devolucao,em.data_devolucao,em.status,c.id id_cliente,c.nome cliente,c.cpf,c.ativo,e.id id_exemplar,e.codigo,e.status status_exemplar,l.id id_livro,l.titulo FROM emprestimo em JOIN cliente c ON c.id=em.id_cliente JOIN exemplar e ON e.id=em.id_exemplar JOIN livro l ON l.id=e.id_livro " +
      filtro;
    List<Emprestimo> lista = new ArrayList<Emprestimo>();
    Connection con = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      con = Conexao.abrir();
      p = con.prepareStatement(sql);
      if (v instanceof String) p.setString(1, (String) v);
      r = p.executeQuery();
      while (r.next()) {
        Cliente c = new Cliente();
        c.setId(r.getInt("id_cliente"));
        c.setNome(r.getString("cliente"));
        c.setCpf(r.getString("cpf"));
        c.setAtivo(r.getBoolean("ativo"));
        Livro l = new Livro();
        l.setId(r.getInt("id_livro"));
        l.setTitulo(r.getString("titulo"));
        Exemplar e = new Exemplar();
        e.setId(r.getInt("id_exemplar"));
        e.setCodigo(r.getString("codigo"));
        e.setStatus(r.getString("status_exemplar"));
        e.setLivro(l);
        Emprestimo em = new Emprestimo();
        em.setId(r.getInt("id"));
        em.setCliente(c);
        em.setExemplar(e);
        em.setDataEmprestimo(r.getDate("data_emprestimo"));
        em.setDataPrevistaDevolucao(r.getDate("data_prevista_devolucao"));
        em.setDataDevolucao(r.getDate("data_devolucao"));
        em.setStatus(r.getString("status"));
        lista.add(em);
      }
      return lista;
    } finally {
      Conexao.fechar(con, p, r);
    }
  }
}
