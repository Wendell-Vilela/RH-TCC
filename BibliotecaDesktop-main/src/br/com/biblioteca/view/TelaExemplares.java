package br.com.biblioteca.view;

import br.com.biblioteca.controller.ExemplarController;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaExemplares extends JPanel {

  private final JTextField id = new JTextField(7),
    codigo = new JTextField(18),
    pesquisa = new JTextField(22);
  private final JComboBox livro = new JComboBox(),
    status = new JComboBox(new String[] {
      Exemplar.DISPONIVEL,
      Exemplar.EMPRESTADO,
      Exemplar.INATIVO,
    });
  private final DefaultTableModel modelo = new DefaultTableModel(
    new Object[] { "ID", "Codigo", "Livro", "Status" },
    0
  ) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final ExemplarController controller;

  public TelaExemplares() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new ExemplarController(this);
    montar();
    controller.inicializar();
  }

  private void montar() {
    JPanel f = new JPanel(new GridBagLayout());
    f.setBorder(
      BorderFactory.createTitledBorder(
        "Cadastro de exemplares (unidades fisicas)"
      )
    );
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(4, 4, 4, 4);
    componente(f, g, 0, "Codigo interno:", id);
    componente(f, g, 1, "Livro*:", livro);
    componente(f, g, 2, "Codigo do exemplar*:", codigo);
    componente(f, g, 3, "Status:", status);
    id.setEditable(false);
    JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton novo = new JButton("Novo"),
      salvar = new JButton("Salvar"),
      inativar = new JButton("Inativar"),
      limpar = new JButton("Limpar"),
      atualizar = new JButton("Atualizar livros");
    b.add(novo);
    b.add(salvar);
    b.add(inativar);
    b.add(limpar);
    b.add(atualizar);
    JPanel n = new JPanel(new BorderLayout());
    n.add(f);
    n.add(b, BorderLayout.SOUTH);
    add(n, BorderLayout.NORTH);
    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
    p.add(new JLabel("Pesquisar codigo:"));
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
    atualizar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.carregarLivros();
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
    codigo.setText("");
    if (livro.getItemCount() > 0) livro.setSelectedIndex(0);
    status.setSelectedItem(Exemplar.DISPONIVEL);
    tabela.clearSelection();
  }

  public void preencherLivros(List<Livro> l) {
    livro.removeAllItems();
    int i;
    for (i = 0; i < l.size(); i++) livro.addItem(l.get(i));
  }

  public void mostrar(Exemplar e) {
    id.setText(String.valueOf(e.getId()));
    codigo.setText(e.getCodigo());
    status.setSelectedItem(e.getStatus());
    int i;
    for (i = 0; i < livro.getItemCount(); i++) if (
      ((Livro) livro.getItemAt(i)).getId() == e.getLivro().getId()
    ) {
      livro.setSelectedIndex(i);
      break;
    }
  }

  public void preencherTabela(List<Exemplar> l) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < l.size(); i++) {
      Exemplar e = l.get(i);
      modelo.addRow(new Object[] {
        Integer.valueOf(e.getId()),
        e.getCodigo(),
        e.getLivro(),
        e.getStatus(),
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

  public JTextField getTxtCodigo() {
    return codigo;
  }

  public JTextField getTxtPesquisa() {
    return pesquisa;
  }

  public JComboBox getCmbLivro() {
    return livro;
  }

  public JComboBox getCmbStatus() {
    return status;
  }

  public JTable getTabela() {
    return tabela;
  }
}
