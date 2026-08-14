package br.com.biblioteca.controller;

import br.com.biblioteca.dao.AutorDAO;
import br.com.biblioteca.model.Autor;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaAutores;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class AutorController {

  private final TelaAutores tela;
  private final AutorDAO dao = new AutorDAO();

  public AutorController(TelaAutores tela) {
    this.tela = tela;
  }

  public void novo() {
    tela.limpar();
    tela.getTxtNome().requestFocus();
  }

  public void salvar() {
    try {
      if (
        Validador.vazio(tela.getTxtNome().getText())
      ) throw new IllegalArgumentException("Informe o nome do autor.");
      Autor a = new Autor(
        tela.getTxtNome().getText().trim(),
        tela.getTxtNacionalidade().getText().trim()
      );
      a.setId(tela.getId());
      if (a.getId() == 0) dao.salvar(a);
      else dao.atualizar(a);
      mensagem("Autor salvo com sucesso.");
      novo();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getId();
    if (id == 0) {
      mensagem("Selecione um autor.");
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Excluir este autor?",
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
    consultar(false);
  }

  public void buscar() {
    consultar(true);
  }

  private void consultar(boolean f) {
    try {
      List<Autor> l = f
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
      Autor a = dao.buscarPorId(
        ((Integer) tela.getTabela().getValueAt(l, 0)).intValue()
      );
      tela.mostrar(a);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private void mensagem(String m) {
    JOptionPane.showMessageDialog(
      tela,
      m,
      "Biblioteca",
      JOptionPane.INFORMATION_MESSAGE
    );
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
