package model;

public class Meta {

    private int id;
    private String funcionario;
    private String cargo;
    private String gestor;
    private String area;
    private String meta;
    private String prazo;
    private String status;

    public Meta(int id, String funcionario, String cargo, String gestor,
                String area, String meta, String prazo, String status) {

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

    public String getFuncionario() {
        return funcionario;
    }

    public String getCargo() {
        return cargo;
    }

    public String getGestor() {
        return gestor;
    }

    public String getArea() {
        return area;
    }

    public String getMeta() {
        return meta;
    }

    public String getPrazo() {
        return prazo;
    }

    public String getStatus() {
        return status;
    }
    
}