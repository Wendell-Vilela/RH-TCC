package br.com.biblioteca.controller;

import br.com.biblioteca.dao.ExemplarDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaExemplares;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ExemplarController {

  private final TelaExemplares tela;
  private final ExemplarDAO dao = new ExemplarDAO();
  private final LivroDAO livroDAO = new LivroDAO();

  public ExemplarController(TelaExemplares t) {
    tela = t;
  }

  public void inicializar() {
    carregarLivros();
    listar();
  }

  public void carregarLivros() {
    try {
      tela.preencherLivros(livroDAO.listarTodos());
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void novo() {
    tela.limpar();
    tela.getTxtCodigo().requestFocus();
  }

  public void salvar() {
    try {
      validar();
      Exemplar e = new Exemplar();
      e.setId(tela.getId());
      e.setLivro((Livro) tela.getCmbLivro().getSelectedItem());
      e.setCodigo(tela.getTxtCodigo().getText().trim());
      e.setStatus((String) tela.getCmbStatus().getSelectedItem());
      if (e.getId() == 0) dao.salvar(e);
      else {
        Exemplar atual = dao.buscarPorId(e.getId());
        if (atual == null) throw new SQLException("Exemplar nao encontrado.");
        if (
          Exemplar.EMPRESTADO.equals(atual.getStatus()) &&
          !Exemplar.EMPRESTADO.equals(e.getStatus())
        ) throw new IllegalArgumentException(
          "Devolva o exemplar pela tela de devolucoes."
        );
        if (
          !Exemplar.EMPRESTADO.equals(atual.getStatus()) &&
          Exemplar.EMPRESTADO.equals(e.getStatus())
        ) throw new IllegalArgumentException(
          "O status EMPRESTADO e definido somente ao registrar um emprestimo."
        );
        dao.atualizar(e);
      }
      msg("Exemplar salvo com sucesso.");
      novo();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getId();
    if (id == 0) {
      msg("Selecione um exemplar.");
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Inativar este exemplar?",
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
      List<Exemplar> l = f
        ? dao.buscarPorCodigo(tela.getTxtPesquisa().getText())
        : dao.listarTodos();
      tela.preencherTabela(l);
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

  private void validar() {
    if (
      Validador.vazio(tela.getTxtCodigo().getText())
    ) throw new IllegalArgumentException("Informe o codigo.");
    if (
      tela.getCmbLivro().getSelectedItem() == null
    ) throw new IllegalArgumentException("Cadastre e selecione um livro.");
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
