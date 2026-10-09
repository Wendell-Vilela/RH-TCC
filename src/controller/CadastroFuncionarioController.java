
package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.CadastroFuncionario;
import model.DadosBancarios;
import model.DadosPessoais;
import model.Dependentes;
import model.Documentos;
import model.Historico;
import view.TelaCadastroFuncionario;

public class CadastroFuncionarioController {

    private final TelaCadastroFuncionario view;
    private final List<CadastroFuncionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    public CadastroFuncionarioController(TelaCadastroFuncionario view) {
        if (view == null) {
            throw new IllegalArgumentException("A tela não pode ser nula.");
        }

        this.view = view;
        configurarEventos();
    }

    private void configurarEventos() {
        view.getSalvar().addActionListener(e -> salvar());
        view.getExcluir().addActionListener(e -> excluir());
        view.getLimpar().addActionListener(e -> limpar());

        view.getAdicionarDependente().addActionListener(
            e -> executar(view::adicionarDependenteNaTabela)
        );

        view.getRemoverDependente().addActionListener(
            e -> view.removerDependenteSelecionado()
        );

        view.getAdicionarHistorico().addActionListener(
            e -> executar(view::adicionarHistoricoNaTabela)
        );

        view.getRemoverHistorico().addActionListener(
            e -> view.removerHistoricoSelecionado()
        );
    }

