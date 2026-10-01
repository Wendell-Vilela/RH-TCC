package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CadastroFuncionario;
import model.DadosBancarios;
import model.DadosPessoais;
import model.Dependentes;
import model.Documentos;
import model.Historico;

public class GestaoFuncionariosController {

    private final Map<Integer, CadastroFuncionario> funcionarios;
    private final Map<Integer, DadosBancarios> dadosBancarios;
    private final Map<Integer, DadosPessoais> dadosPessoais;
    private final Map<Integer, List<Dependentes>> dependentes;
    private final Map<Integer, Documentos> documentos;
    private final Map<Integer, List<Historico>> historicos;

    private int proximoId;

    public GestaoFuncionariosController() {
        funcionarios = new HashMap<>();
        dadosBancarios = new HashMap<>();
        dadosPessoais = new HashMap<>();
        dependentes = new HashMap<>();
        documentos = new HashMap<>();
        historicos = new HashMap<>();
        proximoId = 1;
    }

    public CadastroFuncionario cadastrarFuncionario(
            String nome,
            String matricula,
            String cargo,
            String departamento,
            String email,
            String telefone,
            String status) {

        validarFuncionario(
                nome,
                matricula,
                cargo,
                departamento,
                email,
                telefone,
                status
        );

        if (buscarPorMatricula(matricula) != null) {
            throw new IllegalArgumentException(
                    "Já existe um funcionário com essa matrícula."
            );
        }

        CadastroFuncionario funcionario =
                new CadastroFuncionario();

        funcionario.setId(proximoId++);
        funcionario.setNome(nome.trim());
        funcionario.setMatricula(matricula.trim());
        funcionario.setCargo(cargo.trim());
        funcionario.setDepartamento(departamento.trim());
        funcionario.setEmail(email.trim());
        funcionario.setTelefone(telefone.trim());
        funcionario.setStatus(status.trim());

        funcionarios.put(
                funcionario.getId(),
                funcionario
        );

        historicos.put(
                funcionario.getId(),
                new ArrayList<>()
        );

        dependentes.put(
                funcionario.getId(),
                new ArrayList<>()
        );

        return funcionario;
    }

