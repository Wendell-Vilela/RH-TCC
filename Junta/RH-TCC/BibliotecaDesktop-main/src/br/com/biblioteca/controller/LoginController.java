package br.com.biblioteca.controller;

import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaLogin;
import br.com.biblioteca.view.TelaPrincipal;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController {

  private final TelaLogin tela;
  private final UsuarioDAO dao = new UsuarioDAO();

  public LoginController(TelaLogin t) {
    tela = t;
  }

  public void entrar() {
    String login = tela.getTxtLogin().getText().trim();
    String senha = new String(tela.getTxtSenha().getPassword());
    if (Validador.vazio(login) || Validador.vazio(senha)) {
      JOptionPane.showMessageDialog(
        tela,
        "Informe usuario e senha.",
        "Login",
        JOptionPane.WARNING_MESSAGE
      );
      return;
    }
    try {
      Usuario u = dao.autenticar(login, senha);
      if (u == null) {
        JOptionPane.showMessageDialog(
          tela,
          "Usuario ou senha invalidos, ou usuario inativo.",
          "Login",
          JOptionPane.ERROR_MESSAGE
        );
        tela.getTxtSenha().setText("");
        tela.getTxtSenha().requestFocus();
        return;
      }
      tela.dispose();
      new TelaPrincipal(u).setVisible(true);
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
        tela,
        "Falha ao acessar o banco.\n" + e.getMessage(),
        "Login",
        JOptionPane.ERROR_MESSAGE
      );
    }
  }

  public void sair() {
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Deseja encerrar o sistema?",
        "Sair",
        JOptionPane.YES_NO_OPTION
      ) == JOptionPane.YES_OPTION
    ) System.exit(0);
  }
}