    private void salvar() {
        try {
            validarObrigatorios();

            int id = view.getId();

            if (id != 0) {
                CadastroFuncionario existente = buscarPorId(id);

                if (existente == null) {
                    erro(
                        "O funcionário informado não foi encontrado. "
                        + "Clique em Limpar para cadastrar um novo."
                    );
                    return;
                }

                CadastroFuncionario atualizado = criarFuncionario(id);

                funcionarios.set(
                    funcionarios.indexOf(existente),
                    atualizado
                );

                view.setIdentificacao(
                    "Cadastro salvo: " + atualizado.getNome()
                    + " — código " + id
                );

                JOptionPane.showMessageDialog(
                    view,
                    "Alterações salvas com sucesso.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            if (!funcionarios.isEmpty()) {
                Object[] opcoes = {
                    "Cadastrar novo",
                    "Selecionar para atualizar",
                    "Cancelar"
                };

                int escolha = JOptionPane.showOptionDialog(
                    view,
                    "Deseja cadastrar um novo funcionário "
                    + "ou atualizar um existente?",
                    "Salvar funcionário",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
                );

                if (escolha == 1) {
                    CadastroFuncionario selecionado =
                        selecionarFuncionario(
                            "Selecione o funcionário que deseja atualizar"
                        );

                    if (selecionado != null) {
                        view.carregarFuncionario(selecionado);

                        JOptionPane.showMessageDialog(
                            view,
                            "Os dados foram carregados. Faça as alterações "
                            + "e clique em Salvar novamente.",
                            "Funcionário selecionado",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                    return;
                }

                if (escolha != 0) {
                    return;
                }
            }

            CadastroFuncionario novo = criarFuncionario(proximoId);

            funcionarios.add(novo);
            view.setId(proximoId);

            view.setIdentificacao(
                "Funcionário cadastrado: " + novo.getNome()
                + " — código " + proximoId
            );

            proximoId++;

            JOptionPane.showMessageDialog(
                view,
                "Funcionário cadastrado com sucesso.",
                "Cadastro concluído",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {
            erro("Verifique os campos numéricos do cadastro.");
        } catch (IllegalArgumentException ex) {
            erro(ex.getMessage());
        }
    }

    private void excluir() {
        try {
            int id = view.getId();
            CadastroFuncionario funcionario;

            if (id == 0) {
                funcionario = selecionarFuncionario(
                    "Selecione o funcionário que deseja excluir"
                );
            } else {
                funcionario = buscarPorId(id);
            }

            if (funcionario == null) {
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(
                view,
                "Deseja excluir o cadastro de "
                + funcionario.getNome() + "?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (resposta == JOptionPane.YES_OPTION) {
                funcionarios.remove(funcionario);
                view.limparFormulario();

                JOptionPane.showMessageDialog(
                    view,
                    "Funcionário excluído com sucesso.",
                    "Exclusão concluída",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {
            erro("Código do funcionário inválido.");
        }
    }

    private CadastroFuncionario selecionarFuncionario(String titulo) {
        if (funcionarios.isEmpty()) {
            erro("Ainda não há funcionários cadastrados.");
            return null;
        }

        DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{
                "Código",
                "Nome",
                "Matrícula",
                "Cargo",
                "Departamento",
                "Status"
            },
            0
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (CadastroFuncionario funcionario : funcionarios) {
            modelo.addRow(new Object[]{
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getMatricula(),
                funcionario.getCargo(),
                funcionario.getDepartamento(),
                funcionario.getStatus()
            });
        }

        JTable tabela = new JTable(modelo);

        tabela.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        tabela.setRowHeight(25);
        tabela.setAutoCreateRowSorter(true);
        tabela.setFillsViewportHeight(true);

        tabela.setPreferredScrollableViewportSize(
            new Dimension(760, 250)
        );

        JPanel painel = new JPanel(new BorderLayout(0, 8));

        painel.add(
            new JLabel("Selecione uma linha e clique em OK."),
            BorderLayout.NORTH
        );

        painel.add(
            new JScrollPane(tabela),
            BorderLayout.CENTER
        );

        painel.setPreferredSize(new Dimension(780, 290));

        int resposta = JOptionPane.showConfirmDialog(
            view,
            painel,
            titulo,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (resposta != JOptionPane.OK_OPTION
                || tabela.getSelectedRow() < 0) {
            return null;
        }

        int linha = tabela.convertRowIndexToModel(
            tabela.getSelectedRow()
        );

        int idSelecionado = Integer.parseInt(
            modelo.getValueAt(linha, 0).toString()
        );

        return buscarPorId(idSelecionado);
    }

    private CadastroFuncionario criarFuncionario(int id) {
        CadastroFuncionario funcionario = new CadastroFuncionario(
            id,
            view.getNome(),
            view.getMatricula(),
            view.getCargo(),
            view.getDepartamento(),
            view.getEmail(),
            view.getTelefone(),
            view.getStatus()
        );

        funcionario.setDadosPessoais(
            new DadosPessoais(
                view.getNome(),
                view.getNascimento(),
                view.getEstadoCivil(),
                view.getNaturalidade(),
                view.getNacionalidade(),
                view.getEndereco(),
                view.getCidade()
            )
        );

        funcionario.setDadosBancarios(
            new DadosBancarios(
                view.getBanco(),
                view.getCodigoBanco(),
                view.getAgencia(),
                view.getConta(),
                view.getTipoConta(),
                view.getPix()
            )
        );

        funcionario.setDocumentos(
            new Documentos(
                view.getCpf(),
                view.getRg(),
                view.getPassaporte(),
                view.getValidadePassaporte(),
                view.getPis(),
                view.getCtps()
            )
        );

        for (String[] dados : view.obterDependentes()) {
            funcionario.adicionarDependente(
                new Dependentes(
                    dados[0],
                    dados[1],
                    dados[2],
                    dados[3]
                )
            );
        }

        for (String[] dados : view.obterHistorico()) {
            funcionario.adicionarHistorico(
                new Historico(
                    dados[0],
                    dados[1],
                    dados[2],
                    dados[3]
                )
            );
        }

        return funcionario;
    }

    private CadastroFuncionario buscarPorId(int id) {
        for (CadastroFuncionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }

        return null;
    }

    private void validarObrigatorios() {
        if (view.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Informe o nome completo do funcionário."
            );
        }

        if (view.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Informe o CPF do funcionário."
            );
        }

        if ("Selecione o status".equals(view.getStatus())) {
            throw new IllegalArgumentException(
                "Selecione o status do funcionário."
            );
        }
    }

    private void limpar() {
        view.limparFormulario();
    }

    private void executar(Runnable acao) {
        try {
            acao.run();
        } catch (IllegalArgumentException ex) {
            erro(ex.getMessage());
        }
    }

    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(
            view,
            mensagem,
            "Atenção",
            JOptionPane.WARNING_MESSAGE
        );
    }

    public List<CadastroFuncionario> getFuncionarios() {
        return new ArrayList<>(funcionarios);
    }
}