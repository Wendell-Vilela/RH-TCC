package br.com.biblioteca.dao;

import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExemplarDAO {

  public void salvar(Exemplar e) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "INSERT INTO exemplar (id_livro,codigo,status) VALUES (?,?,?)",
        Statement.RETURN_GENERATED_KEYS
      );
      preencher(p, e, false);
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (r.next()) e.setId(r.getInt(1));
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  public void atualizar(Exemplar e) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "UPDATE exemplar SET id_livro=?,codigo=?,status=? WHERE id=?"
      );
      preencher(p, e, true);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Exemplar nao encontrado."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public void excluir(int id) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "UPDATE exemplar SET status='INATIVO' WHERE id=? AND status<>'EMPRESTADO'"
      );
      p.setInt(1, id);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Exemplar nao encontrado ou esta emprestado."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public Exemplar buscarPorId(int id) throws SQLException {
    List<Exemplar> l = consultar("WHERE e.id=?", Integer.valueOf(id));
    return l.isEmpty() ? null : l.get(0);
  }

  public List<Exemplar> buscarPorCodigo(String codigo) throws SQLException {
    return consultar("WHERE TRIM(e.codigo) LIKE ?", "%" + codigo.trim() + "%");
  }

  public List<Exemplar> listarTodos() throws SQLException {
    return consultar("", null);
  }

  public List<Exemplar> listarDisponiveisPorLivro(int idLivro)
    throws SQLException {
    return consultar(
      "WHERE e.status='DISPONIVEL' AND e.id_livro=?",
      Integer.valueOf(idLivro)
    );
  }

  private List<Exemplar> consultar(String filtro, Object v)
    throws SQLException {
    String sql =
      "SELECT e.id,e.codigo,e.status,l.id id_livro,l.titulo,l.isbn,l.ano_publicacao FROM exemplar e JOIN livro l ON l.id=e.id_livro " +
      filtro +
      " ORDER BY l.titulo,e.codigo";
    List<Exemplar> lista = new ArrayList<Exemplar>();
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(sql);
      if (v instanceof Integer) p.setInt(1, ((Integer) v).intValue());
      if (v instanceof String) p.setString(1, (String) v);
      r = p.executeQuery();
      while (r.next()) {
        Exemplar e = new Exemplar();
        e.setId(r.getInt("id"));
        e.setCodigo(r.getString("codigo"));
        e.setStatus(r.getString("status"));
        Livro l = new Livro();
        l.setId(r.getInt("id_livro"));
        l.setTitulo(r.getString("titulo"));
        l.setIsbn(r.getString("isbn"));
        l.setAnoPublicacao(r.getInt("ano_publicacao"));
        e.setLivro(l);
        lista.add(e);
      }
      return lista;
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  private void preencher(PreparedStatement p, Exemplar e, boolean atualizar)
    throws SQLException {
    p.setInt(1, e.getLivro().getId());
    p.setString(2, e.getCodigo().trim());
    p.setString(3, e.getStatus());
    if (atualizar) p.setInt(4, e.getId());
  }
}
