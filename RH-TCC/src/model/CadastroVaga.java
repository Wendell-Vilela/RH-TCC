package model;

public class CadastroVaga {

    private int id;
    private String cargo;
    private String departamento;
    private String tipoContrato;
    private String modalidade;
    private String salario;
    private String status;
    private String descricao;

    public CadastroVaga() {
    }

    public CadastroVaga(int id, String cargo, String departamento,
            String tipoContrato, String modalidade, String salario,
            String status, String descricao) {

        this.id = id;
        this.cargo = cargo;
        this.departamento = departamento;
        this.tipoContrato = tipoContrato;
        this.modalidade = modalidade;
        this.salario = salario;
        this.status = status;
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}