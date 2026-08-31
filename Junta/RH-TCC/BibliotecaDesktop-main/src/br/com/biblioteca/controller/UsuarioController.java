package br.com.biblioteca.controller;

import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.util.SenhaUtil;
import br.com.biblioteca.util.Validador;
import br.com.biblioteca.view.TelaUsuarios;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioController {

  private final TelaUsuarios tela;
  private final UsuarioDAO dao = new UsuarioDAO();

  public UsuarioController(TelaUsuarios t) {
    tela = t;
  }

  public void novo() {
    tela.limpar();
    tela.getTxtNome().requestFocus();
  }

  public void salvar() {
    try {
      validar();
      Usuario u = new Usuario();
      u.setId(tela.getId());
      u.setNome(tela.getTxtNome().getText().trim());
      u.setLogin(tela.getTxtLogin().getText().trim());
      u.setPerfil((String) tela.getCmbPerfil().getSelectedItem());
      u.setAtivo(tela.getChkAtivo().isSelected());
      String senha = new String(tela.getTxtSenha().getPassword());
      if (senha.length() > 0) {
        String salt = SenhaUtil.gerarSalt();
        u.setSenhaSalt(salt);
        u.setSenhaHash(SenhaUtil.gerarHash(senha, salt));
      }
      if (u.getId() == 0) dao.salvar(u);
      else dao.atualizar(u);
      msg("Usuario salvo com sucesso.");
      novo();
      listar();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getId();
    if (id == 0) {
      msg("Selecione um usuario.");
      return;
    }
    if (
      JOptionPane.showConfirmDialog(
        tela,
        "Inativar este usuario?",
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
      List<Usuario> l = f
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

  private void validar() {
    if (
      Validador.vazio(tela.getTxtNome().getText())
    ) throw new IllegalArgumentException("Informe o nome.");
    if (
      Validador.vazio(tela.getTxtLogin().getText())
    ) throw new IllegalArgumentException("Informe o login.");
    if (
      tela.getId() == 0 && tela.getTxtSenha().getPassword().length == 0
    ) throw new IllegalArgumentException("Informe a senha do novo usuario.");
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
