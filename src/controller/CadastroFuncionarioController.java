package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.CadastroFuncionario;
import model.DadosBancarios;
import model.DadosPessoais;
import model.Dependentes;
import model.Documentos;
import model.Historico;
import view.TelaCadastroFuncionario;

public class CadastroFuncionarioController {

    private final TelaCadastroFuncionario view;

    private final List<CadastroFuncionario> funcionarios =
        new ArrayList<>();

    private int proximoId = 1;

    public CadastroFuncionarioController(
        TelaCadastroFuncionario view
    ) {

        this.view = view;

        configurarEventos();
    }

    private void configurarEventos() {

        view.getNovo()
            .addActionListener(
                e -> novo()
            );

        view.getSalvar()
            .addActionListener(
                e -> salvar()
            );

        view.getExcluir()
            .addActionListener(
                e -> excluir()
            );

        view.getLimpar()
            .addActionListener(
                e -> limpar()
            );

        view.getAdicionarDependente()
            .addActionListener(
                e -> executar(
                    () ->
                        view.adicionarDependenteNaTabela()
                )
            );

        view.getRemoverDependente()
            .addActionListener(
                e ->
                    executar(
                        () ->
                            view.removerDependenteSelecionado()
                    )
            );

        view.getAdicionarHistorico()
            .addActionListener(
                e -> executar(
                    () ->
                        view.adicionarHistoricoNaTabela()
                )
            );

        view.getRemoverHistorico()
            .addActionListener(
                e ->
                    executar(
                        () ->
                            view.removerHistoricoSelecionado()
                    )
            );
    }

    private void novo() {

        view.limparFormulario();

        view.setId(proximoId);

        view.setIdentificacao(
            "Novo funcionário - código "
            + proximoId
        );
    }

    private void salvar() {

        try {

            validarObrigatorios();

            int id = view.getId();

            if (id == 0) {

                id = proximoId;

                view.setId(id);
            }

            CadastroFuncionario funcionario =
                criarFuncionario(id);

            substituirFuncionario(
                funcionario
            );

            if (id >= proximoId) {
                proximoId = id + 1;
            }

            view.setIdentificacao(
                "Funcionário "
                + funcionario.getNome()
                + " - código "
                + id
            );

            JOptionPane.showMessageDialog(
                view,
                "Funcionário cadastrado com todos os dados com sucesso.",
                "Cadastro concluído",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {

            erro(
                "O código do funcionário é inválido."
            );

        } catch (IllegalArgumentException ex) {

            erro(
                ex.getMessage()
            );
        }
    }

    private CadastroFuncionario criarFuncionario(
        int id
    ) {

        CadastroFuncionario funcionario =
            new CadastroFuncionario(
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

        for (
            String[] dados :
            view.obterDependentes()
        ) {

            funcionario.adicionarDependente(
                new Dependentes(
                    dados[0],
                    dados[1],
                    dados[2],
                    dados[3]
                )
            );
        }

        for (
            String[] dados :
            view.obterHistorico()
        ) {

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

    private void substituirFuncionario(
        CadastroFuncionario funcionario
    ) {

        for (
            int i = 0;
            i < funcionarios.size();
            i++
        ) {

            if (
                funcionarios
                    .get(i)
                    .getId()
                    ==
                funcionario.getId()
            ) {

                funcionarios.set(
                    i,
                    funcionario
                );

                return;
            }
        }

        funcionarios.add(
            funcionario
        );
    }

    private void excluir() {

        try {

            int id = view.getId();

            if (id == 0) {

                erro(
                    "Nenhum funcionário selecionado para exclusão."
                );

                return;
            }

            CadastroFuncionario encontrado =
                buscarPorId(id);

            if (encontrado == null) {

                erro(
                    "O funcionário informado não está cadastrado."
                );

                return;
            }

            int resposta =
                JOptionPane.showConfirmDialog(
                    view,
                    "Excluir o cadastro completo de "
                    + encontrado.getNome()
                    + "?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

            if (
                resposta ==
                JOptionPane.YES_OPTION
            ) {

                funcionarios.remove(
                    encontrado
                );

                view.limparFormulario();

                JOptionPane.showMessageDialog(
                    view,
                    "Cadastro completo excluído.",
                    "Exclusão concluída",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            erro(
                "O código do funcionário é inválido."
            );
        }
    }

    private CadastroFuncionario buscarPorId(
        int id
    ) {

        for (
            CadastroFuncionario funcionario :
            funcionarios
        ) {

            if (
                funcionario.getId()
                ==
                id
            ) {

                return funcionario;
            }
        }

        return null;
    }

    private void limpar() {

        view.limparFormulario();
    }

    private void validarObrigatorios() {

        if (
            view.getNome().isEmpty()
        ) {

            throw new IllegalArgumentException(
                "Informe o nome completo do funcionário."
            );
        }

        if (
            view.getCpf().isEmpty()
        ) {

            throw new IllegalArgumentException(
                "Informe o CPF do funcionário."
            );
        }

        if (
            "Selecione o status"
            .equals(view.getStatus())
        ) {

            throw new IllegalArgumentException(
                "Selecione o status do funcionário."
            );
        }
    }

    private void executar(
        Runnable acao
    ) {

        try {

            acao.run();

        } catch (
            IllegalArgumentException ex
        ) {

            erro(
                ex.getMessage()
            );
        }
    }

    private void erro(
        String mensagem
    ) {

        JOptionPane.showMessageDialog(
            view,
            mensagem,
            "Atenção",
            JOptionPane.WARNING_MESSAGE
        );
    }

    public List<CadastroFuncionario>
    getFuncionarios() {

        return new ArrayList<>(
            funcionarios
        );
    }
}