package br.com.biblioteca.dao;

import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.util.Conexao;
import br.com.biblioteca.util.SenhaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

  public Usuario autenticar(String login, String senha) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement("SELECT * FROM usuario WHERE login=? AND ativo=1");
      p.setString(1, login.trim());
      r = p.executeQuery();
      if (r.next()) {
        Usuario u = mapear(r);
        if (
          SenhaUtil.conferir(senha, u.getSenhaSalt(), u.getSenhaHash())
        ) return u;
      }
      return null;
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  public void salvar(Usuario u) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(
        "INSERT INTO usuario (nome,login,senha_hash,senha_salt,perfil,ativo) VALUES (?,?,?,?,?,?)",
        Statement.RETURN_GENERATED_KEYS
      );
      preencher(p, u, false);
      p.executeUpdate();
      r = p.getGeneratedKeys();
      if (r.next()) u.setId(r.getInt(1));
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  public void atualizar(Usuario u) throws SQLException {
    Connection c = null;
    PreparedStatement p = null;
    String sqlComSenha =
      "UPDATE usuario SET nome=?,login=?,senha_hash=?,senha_salt=?,perfil=?,ativo=? WHERE id=?";
    String sqlSemSenha =
      "UPDATE usuario SET nome=?,login=?,perfil=?,ativo=? WHERE id=?";
    boolean mudarSenha =
      u.getSenhaHash() != null && u.getSenhaHash().length() > 0;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(mudarSenha ? sqlComSenha : sqlSemSenha);
      if (mudarSenha) preencher(p, u, true);
      else {
        p.setString(1, u.getNome().trim());
        p.setString(2, u.getLogin().trim());
        p.setString(3, u.getPerfil());
        p.setBoolean(4, u.isAtivo());
        p.setInt(5, u.getId());
      }
      if (p.executeUpdate() == 0) throw new SQLException(
        "Usuario nao encontrado."
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
      p = c.prepareStatement("UPDATE usuario SET ativo=0 WHERE id=?");
      p.setInt(1, id);
      if (p.executeUpdate() == 0) throw new SQLException(
        "Usuario nao encontrado."
      );
    } finally {
      Conexao.fechar(c, p, null);
    }
  }

  public Usuario buscarPorId(int id) throws SQLException {
    List<Usuario> l = consultar(
      "SELECT * FROM usuario WHERE id=?",
      Integer.valueOf(id)
    );
    return l.isEmpty() ? null : l.get(0);
  }

  public List<Usuario> buscarPorNome(String nome) throws SQLException {
    return consultar(
      "SELECT * FROM usuario WHERE TRIM(nome) LIKE ? ORDER BY nome",
      "%" + nome.trim() + "%"
    );
  }

  public List<Usuario> listarTodos() throws SQLException {
    return consultar("SELECT * FROM usuario ORDER BY nome", null);
  }

  private List<Usuario> consultar(String sql, Object v) throws SQLException {
    List<Usuario> l = new ArrayList<Usuario>();
    Connection c = null;
    PreparedStatement p = null;
    ResultSet r = null;
    try {
      c = Conexao.abrir();
      p = c.prepareStatement(sql);
      if (v instanceof Integer) p.setInt(1, ((Integer) v).intValue());
      if (v instanceof String) p.setString(1, (String) v);
      r = p.executeQuery();
      while (r.next()) l.add(mapear(r));
      return l;
    } finally {
      Conexao.fechar(c, p, r);
    }
  }

  private Usuario mapear(ResultSet r) throws SQLException {
    Usuario u = new Usuario();
    u.setId(r.getInt("id"));
    u.setNome(r.getString("nome"));
    u.setLogin(r.getString("login"));
    u.setSenhaHash(r.getString("senha_hash"));
    u.setSenhaSalt(r.getString("senha_salt"));
    u.setPerfil(r.getString("perfil"));
    u.setAtivo(r.getBoolean("ativo"));
    return u;
  }

  private void preencher(PreparedStatement p, Usuario u, boolean atualizar)
    throws SQLException {
    p.setString(1, u.getNome().trim());
    p.setString(2, u.getLogin().trim());
    p.setString(3, u.getSenhaHash());
    p.setString(4, u.getSenhaSalt());
    p.setString(5, u.getPerfil());
    p.setBoolean(6, u.isAtivo());
    if (atualizar) p.setInt(7, u.getId());
  }
}
