package model;

public class Historico {

    private String ano;
    private String evento;
    private String cargo;
    private String observacao;

    public Historico() {
    }

    public Historico(String ano, String evento, String cargo, String observacao) {
        this.ano = ano;
        this.evento = evento;
        this.cargo = cargo;
        this.observacao = observacao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}