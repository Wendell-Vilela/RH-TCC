package model;

public class Contratacao {

    private int id;
    private String candidato;
    private String cargo;
    private String dataAdmissao;
    private String salario;
    private String tipoContrato;

    public Contratacao() {
    }

    public Contratacao(int id, String candidato, String cargo,
            String dataAdmissao, String salario, String tipoContrato) {

        this.id = id;
        this.candidato = candidato;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}