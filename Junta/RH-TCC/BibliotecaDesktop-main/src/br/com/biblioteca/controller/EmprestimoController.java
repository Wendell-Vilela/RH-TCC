package br.com.biblioteca.controller;

import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.dao.ExemplarDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaEmprestimos;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class EmprestimoController {

  private final TelaEmprestimos tela;
  private final ClienteDAO clienteDAO = new ClienteDAO();
  private final LivroDAO livroDAO = new LivroDAO();
  private final ExemplarDAO exemplarDAO = new ExemplarDAO();
  private final EmprestimoDAO dao = new EmprestimoDAO();

  public EmprestimoController(TelaEmprestimos t) {
    tela = t;
  }

  public void inicializar() {
    atualizarCadastros();
    definirDatas();
    listar();
  }

  public void atualizarCadastros() {
    try {
      tela.preencherClientes(clienteDAO.listarAtivos());
      tela.preencherLivros(livroDAO.listarTodos());
      carregarExemplares();
    } catch (SQLException e) {
      erro(e);
    }
  }

  public void carregarExemplares() {
    Livro l = (Livro) tela.getCmbLivro().getSelectedItem();
    try {
      tela.preencherExemplares(
        l == null
          ? new java.util.ArrayList<Exemplar>()
          : exemplarDAO.listarDisponiveisPorLivro(l.getId())
      );
    } catch (SQLException e) {
      erro(e);
    }
  }

  private void definirDatas() {
    Date hoje = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(hoje);
    c.add(Calendar.DAY_OF_MONTH, 7);
    tela.getTxtData().setText(Validador.formatarData(hoje));
    tela.getTxtPrevisao().setText(Validador.formatarData(c.getTime()));
  }

  public void registrar() {
    try {
      Cliente c = (Cliente) tela.getCmbCliente().getSelectedItem();
      Exemplar e = (Exemplar) tela.getCmbExemplar().getSelectedItem();
      if (c == null) throw new IllegalArgumentException(
        "Selecione um cliente ativo."
      );
      if (e == null) throw new IllegalArgumentException(
        "Selecione um exemplar disponivel."
      );
      Emprestimo em = new Emprestimo();
      em.setCliente(c);
      em.setExemplar(e);
      em.setDataEmprestimo(
        Validador.converterData(tela.getTxtData().getText())
      );
      em.setDataPrevistaDevolucao(
        Validador.converterData(tela.getTxtPrevisao().getText())
      );
      dao.registrar(em);
      JOptionPane.showMessageDialog(
        tela,
        "Emprestimo registrado com sucesso. Codigo: " + em.getId()
      );
      carregarExemplares();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void listar() {
    try {
      tela.preencherTabela(dao.listarTodos());
    } catch (SQLException e) {
      erro(e);
    }
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
