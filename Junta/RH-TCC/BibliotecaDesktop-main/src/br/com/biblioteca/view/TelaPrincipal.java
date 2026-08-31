package br.com.biblioteca.view;

import br.com.biblioteca.model.Usuario;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Janela principal. Todas as funcionalidades sao abertas no mesmo JTabbedPane. */
public class TelaPrincipal extends JFrame {

  private final JTabbedPane abas = new JTabbedPane();
  private final Usuario usuario;

  public TelaPrincipal(Usuario usuario) {
    super("Sistema de Gestao de Biblioteca");
    this.usuario = usuario;
    montar();
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setMinimumSize(new Dimension(920, 650));
    setSize(1100, 760);
    setLocationRelativeTo(null);
    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          sair();
        }
      }
    );
  }

  private void montar() {
    setJMenuBar(criarMenu());
    JPanel inicio = new JPanel(new GridBagLayout());
    JLabel texto = new JLabel("Selecione uma funcionalidade no menu acima.");
    texto.setFont(texto.getFont().deriveFont(Font.BOLD, 18f));
    inicio.add(texto);
    abas.addTab("Inicio", inicio);
    add(abas, BorderLayout.CENTER);
    JPanel rodape = new JPanel(new BorderLayout());
    rodape.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    rodape.add(
      new JLabel(
        "Usuario: " + usuario.getNome() + " (" + usuario.getPerfil() + ")"
      ),
      BorderLayout.WEST
    );
    rodape.add(
      new JLabel("Sistema Biblioteca - Java Desktop"),
      BorderLayout.EAST
    );
    add(rodape, BorderLayout.SOUTH);
  }

  private JMenuBar criarMenu() {
    JMenuBar barra = new JMenuBar();
    JMenu cadastros = new JMenu("Cadastros"),
      movimentacoes = new JMenu("Movimentacoes"),
      consultas = new JMenu("Consultas"),
      sistema = new JMenu("Sistema");
    JMenuItem usuarios = item("Usuarios", 1),
      clientes = item("Clientes", 2),
      livros = item("Livros", 3),
      autores = item("Autores", 4),
      editoras = item("Editoras", 5),
      categorias = item("Categorias", 6),
      exemplares = item("Exemplares", 7),
      emprestimos = item("Emprestimos", 8),
      devolucoes = item("Devolucoes", 9);
    usuarios.setEnabled("ADMIN".equals(usuario.getPerfil()));
    cadastros.add(usuarios);
    cadastros.addSeparator();
    cadastros.add(clientes);
    cadastros.add(livros);
    cadastros.add(autores);
    cadastros.add(editoras);
    cadastros.add(categorias);
    cadastros.add(exemplares);
    movimentacoes.add(emprestimos);
    movimentacoes.add(devolucoes);
    consultas.add(item("Clientes", 2));
    consultas.add(item("Livros", 3));
    consultas.add(item("Emprestimos", 8));
    JMenuItem sobre = new JMenuItem("Sobre"),
      sair = new JMenuItem("Sair");
    sobre.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(
            TelaPrincipal.this,
            "Sistema didatico de Gestao de Biblioteca\nJava SE 6 + Swing + JDBC + MySQL",
            "Sobre",
            JOptionPane.INFORMATION_MESSAGE
          );
        }
      }
    );
    sair.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          sair();
        }
      }
    );
    sistema.add(sobre);
    sistema.addSeparator();
    sistema.add(sair);
    barra.add(cadastros);
    barra.add(movimentacoes);
    barra.add(consultas);
    barra.add(sistema);
    return barra;
  }

  private JMenuItem item(String titulo, final int modulo) {
    JMenuItem item = new JMenuItem(titulo);
    item.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          abrirModulo(modulo);
        }
      }
    );
    return item;
  }

  private void abrirModulo(int modulo) {
    String titulo;
    JPanel painel;
    if (modulo == 1) {
      titulo = "Usuarios";
      if (selecionar(titulo)) return;
      painel = new TelaUsuarios();
    } else if (modulo == 2) {
      titulo = "Clientes";
      if (selecionar(titulo)) return;
      painel = new TelaClientes();
    } else if (modulo == 3) {
      titulo = "Livros";
      if (selecionar(titulo)) return;
      painel = new TelaLivros();
    } else if (modulo == 4) {
      titulo = "Autores";
      if (selecionar(titulo)) return;
      painel = new TelaAutores();
    } else if (modulo == 5) {
      titulo = "Editoras";
      if (selecionar(titulo)) return;
      painel = new TelaEditoras();
    } else if (modulo == 6) {
      titulo = "Categorias";
      if (selecionar(titulo)) return;
      painel = new TelaCategorias();
    } else if (modulo == 7) {
      titulo = "Exemplares";
      if (selecionar(titulo)) return;
      painel = new TelaExemplares();
    } else if (modulo == 8) {
      titulo = "Emprestimos";
      if (selecionar(titulo)) return;
      painel = new TelaEmprestimos();
    } else {
      titulo = "Devolucoes";
      if (selecionar(titulo)) return;
      painel = new TelaDevolucoes();
    }
    abrirAba(titulo, painel);
  }

  /** Reutiliza uma aba existente para impedir Clientes, Clientes, Clientes... */
  public void abrirAba(String titulo, JPanel painel) {
    int i = abas.indexOfTab(titulo);
    if (i >= 0) {
      abas.setSelectedIndex(i);
      return;
    }
    abas.addTab(titulo, painel);
    abas.setTabComponentAt(
      abas.indexOfComponent(painel),
      cabecalhoFechavel(titulo, painel)
    );
    abas.setSelectedComponent(painel);
  }

  private boolean selecionar(String titulo) {
    int i = abas.indexOfTab(titulo);
    if (i >= 0) {
      abas.setSelectedIndex(i);
      return true;
    }
    return false;
  }

  private JPanel cabecalhoFechavel(String titulo, final Component painel) {
    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
    p.setOpaque(false);
    p.add(new JLabel(titulo));
    JButton fechar = new JButton("x");
    fechar.setMargin(new Insets(0, 4, 0, 4));
    fechar.setToolTipText("Fechar aba");
    fechar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          abas.remove(painel);
        }
      }
    );
    p.add(fechar);
    return p;
  }

  private void sair() {
    if (
      JOptionPane.showConfirmDialog(
        this,
        "Deseja encerrar o sistema?",
        "Sair",
        JOptionPane.YES_NO_OPTION
      ) == JOptionPane.YES_OPTION
    ) {
      dispose();
      System.exit(0);
    }
  }
}
