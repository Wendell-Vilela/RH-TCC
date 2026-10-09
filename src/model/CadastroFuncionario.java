
package model;

import java.util.ArrayList;
import java.util.List;

public class CadastroFuncionario {

    private int id;
    private String nome;
    private String matricula;
    private String cargo;
    private String departamento;
    private String email;
    private String telefone;
    private String status;

    private DadosPessoais dadosPessoais;
    private DadosBancarios dadosBancarios;
    private Documentos documentos;

    private final List<Dependentes> dependentes = new ArrayList<>();
    private final List<Historico> historico = new ArrayList<>();

    public CadastroFuncionario() {
    }

    public CadastroFuncionario(
            int id,
            String nome,
            String matricula,
            String cargo,
            String departamento,
            String email,
            String telefone,
            String status) {

        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.departamento = departamento;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public DadosBancarios getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(DadosBancarios dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    public Documentos getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documentos documentos) {
        this.documentos = documentos;
    }

    public void adicionarDependente(Dependentes dependente) {
        if (dependente != null) {
            dependentes.add(dependente);
        }
    }

    public List<Dependentes> getDependentes() {
        return new ArrayList<>(dependentes);
    }

    public void setDependentes(List<Dependentes> dependentes) {
        this.dependentes.clear();

        if (dependentes != null) {
            this.dependentes.addAll(dependentes);
        }
    }

    public void adicionarHistorico(Historico registro) {
        if (registro != null) {
            historico.add(registro);
        }
    }

    public List<Historico> getHistorico() {
        return new ArrayList<>(historico);
    }

    public void setHistorico(List<Historico> historico) {
        this.historico.clear();

        if (historico != null) {
            this.historico.addAll(historico);
        }
    }
}