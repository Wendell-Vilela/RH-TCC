package br.com.biblioteca.view;

import br.com.biblioteca.controller.LivroController;
import br.com.biblioteca.model.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaLivros extends JPanel {

  private final JTextField id = new JTextField(7),
    titulo = new JTextField(32),
    isbn = new JTextField(18),
    ano = new JTextField(8),
    pesquisa = new JTextField(24);
  private final JComboBox editora = new JComboBox(),
    categoria = new JComboBox();
  private final DefaultListModel modeloAutores = new DefaultListModel();
  private final JList autores = new JList(modeloAutores);
  private final DefaultTableModel modelo = new DefaultTableModel(
    new Object[] {
      "ID",
      "Titulo",
      "ISBN",
      "Ano",
      "Editora",
      "Categoria",
      "Autores",
    },
    0
  ) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final LivroController controller;

  public TelaLivros() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new LivroController(this);
    montar();
    controller.inicializar();
  }

  private void montar() {
    JPanel f = new JPanel(new GridBagLayout());
    f.setBorder(
      BorderFactory.createTitledBorder(
        "Cadastro de livros - selecione um ou mais autores"
      )
    );
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(3, 4, 3, 4);
    g.anchor = GridBagConstraints.WEST;
    campo(f, g, 0, "Codigo:", id);
    campo(f, g, 1, "Titulo*:", titulo);
    campo(f, g, 2, "ISBN*:", isbn);
    campo(f, g, 3, "Ano*:", ano);
    componente(f, g, 4, "Editora*:", editora);
    componente(f, g, 5, "Categoria*:", categoria);
    autores.setVisibleRowCount(4);
    autores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    componente(f, g, 6, "Autores*:", new JScrollPane(autores));
    id.setEditable(false);
    JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton novo = new JButton("Novo"),
      salvar = new JButton("Salvar"),
      excluir = new JButton("Excluir"),
      limpar = new JButton("Limpar"),
      atualizar = new JButton("Atualizar listas");
    b.add(novo);
    b.add(salvar);
    b.add(excluir);
    b.add(limpar);
    b.add(atualizar);
    JPanel n = new JPanel(new BorderLayout());
    n.add(f);
    n.add(b, BorderLayout.SOUTH);
    add(n, BorderLayout.NORTH);
    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
    p.add(new JLabel("Pesquisar titulo:"));
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
    atualizar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.carregarRelacionamentos();
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
    componente(p, g, y, r, t);
  }

  private void componente(
    JPanel p,
    GridBagConstraints g,
    int y,
    String r,
    Component c
  ) {
    g.gridx = 0;
    g.gridy = y;
    g.weightx = 0;
    g.fill = GridBagConstraints.NONE;
    p.add(new JLabel(r), g);
    g.gridx = 1;
    g.weightx = 1;
    g.fill = GridBagConstraints.HORIZONTAL;
    p.add(c, g);
  }

  public void limpar() {
    id.setText("");
    titulo.setText("");
    isbn.setText("");
    ano.setText("");
    if (editora.getItemCount() > 0) editora.setSelectedIndex(0);
    if (categoria.getItemCount() > 0) categoria.setSelectedIndex(0);
    autores.clearSelection();
    tabela.clearSelection();
  }

  public void preencherEditoras(List<Editora> l) {
    editora.removeAllItems();
    int i;
    for (i = 0; i < l.size(); i++) editora.addItem(l.get(i));
  }

  public void preencherCategorias(List<Categoria> l) {
    categoria.removeAllItems();
    int i;
    for (i = 0; i < l.size(); i++) categoria.addItem(l.get(i));
  }

  public void preencherAutores(List<Autor> l) {
    modeloAutores.clear();
    int i;
    for (i = 0; i < l.size(); i++) modeloAutores.addElement(l.get(i));
  }

  public void mostrar(Livro l) {
    id.setText(String.valueOf(l.getId()));
    titulo.setText(l.getTitulo());
    isbn.setText(l.getIsbn());
    ano.setText(String.valueOf(l.getAnoPublicacao()));
    selecionarCombo(editora, l.getEditora().getId());
    selecionarCombo(categoria, l.getCategoria().getId());
    int[] indices = new int[l.getAutores().size()];
    int i, j;
    for (i = 0; i < l.getAutores().size(); i++) {
      indices[i] = -1;
      for (j = 0; j < modeloAutores.size(); j++) if (
        ((Autor) modeloAutores.get(j)).getId() == l.getAutores().get(i).getId()
      ) {
        indices[i] = j;
        break;
      }
    }
    autores.setSelectedIndices(indices);
  }

  private void selecionarCombo(JComboBox combo, int codigo) {
    int i;
    for (i = 0; i < combo.getItemCount(); i++) {
      Object o = combo.getItemAt(i);
      if (o instanceof Editora && ((Editora) o).getId() == codigo) {
        combo.setSelectedIndex(i);
        return;
      }
      if (o instanceof Categoria && ((Categoria) o).getId() == codigo) {
        combo.setSelectedIndex(i);
        return;
      }
    }
  }

  public void preencherTabela(List<Livro> l) {
    modelo.setRowCount(0);
    int i, j;
    for (i = 0; i < l.size(); i++) {
      Livro x = l.get(i);
      StringBuilder a = new StringBuilder();
      for (j = 0; j < x.getAutores().size(); j++) {
        if (j > 0) a.append(", ");
        a.append(x.getAutores().get(j).getNome());
      }
      modelo.addRow(new Object[] {
        Integer.valueOf(x.getId()),
        x.getTitulo(),
        x.getIsbn(),
        Integer.valueOf(x.getAnoPublicacao()),
        x.getEditora(),
        x.getCategoria(),
        a.toString(),
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

  public JTextField getTxtTitulo() {
    return titulo;
  }

  public JTextField getTxtIsbn() {
    return isbn;
  }

  public JTextField getTxtAno() {
    return ano;
  }

  public JTextField getTxtPesquisa() {
    return pesquisa;
  }

  public JComboBox getCmbEditora() {
    return editora;
  }

  public JComboBox getCmbCategoria() {
    return categoria;
  }

  public JList getListaAutores() {
    return autores;
  }

  public JTable getTabela() {
    return tabela;
  }
}
