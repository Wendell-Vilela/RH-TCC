package model;

public class MetasCompetencias {

    private int id;
    private String funcionario;
    private String cargo;
    private String gestor;
    private String area;
    private String meta;
    private String prazo;
    private String status;

    public MetasCompetencias() {
    }

    public MetasCompetencias(int id, String funcionario, String cargo,
            String gestor, String area, String meta,
            String prazo, String status) {

        this.id = id;
        this.funcionario = funcionario;
        this.cargo = cargo;
        this.gestor = gestor;
        this.area = area;
        this.meta = meta;
        this.prazo = prazo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
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
}