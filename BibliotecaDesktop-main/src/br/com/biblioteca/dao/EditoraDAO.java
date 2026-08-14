package br.com.biblioteca.dao;

import br.com.biblioteca.model.Editora;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

  public void salvar(Editora e) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "INSERT INTO editora (nome,cidade) VALUES (?,?)",
        Statement.RETURN_GENERATED_KEYS
      );
      p.setString(1, e.getNome().trim());
      p.setString(2, e.getCidade());
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (r.next()) e.setId(r.getInt(1));
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  public void atualizar(Editora e) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement("UPDATE editora SET nome=?,cidade=? WHERE id=?");
      p.setString(1, e.getNome().trim());
      p.setString(2, e.getCidade());
      p.setInt(3, e.getId());
      if (p.executeUpdate() == 0) throw new SQLException(
        "Editora nao encontrada."
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
      p = c.prepareStatement("DELETE FROM editora WHERE id=?");
      p.setInt(1, id);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Editora nao encontrada."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public Editora buscarPorId(int id) throws SQLException {
    List<Editora> l = consultar(
      "SELECT * FROM editora WHERE id=?",
      Integer.valueOf(id)
    );
    return l.isEmpty() ? null : l.get(0);
  }

  public List<Editora> buscarPorNome(String nome) throws SQLException {
    return consultar(
      "SELECT * FROM editora WHERE TRIM(nome) LIKE ? ORDER BY nome",
      "%" + nome.trim() + "%"
    );
  }

  public List<Editora> listarTodos() throws SQLException {
    return consultar("SELECT * FROM editora ORDER BY nome", null);
  }

  private List<Editora> consultar(String sql, Object v) throws SQLException {
    List<Editora> l = new ArrayList<Editora>();
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
        Editora e = new Editora();
        e.setId(r.getInt("id"));
        e.setNome(r.getString("nome"));
        e.setCidade(r.getString("cidade"));
        l.add(e);
      }
      return l;
    } finally {
      Conexao.fechar(c, p, r);
    }
  }
}
