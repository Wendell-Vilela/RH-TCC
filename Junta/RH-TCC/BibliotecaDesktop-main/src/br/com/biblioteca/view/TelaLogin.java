package br.com.biblioteca.view;

import br.com.biblioteca.controller.LoginController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaLogin extends JFrame {

  private final JTextField login = new JTextField(20);
  private final JPasswordField senha = new JPasswordField(20);
  private final LoginController controller;

  public TelaLogin() {
    super("Acesso - Sistema de Biblioteca");
    controller = new LoginController(this);
    montar();
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
  }

  private void montar() {
    JPanel principal = new JPanel(new BorderLayout(10, 10));
    principal.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));
    JLabel titulo = new JLabel(
      "SISTEMA DE GESTAO DE BIBLIOTECA",
      SwingConstants.CENTER
    );
    titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
    principal.add(titulo, BorderLayout.NORTH);
    JPanel f = new JPanel(new GridBagLayout());
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(6, 5, 6, 5);
    g.anchor = GridBagConstraints.WEST;
    g.gridx = 0;
    g.gridy = 0;
    f.add(new JLabel("Usuario:"), g);
    g.gridx = 1;
    f.add(login, g);
    g.gridx = 0;
    g.gridy = 1;
    f.add(new JLabel("Senha:"), g);
    g.gridx = 1;
    f.add(senha, g);
    principal.add(f);
    JPanel b = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton entrar = new JButton("Entrar"),
      sair = new JButton("Sair");
    b.add(entrar);
    b.add(sair);
    principal.add(b, BorderLayout.SOUTH);
    setContentPane(principal);
    // Pressionar Enter executa o mesmo evento do botao Entrar.
    getRootPane().setDefaultButton(entrar);
    entrar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.entrar();
        }
      }
    );
    sair.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.sair();
        }
      }
    );
    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          controller.sair();
        }
      }
    );
  }

  public JTextField getTxtLogin() {
    return login;
  }

  public JPasswordField getTxtSenha() {
    return senha;
  }
}
