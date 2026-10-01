package model;

public class CursosTrilhas {

    private String area;
    private String funcionario;
    private String curso;
    private String cargaHoraria;
    private String fornecedor;
    private String status;

    public CursosTrilhas() {
    }

    public CursosTrilhas(String area, String funcionario, String curso,
            String cargaHoraria, String fornecedor, String status) {

        this.area = area;
        this.funcionario = funcionario;
        this.curso = curso;
        this.cargaHoraria = cargaHoraria;
        this.fornecedor = fornecedor;
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}