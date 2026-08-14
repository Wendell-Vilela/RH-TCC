package br.com.biblioteca.dao;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO (Data Access Object) concentra os comandos SQL de Cliente.
 * Connection representa a conexao, PreparedStatement envia SQL parametrizado
 * e ResultSet permite percorrer as linhas devolvidas por um SELECT.
 */
public class ClienteDAO {

  public void salvar(Cliente cliente) throws SQLException {
    String sql =
      "INSERT INTO cliente (nome, cpf, email, telefone, endereco, data_cadastro, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preencher(stmt, cliente, false);
      stmt.executeUpdate();
      ResultSet chaves = null;
      try {
        chaves = stmt.getGeneratedKeys();
        if (chaves.next()) cliente.setId(chaves.getInt(1));
      } finally {
        Conexao.fechar(chaves);
      }
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void atualizar(Cliente cliente) throws SQLException {
    String sql =
      "UPDATE cliente SET nome=?, cpf=?, email=?, telefone=?, endereco=?, ativo=? WHERE id=?";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      preencher(stmt, cliente, true);
      if (stmt.executeUpdate() == 0) throw new SQLException(
        "Cliente nao encontrado."
      );
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  /** Preserva o historico: excluir um cliente significa inativa-lo. */
  public void excluir(int id) throws SQLException {
    alterarAtivo(id, false);
  }

  public void alterarAtivo(int id, boolean ativo) throws SQLException {
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement("UPDATE cliente SET ativo=? WHERE id=?");
      stmt.setBoolean(1, ativo);
      stmt.setInt(2, id);
      if (stmt.executeUpdate() == 0) throw new SQLException(
        "Cliente nao encontrado."
      );
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public Cliente buscarPorId(int id) throws SQLException {
    List<Cliente> lista = consultar(
      "SELECT * FROM cliente WHERE id=?",
      Integer.valueOf(id)
    );
    return lista.isEmpty() ? null : lista.get(0);
  }

  public List<Cliente> buscarPorNome(String nome) throws SQLException {
    return consultar(
      "SELECT * FROM cliente WHERE TRIM(nome) LIKE ? ORDER BY nome",
      "%" + nome.trim() + "%"
    );
  }

  public List<Cliente> listarTodos() throws SQLException {
    return consultar("SELECT * FROM cliente ORDER BY nome", null);
  }

  public List<Cliente> listarAtivos() throws SQLException {
    return consultar("SELECT * FROM cliente WHERE ativo=1 ORDER BY nome", null);
  }

  private List<Cliente> consultar(String sql, Object parametro)
    throws SQLException {
    List<Cliente> lista = new ArrayList<Cliente>();
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      if (parametro instanceof Integer) stmt.setInt(
        1,
        ((Integer) parametro).intValue()
      );
      if (parametro instanceof String) stmt.setString(1, (String) parametro);
      rs = stmt.executeQuery();
      while (rs.next()) lista.add(mapear(rs));
      return lista;
    } finally {
      Conexao.fechar(conexao, stmt, rs);
    }
  }

  private Cliente mapear(ResultSet rs) throws SQLException {
    Cliente c = new Cliente();
    c.setId(rs.getInt("id"));
    c.setNome(rs.getString("nome"));
    c.setCpf(rs.getString("cpf"));
    c.setEmail(rs.getString("email"));
    c.setTelefone(rs.getString("telefone"));
    c.setEndereco(rs.getString("endereco"));
    c.setDataCadastro(rs.getDate("data_cadastro"));
    c.setAtivo(rs.getBoolean("ativo"));
    return c;
  }

  private void preencher(PreparedStatement stmt, Cliente c, boolean atualizacao)
    throws SQLException {
    stmt.setString(1, c.getNome().trim());
    stmt.setString(2, c.getCpf().trim());
    stmt.setString(3, c.getEmail());
    stmt.setString(4, c.getTelefone());
    stmt.setString(5, c.getEndereco());
    if (atualizacao) {
      stmt.setBoolean(6, c.isAtivo());
      stmt.setInt(7, c.getId());
    } else {
      Date data =
        c.getDataCadastro() == null ? new Date() : c.getDataCadastro();
      stmt.setDate(6, new java.sql.Date(data.getTime()));
      stmt.setBoolean(7, c.isAtivo());
    }
  }
}