    public void salvarFuncionario(
            CadastroFuncionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "Funcionário inválido."
            );
        }

        validarFuncionario(
                funcionario.getNome(),
                funcionario.getMatricula(),
                funcionario.getCargo(),
                funcionario.getDepartamento(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getStatus()
        );

        CadastroFuncionario funcionarioExistente =
                buscarPorMatricula(
                        funcionario.getMatricula()
                );

        if (funcionarioExistente != null &&
                funcionarioExistente.getId() != funcionario.getId()) {

            throw new IllegalArgumentException(
                    "A matrícula já pertence a outro funcionário."
            );
        }

        if (funcionario.getId() <= 0) {
            funcionario.setId(proximoId++);
        } else if (funcionario.getId() >= proximoId) {
            proximoId = funcionario.getId() + 1;
        }

        funcionarios.put(
                funcionario.getId(),
                funcionario
        );

        if (!historicos.containsKey(funcionario.getId())) {
            historicos.put(
                    funcionario.getId(),
                    new ArrayList<>()
            );
        }

        if (!dependentes.containsKey(funcionario.getId())) {
            dependentes.put(
                    funcionario.getId(),
                    new ArrayList<>()
            );
        }
    }

    public CadastroFuncionario buscarPorId(int id) {
        return funcionarios.get(id);
    }

    public CadastroFuncionario buscarPorMatricula(
            String matricula) {

        if (matricula == null) {
            return null;
        }

        for (CadastroFuncionario funcionario :
                funcionarios.values()) {

            if (matricula.trim().equalsIgnoreCase(
                    funcionario.getMatricula())) {

                return funcionario;
            }
        }

        return null;
    }

    public List<CadastroFuncionario> buscarPorNome(
            String nome) {

        List<CadastroFuncionario> resultado =
                new ArrayList<>();

        if (nome == null) {
            return resultado;
        }

        String busca = nome.trim().toLowerCase();

        for (CadastroFuncionario funcionario :
                funcionarios.values()) {

            if (funcionario.getNome() != null &&
                    funcionario.getNome()
                            .toLowerCase()
                            .contains(busca)) {

                resultado.add(funcionario);
            }
        }

        return resultado;
    }

    public List<CadastroFuncionario> listarFuncionarios() {
        return new ArrayList<>(
                funcionarios.values()
        );
    }

    public List<CadastroFuncionario> listarAtivos() {
        return listarPorStatus("Ativo");
    }

    public List<CadastroFuncionario> listarPorStatus(
            String status) {

        List<CadastroFuncionario> resultado =
                new ArrayList<>();

        if (status == null) {
            return resultado;
        }

        for (CadastroFuncionario funcionario :
                funcionarios.values()) {

            if (status.equalsIgnoreCase(
                    funcionario.getStatus())) {

                resultado.add(funcionario);
            }
        }

        return resultado;
    }

    public List<CadastroFuncionario> listarPorDepartamento(
            String departamento) {

        List<CadastroFuncionario> resultado =
                new ArrayList<>();

        if (departamento == null) {
            return resultado;
        }

        for (CadastroFuncionario funcionario :
                funcionarios.values()) {

            if (departamento.equalsIgnoreCase(
                    funcionario.getDepartamento())) {

                resultado.add(funcionario);
            }
        }

        return resultado;
    }

    public List<CadastroFuncionario> listarPorCargo(
            String cargo) {

        List<CadastroFuncionario> resultado =
                new ArrayList<>();

        if (cargo == null) {
            return resultado;
        }

        for (CadastroFuncionario funcionario :
                funcionarios.values()) {

            if (cargo.equalsIgnoreCase(
                    funcionario.getCargo())) {

                resultado.add(funcionario);
            }
        }

        return resultado;
    }

    public void atualizarFuncionario(
            int id,
            String nome,
            String matricula,
            String cargo,
            String departamento,
            String email,
            String telefone,
            String status) {

        CadastroFuncionario funcionario =
                buscarPorId(id);

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "Funcionário não encontrado."
            );
        }

        CadastroFuncionario outro =
                buscarPorMatricula(matricula);

        if (outro != null &&
                outro.getId() != id) {

            throw new IllegalArgumentException(
                    "A matrícula já pertence a outro funcionário."
            );
        }

        validarFuncionario(
                nome,
                matricula,
                cargo,
                departamento,
                email,
                telefone,
                status
        );

        funcionario.setNome(nome.trim());
        funcionario.setMatricula(matricula.trim());
        funcionario.setCargo(cargo.trim());
        funcionario.setDepartamento(
                departamento.trim()
        );
        funcionario.setEmail(email.trim());
        funcionario.setTelefone(telefone.trim());
        funcionario.setStatus(status.trim());
    }

    public void alterarStatus(
            int id,
            String novoStatus) {

        CadastroFuncionario funcionario =
                buscarPorId(id);

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "Funcionário não encontrado."
            );
        }

        if (!statusValido(novoStatus)) {
            throw new IllegalArgumentException(
                    "Status inválido."
            );
        }

        funcionario.setStatus(
                novoStatus.trim()
        );
    }

    public void desligarFuncionario(int id) {
        alterarStatus(
                id,
                "Desligado"
        );
    }

    public void excluirFuncionario(int id) {

        if (!funcionarios.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Funcionário não encontrado."
            );
        }

        funcionarios.remove(id);
        dadosBancarios.remove(id);
        dadosPessoais.remove(id);
        documentos.remove(id);
        dependentes.remove(id);
        historicos.remove(id);
    }

    public void salvarDadosBancarios(
            int idFuncionario,
            DadosBancarios dados) {

        validarFuncionarioExistente(
                idFuncionario
        );

        if (dados == null) {
            throw new IllegalArgumentException(
                    "Dados bancários inválidos."
            );
        }

        dadosBancarios.put(
                idFuncionario,
                dados
        );
    }

    public DadosBancarios buscarDadosBancarios(
            int idFuncionario) {

        validarFuncionarioExistente(
                idFuncionario
        );

        return dadosBancarios.get(
                idFuncionario
        );
    }

    public void excluirDadosBancarios(
            int idFuncionario) {

        dadosBancarios.remove(
                idFuncionario
        );
    }

    public void salvarDadosPessoais(
            int idFuncionario,
            DadosPessoais dados) {

        validarFuncionarioExistente(
                idFuncionario
        );

        if (dados == null) {
            throw new IllegalArgumentException(
                    "Dados pessoais inválidos."
            );
        }

        dadosPessoais.put(
                idFuncionario,
                dados
        );
    }

    public DadosPessoais buscarDadosPessoais(
            int idFuncionario) {

        validarFuncionarioExistente(
                idFuncionario
        );

        return dadosPessoais.get(
                idFuncionario
        );
    }

    public void excluirDadosPessoais(
            int idFuncionario) {

        dadosPessoais.remove(
                idFuncionario
        );
    }

    public void adicionarDependente(
            int idFuncionario,
            Dependentes dependente) {

        validarFuncionarioExistente(
                idFuncionario
        );

        if (dependente == null) {
            throw new IllegalArgumentException(
                    "Dependente inválido."
            );
        }

        if (dependente.getNome() == null ||
                dependente.getNome().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O nome do dependente é obrigatório."
            );
        }

        dependentes
                .computeIfAbsent(
                        idFuncionario,
                        k -> new ArrayList<>()
                )
                .add(dependente);
    }

    public List<Dependentes> listarDependentes(
            int idFuncionario) {

        validarFuncionarioExistente(
                idFuncionario
        );

        return new ArrayList<>(
                dependentes.getOrDefault(
                        idFuncionario,
                        new ArrayList<>()
                )
        );
    }

    public void excluirDependente(
            int idFuncionario,
            Dependentes dependente) {

        validarFuncionarioExistente(
                idFuncionario
        );

        List<Dependentes> lista =
                dependentes.get(idFuncionario);

        if (lista != null) {
            lista.remove(dependente);
        }
    }

    public void salvarDocumentos(
            int idFuncionario,
            Documentos documento) {

        validarFuncionarioExistente(
                idFuncionario
        );

        if (documento == null) {
            throw new IllegalArgumentException(
                    "Documento inválido."
            );
        }

        documentos.put(
                idFuncionario,
                documento
        );
    }

    public Documentos buscarDocumentos(
            int idFuncionario) {

        validarFuncionarioExistente(
                idFuncionario
        );

        return documentos.get(
                idFuncionario
        );
    }

    public void excluirDocumentos(
            int idFuncionario) {

        documentos.remove(
                idFuncionario
        );
    }

    public void adicionarHistorico(
            int idFuncionario,
            Historico historico) {

        validarFuncionarioExistente(
                idFuncionario
        );

        if (historico == null) {
            throw new IllegalArgumentException(
                    "Histórico inválido."
            );
        }

        historicos
                .computeIfAbsent(
                        idFuncionario,
                        k -> new ArrayList<>()
                )
                .add(historico);
    }

    public List<Historico> listarHistorico(
            int idFuncionario) {

        validarFuncionarioExistente(
                idFuncionario
        );

        return new ArrayList<>(
                historicos.getOrDefault(
                        idFuncionario,
                        new ArrayList<>()
                )
        );
    }

    public int quantidadeFuncionarios() {
        return funcionarios.size();
    }

    public int quantidadeAtivos() {
        return listarAtivos().size();
    }

    public int quantidadeDesligados() {
        return listarPorStatus(
                "Desligado"
        ).size();
    }

    public boolean funcionarioExiste(int id) {
        return funcionarios.containsKey(id);
    }

    public boolean matriculaExiste(
            String matricula) {

        return buscarPorMatricula(
                matricula
        ) != null;
    }

    public void limparDados() {
        funcionarios.clear();
        dadosBancarios.clear();
        dadosPessoais.clear();
        dependentes.clear();
        documentos.clear();
        historicos.clear();
        proximoId = 1;
    }

    private void validarFuncionarioExistente(
            int idFuncionario) {

        if (!funcionarioExiste(idFuncionario)) {
            throw new IllegalArgumentException(
                    "Funcionário não encontrado."
            );
        }
    }

    private void validarFuncionario(
            String nome,
            String matricula,
            String cargo,
            String departamento,
            String email,
            String telefone,
            String status) {

        if (nome == null ||
                nome.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Nome é obrigatório."
            );
        }

        if (matricula == null ||
                matricula.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Matrícula é obrigatória."
            );
        }

        if (cargo == null ||
                cargo.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Cargo é obrigatório."
            );
        }

        if (departamento == null ||
                departamento.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Departamento é obrigatório."
            );
        }

        if (email == null ||
                email.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "E-mail é obrigatório."
            );
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException(
                    "E-mail inválido."
            );
        }

        if (telefone == null ||
                telefone.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Telefone é obrigatório."
            );
        }

        if (!statusValido(status)) {
            throw new IllegalArgumentException(
                    "Status inválido."
            );
        }
    }

    private boolean statusValido(
            String status) {

        if (status == null) {
            return false;
        }

        return status.equalsIgnoreCase("Ativo")
                || status.equalsIgnoreCase("Afastado")
                || status.equalsIgnoreCase("Em férias")
                || status.equalsIgnoreCase("Desligado");
    }
}