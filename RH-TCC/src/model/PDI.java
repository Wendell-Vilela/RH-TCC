package model;

public class PDI {

    private String area;
    private String funcionario;
    private String proximaAcao;
    private String descricao;
    private String prazo;
    private String status;
    private int progresso;

    public PDI() {
    }

    public PDI(String area, String funcionario, String proximaAcao,
            String descricao, String prazo, String status, int progresso) {

        this.area = area;
        this.funcionario = funcionario;
        this.proximaAcao = proximaAcao;
        this.descricao = descricao;
        this.prazo = prazo;
        this.status = status;
        this.progresso = progresso;
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

    public String getProximaAcao() {
        return proximaAcao;
    }

    public void setProximaAcao(String proximaAcao) {
        this.proximaAcao = proximaAcao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        this.progresso = progresso;
    }
}