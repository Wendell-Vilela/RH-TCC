package br.com.biblioteca.controller;

import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaEditoras;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class EditoraController {

  private final TelaEditoras tela;
  private final EditoraDAO dao = new EditoraDAO();

  public EditoraController(TelaEditoras t) {
    tela = t;
  }

  public void novo() {
    tela.limpar();
    tela.getTxtNome().requestFocus();
  }

  public void salvar() {
    try {
      if (
        Validador.vazio(tela.getTxtNome().getText())
      ) throw new IllegalArgumentException("Informe o nome da editora.");
      Editora e = new Editora(
        tela.getTxtNome().getText().trim(),
        tela.getTxtCidade().getText().trim()
      );
      e.setId(tela.getId());
      if (e.getId() == 0) dao.salvar(e);
      else dao.atualizar(e);
      msg("Editora salva com sucesso.");
      novo();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getId();
    if (id == 0) {
      msg("Selecione uma editora.");
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Excluir esta editora?",
        "Confirmacao",
        JOptionPane.YES_NO_OPTION
      ) == JOptionPane.YES_OPTION
    ) try {
      dao.excluir(id);
      novo();
      listar();
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void listar() {
    consulta(false);
  }

  public void buscar() {
    consulta(true);
  }

  private void consulta(boolean f) {
    try {
      List<Editora> l = f
        ? dao.buscarPorNome(tela.getTxtPesquisa().getText())
        : dao.listarTodos();
      tela.preencher(l);
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void selecionar() {
    int l = tela.getTabela().getSelectedRow();
    if (l < 0) return;
    try {
      tela.mostrar(
        dao.buscarPorId(
          ((Integer) tela.getTabela().getValueAt(l, 0)).intValue()
        )
      );
    } catch (SQLException e) {
      erro(e);
    }
  }

  private void msg(String m) {
    JOptionPane.showMessageDialog(tela, m);
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
