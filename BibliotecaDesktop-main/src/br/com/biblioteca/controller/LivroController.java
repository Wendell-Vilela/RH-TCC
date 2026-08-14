package br.com.biblioteca.controller;

import br.com.biblioteca.dao.AutorDAO;
import br.com.biblioteca.dao.CategoriaDAO;
import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Autor;
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaLivros;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LivroController {

  private final TelaLivros tela;
  private final LivroDAO dao = new LivroDAO();
  private final AutorDAO autorDAO = new AutorDAO();
  private final EditoraDAO editoraDAO = new EditoraDAO();
  private final CategoriaDAO categoriaDAO = new CategoriaDAO();

  public LivroController(TelaLivros tela) {
    this.tela = tela;
  }

  public void inicializar() {
    carregarRelacionamentos();
    listar();
  }

  public void novo() {
    tela.limpar();
    tela.getTxtTitulo().requestFocus();
  }

  public void carregarRelacionamentos() {
    try {
      tela.preencherEditoras(editoraDAO.listarTodos());
      tela.preencherCategorias(categoriaDAO.listarTodos());
      tela.preencherAutores(autorDAO.listarTodos());
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void salvar() {
    try {
      validar();
      Livro l = ler();
      if (l.getId() == 0) dao.salvar(l);
      else dao.atualizar(l);
      msg("Livro salvo com sucesso.");
      novo();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getId();
    if (id == 0) {
      msg("Selecione um livro.");
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Excluir este livro? A operacao sera impedida se houver exemplares.",
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

  private void consulta(boolean filtro) {
    try {
      List<Livro> l = filtro
        ? dao.buscarPorTitulo(tela.getTxtPesquisa().getText())
        : dao.listarTodos();
      tela.preencherTabela(l);
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void selecionar() {
    int linha = tela.getTabela().getSelectedRow();
    if (linha < 0) return;
    try {
      Livro l = dao.buscarPorId(
        ((Integer) tela.getTabela().getValueAt(linha, 0)).intValue()
      );
      if (l != null) tela.mostrar(l);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private Livro ler() {
    Livro l = new Livro();
    l.setId(tela.getId());
    l.setTitulo(tela.getTxtTitulo().getText().trim());
    l.setIsbn(tela.getTxtIsbn().getText().trim());
    l.setAnoPublicacao(Integer.parseInt(tela.getTxtAno().getText().trim()));
    l.setEditora((Editora) tela.getCmbEditora().getSelectedItem());
    l.setCategoria((Categoria) tela.getCmbCategoria().getSelectedItem());
    Object[] selecionados = tela.getListaAutores().getSelectedValues();
    List<Autor> autores = new ArrayList<Autor>();
    int i;
    for (i = 0; i < selecionados.length; i++) autores.add(
      (Autor) selecionados[i]
    );
    l.setAutores(autores);
    return l;
  }

  private void validar() {
    if (
      Validador.vazio(tela.getTxtTitulo().getText())
    ) throw new IllegalArgumentException("Informe o titulo.");
    if (
      Validador.vazio(tela.getTxtIsbn().getText())
    ) throw new IllegalArgumentException("Informe o ISBN.");
    try {
      int ano = Integer.parseInt(tela.getTxtAno().getText().trim());
      if (ano < 0 || ano > 9999) throw new Exception();
    } catch (Exception e) {
      throw new IllegalArgumentException("Informe um ano valido.");
    }
    if (
      tela.getCmbEditora().getSelectedItem() == null
    ) throw new IllegalArgumentException("Cadastre e selecione uma editora.");
    if (
      tela.getCmbCategoria().getSelectedItem() == null
    ) throw new IllegalArgumentException("Cadastre e selecione uma categoria.");
    if (
      tela.getListaAutores().getSelectedIndices().length == 0
    ) throw new IllegalArgumentException("Selecione ao menos um autor.");
  }

  private void msg(String m) {
    JOptionPane.showMessageDialog(tela, m);
  }

  private void erro(Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(
      tela,
      "Nao foi possivel concluir.\n" + e.getMessage(),
      "Biblioteca",
      JOptionPane.ERROR_MESSAGE
    );
  }
}
