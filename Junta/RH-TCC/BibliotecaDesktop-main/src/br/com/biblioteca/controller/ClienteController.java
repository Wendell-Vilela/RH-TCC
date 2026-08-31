package br.com.biblioteca.controller;

import br.com.biblioteca.dao.ClienteDAO;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaClientes;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/** Faz a ponte entre os componentes da tela, o objeto Cliente e o DAO. */
public class ClienteController {

  private final TelaClientes tela;
  private final ClienteDAO dao;

  public ClienteController(TelaClientes tela) {
    this.tela = tela;
    this.dao = new ClienteDAO();
  }

  public void novo() {
    tela.limparFormulario();
    tela.definirEdicao(true);
    tela.getTxtNome().requestFocus();
  }

  public void limpar() {
    tela.limparFormulario();
    tela.definirEdicao(true);
  }

  public void carregarTabela() {
    consultar(false);
  }

  public void buscar() {
    consultar(true);
  }

  public void salvar() {
    try {
      validar();
      Cliente c = lerFormulario();
      if (c.getId() == 0) {
        dao.salvar(c);
        mensagem(
          "Cliente cadastrado com sucesso.",
          JOptionPane.INFORMATION_MESSAGE
        );
      } else {
        dao.atualizar(c);
        mensagem(
          "Cliente atualizado com sucesso.",
          JOptionPane.INFORMATION_MESSAGE
        );
      }
      limpar();
      carregarTabela();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getIdSelecionado();
    if (id == 0) {
      mensagem("Selecione um cliente.", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Deseja inativar este cliente?",
        "Confirmacao",
        JOptionPane.YES_NO_OPTION
      ) == JOptionPane.YES_OPTION
    ) {
      try {
        dao.excluir(id);
        mensagem("Cliente inativado.", JOptionPane.INFORMATION_MESSAGE);
        limpar();
        carregarTabela();
      } catch (SQLException e) {
        erro(e);
      }
    }
  }

  public void selecionarLinha() {
    int linha = tela.getTabela().getSelectedRow();
    if (linha < 0) return;
    int id = ((Integer) tela.getTabela().getValueAt(linha, 0)).intValue();
    try {
      Cliente c = dao.buscarPorId(id);
      if (c != null) tela.mostrarCliente(c);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private Cliente lerFormulario() {
    Cliente c = new Cliente();
    c.setId(tela.getIdSelecionado());
    c.setNome(tela.getTxtNome().getText().trim());
    c.setCpf(tela.getTxtCpf().getText().trim());
    c.setEmail(tela.getTxtEmail().getText().trim());
    c.setTelefone(tela.getTxtTelefone().getText().trim());
    c.setEndereco(tela.getTxtEndereco().getText().trim());
    c.setAtivo(tela.getChkAtivo().isSelected());
    return c;
  }

  private void validar() {
    if (
      Validador.vazio(tela.getTxtNome().getText())
    ) throw new IllegalArgumentException("Informe o nome.");
    if (
      Validador.vazio(tela.getTxtCpf().getText())
    ) throw new IllegalArgumentException("Informe o CPF.");
    if (
      !Validador.emailValido(tela.getTxtEmail().getText())
    ) throw new IllegalArgumentException("Informe um e-mail valido.");
  }

  private void consultar(boolean filtro) {
    try {
      List<Cliente> l = filtro
        ? dao.buscarPorNome(tela.getTxtPesquisa().getText())
        : dao.listarTodos();
      tela.preencherTabela(l);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private void mensagem(String m, int tipo) {
    JOptionPane.showMessageDialog(tela, m, "Biblioteca", tipo);
  }

  private void erro(Exception e) {
    e.printStackTrace();
    mensagem(
      "Nao foi possivel concluir a operacao.\n" + e.getMessage(),
      JOptionPane.ERROR_MESSAGE
    );
  }
}
