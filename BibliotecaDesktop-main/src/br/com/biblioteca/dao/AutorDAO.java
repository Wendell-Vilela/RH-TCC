package br.com.biblioteca.dao;

import br.com.biblioteca.model.Autor;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

  public void salvar(Autor autor) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "INSERT INTO autor (nome, nacionalidade) VALUES (?, ?)",
        Statement.RETURN_GENERATED_KEYS
      );
      p.setString(1, autor.getNome().trim());
      p.setString(2, autor.getNacionalidade());
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (r.next()) autor.setId(r.getInt(1));
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  public void atualizar(Autor autor) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "UPDATE autor SET nome=?, nacionalidade=? WHERE id=?"
      );
      p.setString(1, autor.getNome().trim());
      p.setString(2, autor.getNacionalidade());
      p.setInt(3, autor.getId());
      if (p.executeUpdate() == 0) throw new SQLException(
        "Autor nao encontrado."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public void excluir(int id) throws SQLException {
    executarExclusao("DELETE FROM autor WHERE id=?", id);
  }

  public Autor buscarPorId(int id) throws SQLException {
    List<Autor> l = consultar(
      "SELECT * FROM autor WHERE id=?",
      Integer.valueOf(id)
    );
    return l.isEmpty() ? null : l.get(0);
  }

  public List<Autor> buscarPorNome(String nome) throws SQLException {
    return consultar(
      "SELECT * FROM autor WHERE TRIM(nome) LIKE ? ORDER BY nome",
      "%" + nome.trim() + "%"
    );
  }

  public List<Autor> listarTodos() throws SQLException {
    return consultar("SELECT * FROM autor ORDER BY nome", null);
  }

  private List<Autor> consultar(String sql, Object valor) throws SQLException {
    List<Autor> lista = new ArrayList<Autor>();
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(sql);
      if (valor instanceof Integer) p.setInt(1, ((Integer) valor).intValue());
      if (valor instanceof String) p.setString(1, (String) valor);
      r = p.executeQuery();
      while (r.next()) {
        Autor a = new Autor();
        a.setId(r.getInt("id"));
        a.setNome(r.getString("nome"));
        a.setNacionalidade(r.getString("nacionalidade"));
        lista.add(a);
      }
      return lista;
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  private void executarExclusao(String sql, int id) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(sql);
      p.setInt(1, id);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Autor nao encontrado."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }
}
