package br.com.biblioteca.controller;

import br.com.biblioteca.dao.CategoriaDAO;
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaCategorias;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaController {

  private final TelaCategorias tela;
  private final CategoriaDAO dao = new CategoriaDAO();

  public CategoriaController(TelaCategorias t) {
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
      ) throw new IllegalArgumentException("Informe o nome da categoria.");
      Categoria c = new Categoria(
        tela.getTxtNome().getText().trim(),
        tela.getTxtDescricao().getText().trim()
      );
      c.setId(tela.getId());
      if (c.getId() == 0) dao.salvar(c);
      else dao.atualizar(c);
      msg("Categoria salva com sucesso.");
      novo();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getId();
    if (id == 0) {
      msg("Selecione uma categoria.");
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Excluir esta categoria?",
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
      List<Categoria> l = f
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
