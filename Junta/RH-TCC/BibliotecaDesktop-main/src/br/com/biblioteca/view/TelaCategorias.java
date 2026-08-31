package br.com.biblioteca.view;

import br.com.biblioteca.controller.CategoriaController;
import br.com.biblioteca.model.Categoria;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaCategorias extends JPanel {

  private final JTextField id = new JTextField(7),
    nome = new JTextField(30),
    descricao = new JTextField(35),
    pesquisa = new JTextField(22);
  private final DefaultTableModel modelo = new DefaultTableModel(
    new Object[] { "ID", "Nome", "Descricao" },
    0
  ) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final CategoriaController controller;

  public TelaCategorias() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new CategoriaController(this);
    montar();
    controller.listar();
  }

  private void montar() {
    JPanel f = new JPanel(new GridBagLayout());
    f.setBorder(BorderFactory.createTitledBorder("Cadastro de categorias"));
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(4, 4, 4, 4);
    campo(f, g, 0, "Codigo:", id);
    campo(f, g, 1, "Nome*:", nome);
    campo(f, g, 2, "Descricao:", descricao);
    id.setEditable(false);
    JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton novo = new JButton("Novo"),
      salvar = new JButton("Salvar"),
      excluir = new JButton("Excluir"),
      limpar = new JButton("Limpar");
    b.add(novo);
    b.add(salvar);
    b.add(excluir);
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
    excluir.addActionListener(
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

  private void campo(
    JPanel p,
    GridBagConstraints g,
    int y,
    String r,
    JTextField t
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
    descricao.setText("");
    tabela.clearSelection();
  }

  public void mostrar(Categoria a) {
    id.setText(String.valueOf(a.getId()));
    nome.setText(a.getNome());
    descricao.setText(a.getDescricao());
  }

  public void preencher(List<Categoria> l) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < l.size(); i++) {
      Categoria a = l.get(i);
      modelo.addRow(new Object[] {
        Integer.valueOf(a.getId()),
        a.getNome(),
        a.getDescricao(),
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

  public JTextField getTxtDescricao() {
    return descricao;
  }

  public JTextField getTxtPesquisa() {
    return pesquisa;
  }

  public JTable getTabela() {
    return tabela;
  }
}
