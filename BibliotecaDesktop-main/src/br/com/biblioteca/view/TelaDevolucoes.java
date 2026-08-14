package br.com.biblioteca.view;

import br.com.biblioteca.controller.DevolucaoController;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.util.Validador;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaDevolucoes extends JPanel {

  private final JTextField data = new JTextField(12);
  private final DefaultTableModel modelo = new DefaultTableModel(
    new Object[] {
      "ID",
      "Cliente",
      "Exemplar",
      "Livro",
      "Emprestimo",
      "Previsao",
      "Status",
    },
    0
  ) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final DevolucaoController controller;

  public TelaDevolucoes() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new DevolucaoController(this);
    montar();
    controller.inicializar();
  }

  private void montar() {
    JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topo.setBorder(BorderFactory.createTitledBorder("Devolucao de exemplar"));
    topo.add(new JLabel("Data da devolucao (dd/mm/aaaa):"));
    topo.add(data);
    JButton devolver = new JButton("Registrar devolucao"),
      atualizar = new JButton("Atualizar lista");
    topo.add(devolver);
    topo.add(atualizar);
    add(topo, BorderLayout.NORTH);
    add(new JScrollPane(tabela));
    tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    devolver.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.devolver();
        }
      }
    );
    atualizar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.listar();
        }
      }
    );
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
        e.getStatus(),
      });
    }
  }

  public JTextField getTxtData() {
    return data;
  }

  public JTable getTabela() {
    return tabela;
  }
}
