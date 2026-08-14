package br.com.biblioteca.controller;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaDevolucoes;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class DevolucaoController {

  private final TelaDevolucoes tela;
  private final EmprestimoDAO dao = new EmprestimoDAO();

  public DevolucaoController(TelaDevolucoes t) {
    tela = t;
  }

  public void inicializar() {
    tela.getTxtData().setText(Validador.formatarData(new java.util.Date()));
    listar();
  }

  public void listar() {
    try {
      tela.preencherTabela(dao.listarAbertos());
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void devolver() {
    int linha = tela.getTabela().getSelectedRow();
    if (linha < 0) {
      JOptionPane.showMessageDialog(tela, "Selecione um emprestimo aberto.");
      return;
    }
    int id = ((Integer) tela.getTabela().getValueAt(linha, 0)).intValue();
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Confirmar a devolucao do emprestimo " + id + "?",
        "Devolucao",
        JOptionPane.YES_NO_OPTION
      ) != JOptionPane.YES_OPTION
    ) return;
    try {
      dao.devolver(id, Validador.converterData(tela.getTxtData().getText()));
      JOptionPane.showMessageDialog(
        tela,
        "Devolucao registrada e exemplar liberado."
      );
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  private void erro(Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(
      tela,
      "Erro: " + e.getMessage(),
      "Biblioteca",
      JOptionPane.ERROR_MESSAGE
    );
  }
}
