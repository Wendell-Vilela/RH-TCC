package br.com.biblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Centraliza a configuracao e o ciclo de vida da conexao JDBC. */
public final class Conexao {

  // Altere somente estas tres constantes para o seu ambiente MySQL.
  private static final String URL =
    "jdbc:mysql://localhost:3306/biblioteca?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
  private static final String USUARIO = "root";
  private static final String SENHA = "Biblioteca@2026";

  private Conexao() {}

  public static Connection abrir() throws SQLException {
    try {
      // Connector/J 5.1 usa este nome de driver, compativel com Java 6.
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new SQLException(
        "Driver MySQL nao encontrado. Adicione o JAR da pasta lib ao Build Path."
      );
    }
    return DriverManager.getConnection(URL, USUARIO, SENHA);
  }

  public static void fechar(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void fechar(Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void fechar(Connection conexao) {
    if (conexao != null) {
      try {
        conexao.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void fechar(Connection conexao, Statement stmt, ResultSet rs) {
    fechar(rs);
    fechar(stmt);
    fechar(conexao);
  }
}
