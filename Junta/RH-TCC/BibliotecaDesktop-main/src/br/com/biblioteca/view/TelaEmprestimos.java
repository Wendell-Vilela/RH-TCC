package br.com.biblioteca.view;

import br.com.biblioteca.controller.EmprestimoController;
import br.com.biblioteca.model.*;
import br.com.biblioteca.util.Validador;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaEmprestimos extends JPanel {

  private final JComboBox cliente = new JComboBox(),
    livro = new JComboBox(),
    exemplar = new JComboBox();
  private final JTextField data = new JTextField(12),
    previsao = new JTextField(12);
  private final DefaultTableModel modelo = new DefaultTableModel(
    new Object[] {
      "ID",
      "Cliente",
      "Exemplar",
      "Livro",
      "Emprestimo",
      "Previsao",
      "Devolucao",
      "Status",
    },
    0
  ) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final EmprestimoController controller;

  public TelaEmprestimos() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new EmprestimoController(this);
    montar();
    controller.inicializar();
  }

  private void montar() {
    JPanel f = new JPanel(new GridBagLayout());
    f.setBorder(
      BorderFactory.createTitledBorder(
        "Novo emprestimo - cada registro corresponde a um exemplar"
      )
    );
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(4, 4, 4, 4);
    componente(f, g, 0, "Cliente*:", cliente);
    componente(f, g, 1, "Livro*:", livro);
    componente(f, g, 2, "Exemplar disponivel*:", exemplar);
    componente(f, g, 3, "Data (dd/mm/aaaa)*:", data);
    componente(f, g, 4, "Previsao (dd/mm/aaaa)*:", previsao);
    JPanel b = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton registrar = new JButton("Registrar emprestimo"),
      atualizar = new JButton("Atualizar cadastros"),
      recarregar = new JButton("Atualizar tabela");
    b.add(registrar);
    b.add(atualizar);
    b.add(recarregar);
    JPanel topo = new JPanel(new BorderLayout());
    topo.add(f);
    topo.add(b, BorderLayout.SOUTH);
    add(topo, BorderLayout.NORTH);
    add(new JScrollPane(tabela));
    livro.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.carregarExemplares();
        }
      }
    );
    registrar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.registrar();
        }
      }
    );
    atualizar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.atualizarCadastros();
        }
      }
    );
    recarregar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.listar();
        }
      }
    );
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

  public void preencherClientes(List<Cliente> l) {
    cliente.removeAllItems();
    int i;
    for (i = 0; i < l.size(); i++) cliente.addItem(l.get(i));
  }

  public void preencherLivros(List<Livro> l) {
    livro.removeAllItems();
    int i;
    for (i = 0; i < l.size(); i++) livro.addItem(l.get(i));
  }

  public void preencherExemplares(List<Exemplar> l) {
    exemplar.removeAllItems();
    int i;
    for (i = 0; i < l.size(); i++) exemplar.addItem(l.get(i));
  }

  public void preencherTabela(List<Emprestimo> l) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < l.size(); i++) {
      Emprestimo e = l.get(i);
      modelo.addRow(new Object[] {
        Integer.valueOf(e.getId()),
        e.getCliente(),
        e.getExemplar().getCodigo(),
        e.getExemplar().getLivro(),
        Validador.formatarData(e.getDataEmprestimo()),
        Validador.formatarData(e.getDataPrevistaDevolucao()),
        Validador.formatarData(e.getDataDevolucao()),
        e.getStatus(),
      });
    }
  }

  public JComboBox getCmbCliente() {
    return cliente;
  }

  public JComboBox getCmbLivro() {
    return livro;
  }

  public JComboBox getCmbExemplar() {
    return exemplar;
  }

  public JTextField getTxtData() {
    return data;
  }

  public JTextField getTxtPrevisao() {
    return previsao;
  }
}
