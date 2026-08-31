package br.com.biblioteca.view;

import br.com.biblioteca.controller.UsuarioController;
import br.com.biblioteca.model.Usuario;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaUsuarios extends JPanel {

  private final JTextField id = new JTextField(7),
    nome = new JTextField(28),
    login = new JTextField(20),
    pesquisa = new JTextField(22);
  private final JPasswordField senha = new JPasswordField(20);
  private final JComboBox perfil = new JComboBox(new String[] {
    "ADMIN",
    "ATENDENTE",
  });
  private final JCheckBox ativo = new JCheckBox("Usuario ativo", true);
  private final DefaultTableModel modelo = new DefaultTableModel(
    new Object[] { "ID", "Nome", "Login", "Perfil", "Ativo" },
    0
  ) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final UsuarioController controller;

  public TelaUsuarios() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new UsuarioController(this);
    montar();
    controller.listar();
  }

  private void montar() {
    JPanel f = new JPanel(new GridBagLayout());
    f.setBorder(BorderFactory.createTitledBorder("Usuarios do sistema"));
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(4, 4, 4, 4);
    componente(f, g, 0, "Codigo:", id);
    componente(f, g, 1, "Nome*:", nome);
    componente(f, g, 2, "Login*:", login);
    componente(f, g, 3, "Senha*:", senha);
    componente(f, g, 4, "Perfil:", perfil);
    g.gridx = 1;
    g.gridy = 5;
    f.add(ativo, g);
    id.setEditable(false);
    JLabel aviso = new JLabel("Ao editar, deixe a senha vazia para mante-la.");
    g.gridy = 6;
    f.add(aviso, g);
    JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton novo = new JButton("Novo"),
      salvar = new JButton("Salvar"),
      inativar = new JButton("Inativar"),
      limpar = new JButton("Limpar");
    b.add(novo);
    b.add(salvar);
    b.add(inativar);
    b.add(limpar);
    JPanel n = new JPanel(new BorderLayout());
    n.add(f);
    n.add(b, BorderLayout.SOUTH);
    add(n, BorderLayout.NORTH);
    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
    p.add(new JLabel("Pesquisar:"));
    p.add(pesquisa);
    JButton buscar = new JButton("Buscar"),
      todos = new JButton("Todos");
    p.add(buscar);
    p.add(todos);
    JPanel c = new JPanel(new BorderLayout());
    c.add(p, BorderLayout.NORTH);
    c.add(new JScrollPane(tabela));
    add(c);
    tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    novo.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.novo();
        }
      }
    );
    limpar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.novo();
        }
      }
    );
    salvar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.salvar();
        }
      }
    );
    inativar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.excluir();
        }
      }
    );
    buscar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.buscar();
        }
      }
    );
    todos.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          pesquisa.setText("");
          controller.listar();
        }
      }
    );
    tabela.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          controller.selecionar();
        }
      }
    );
  }

  private void componente(
    JPanel p,
    GridBagConstraints g,
    int y,
    String r,
    Component t
  ) {
    g.gridx = 0;
    g.gridy = y;
    g.weightx = 0;
    g.fill = GridBagConstraints.NONE;
    p.add(new JLabel(r), g);
    g.gridx = 1;
    g.weightx = 1;
    g.fill = GridBagConstraints.HORIZONTAL;
    p.add(t, g);
  }

  public void limpar() {
    id.setText("");
    nome.setText("");
    login.setText("");
    senha.setText("");
    perfil.setSelectedItem("ATENDENTE");
    ativo.setSelected(true);
    tabela.clearSelection();
  }

  public void mostrar(Usuario u) {
    id.setText(String.valueOf(u.getId()));
    nome.setText(u.getNome());
    login.setText(u.getLogin());
    senha.setText("");
    perfil.setSelectedItem(u.getPerfil());
    ativo.setSelected(u.isAtivo());
  }

  public void preencher(List<Usuario> l) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < l.size(); i++) {
      Usuario u = l.get(i);
      modelo.addRow(new Object[] {
        Integer.valueOf(u.getId()),
        u.getNome(),
        u.getLogin(),
        u.getPerfil(),
        u.isAtivo() ? "Sim" : "Nao",
      });
    }
  }

  public int getId() {
    try {
      return Integer.parseInt(id.getText());
    } catch (Exception e) {
      return 0;
    }
  }

  public JTextField getTxtNome() {
    return nome;
  }

  public JTextField getTxtLogin() {
    return login;
  }

  public JPasswordField getTxtSenha() {
    return senha;
  }

  public JTextField getTxtPesquisa() {
    return pesquisa;
  }

  public JComboBox getCmbPerfil() {
    return perfil;
  }

  public JCheckBox getChkAtivo() {
    return ativo;
  }

  public JTable getTabela() {
    return tabela;
  }
}
