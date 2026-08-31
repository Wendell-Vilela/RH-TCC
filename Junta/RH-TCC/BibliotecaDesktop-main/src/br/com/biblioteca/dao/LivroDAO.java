package br.com.biblioteca.dao;

import br.com.biblioteca.model.Autor;
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

  public void salvar(Livro livro) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      c.setAutoCommit(false);
      p = c.prepareStatement(
        "INSERT INTO livro (titulo,isbn,ano_publicacao,id_editora,id_categoria) VALUES (?,?,?,?,?)",
        Statement.RETURN_GENERATED_KEYS
      );
      preencher(p, livro, false);
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (!r.next()) throw new SQLException(
        "Nao foi possivel obter o codigo do livro."
      );
      livro.setId(r.getInt(1));
      Conexao.fechar(r);
      r = null;
      Conexao.fechar(p);
      p = null;
      salvarAutores(c, livro);
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

  public void atualizar(Livro livro) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      c.setAutoCommit(false);
      p = c.prepareStatement(
        "UPDATE livro SET titulo=?,isbn=?,ano_publicacao=?,id_editora=?,id_categoria=? WHERE id=?"
      );
      preencher(p, livro, true);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Livro nao encontrado."
      );
      Conexao.fechar(p);
      p = c.prepareStatement("DELETE FROM livro_autor WHERE id_livro=?");
      p.setInt(1, livro.getId());
      p.executeUpdate();
      Conexao.fechar(p);
      p = null;
      salvarAutores(c, livro);
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
      Conexao.fechar(c, p, null);
    }
  }

  public void excluir(int id) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement("DELETE FROM livro WHERE id=?");
      p.setInt(1, id);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Livro nao encontrado."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public Livro buscarPorId(int id) throws SQLException {
    List<Livro> l = consultar("WHERE l.id=?", Integer.valueOf(id));
    return l.isEmpty() ? null : l.get(0);
  }

  public List<Livro> buscarPorTitulo(String titulo) throws SQLException {
    return consultar("WHERE TRIM(l.titulo) LIKE ?", "%" + titulo.trim() + "%");
  }

  public List<Livro> listarTodos() throws SQLException {
    return consultar("", null);
  }

  private List<Livro> consultar(String filtro, Object valor)
    throws SQLException {
    String sql =
      "SELECT l.id,l.titulo,l.isbn,l.ano_publicacao,e.id id_editora,e.nome editora,e.cidade,c.id id_categoria,c.nome categoria,c.descricao FROM livro l JOIN editora e ON e.id=l.id_editora JOIN categoria c ON c.id=l.id_categoria " +
      filtro +
      " ORDER BY l.titulo";
    List<Livro> lista = new ArrayList<Livro>();
    Connection con = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      con = Conexao.abrir();
      p = con.prepareStatement(sql);
      if (valor instanceof Integer) p.setInt(1, ((Integer) valor).intValue());
      if (valor instanceof String) p.setString(1, (String) valor);
      r = p.executeQuery();
      while (r.next()) {
        Livro l = new Livro();
        l.setId(r.getInt("id"));
        l.setTitulo(r.getString("titulo"));
        l.setIsbn(r.getString("isbn"));
        l.setAnoPublicacao(r.getInt("ano_publicacao"));
        Editora e = new Editora();
        e.setId(r.getInt("id_editora"));
        e.setNome(r.getString("editora"));
        e.setCidade(r.getString("cidade"));
        l.setEditora(e);
        Categoria ca = new Categoria();
        ca.setId(r.getInt("id_categoria"));
        ca.setNome(r.getString("categoria"));
        ca.setDescricao(r.getString("descricao"));
        l.setCategoria(ca);
        l.setAutores(listarAutores(con, l.getId()));
        lista.add(l);
      }
      return lista;
    } finally {
      Conexao.fechar(con, p, r);
    }
  }

  private List<Autor> listarAutores(Connection c, int id) throws SQLException {
    List<Autor> l = new ArrayList<Autor>();
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      p = c.prepareStatement(
        "SELECT a.id,a.nome,a.nacionalidade FROM autor a JOIN livro_autor la ON la.id_autor=a.id WHERE la.id_livro=? ORDER BY a.nome"
      );
      p.setInt(1, id);
      r = p.executeQuery();
      while (r.next()) {
        Autor a = new Autor();
        a.setId(r.getInt("id"));
        a.setNome(r.getString("nome"));
        a.setNacionalidade(r.getString("nacionalidade"));
        l.add(a);
      }
      return l;
    } finally {
      Conexao.fechar(r);
      Conexao.fechar(p);
    }
  }

  private void salvarAutores(Connection c, Livro l) throws SQLException {
    PreparedStatement p = null;
    try {
      p = c.prepareStatement(
        "INSERT INTO livro_autor (id_livro,id_autor) VALUES (?,?)"
      );
      int i;
      for (i = 0; i < l.getAutores().size(); i++) {
        p.setInt(1, l.getId());
        p.setInt(2, l.getAutores().get(i).getId());
        p.addBatch();
      }
      p.executeBatch();
    } finally {
      Conexao.fechar(p);
    }
  }

  private void preencher(PreparedStatement p, Livro l, boolean atualizacao)
    throws SQLException {
    p.setString(1, l.getTitulo().trim());
    p.setString(2, l.getIsbn().trim());
    p.setInt(3, l.getAnoPublicacao());
    p.setInt(4, l.getEditora().getId());
    p.setInt(5, l.getCategoria().getId());
    if (atualizacao) p.setInt(6, l.getId());
  }
}
