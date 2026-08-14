package br.com.biblioteca.dao;

import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

  public void salvar(Categoria e) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "INSERT INTO categoria (nome,descricao) VALUES (?,?)",
        Statement.RETURN_GENERATED_KEYS
      );
      p.setString(1, e.getNome().trim());
      p.setString(2, e.getDescricao());
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (r.next()) e.setId(r.getInt(1));
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  public void atualizar(Categoria e) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "UPDATE categoria SET nome=?,descricao=? WHERE id=?"
      );
      p.setString(1, e.getNome().trim());
      p.setString(2, e.getDescricao());
      p.setInt(3, e.getId());
      if (p.executeUpdate() == 0) throw new SQLException(
        "Categoria nao encontrada."
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
      p = c.prepareStatement("DELETE FROM categoria WHERE id=?");
      p.setInt(1, id);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Categoria nao encontrada."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public Categoria buscarPorId(int id) throws SQLException {
    List<Categoria> l = consultar(
      "SELECT * FROM categoria WHERE id=?",
      Integer.valueOf(id)
    );
    return l.isEmpty() ? null : l.get(0);
  }

  public List<Categoria> buscarPorNome(String nome) throws SQLException {
    return consultar(
      "SELECT * FROM categoria WHERE TRIM(nome) LIKE ? ORDER BY nome",
      "%" + nome.trim() + "%"
    );
  }

  public List<Categoria> listarTodos() throws SQLException {
    return consultar("SELECT * FROM categoria ORDER BY nome", null);
  }

  private List<Categoria> consultar(String sql, Object v) throws SQLException {
    List<Categoria> l = new ArrayList<Categoria>();
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
        Categoria e = new Categoria();
        e.setId(r.getInt("id"));
        e.setNome(r.getString("nome"));
        e.setDescricao(r.getString("descricao"));
        l.add(e);
      }
      return l;
    } finally {
      Conexao.fechar(c, p, r);
    }
  }
}
