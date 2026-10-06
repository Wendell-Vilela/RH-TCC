package model;

public class NovasAdmissoes {

    private int operacoes;
    private int vendas;
    private int tecnologia;
    private int administrativo;
    private int totalAcumulado;

    public NovasAdmissoes() {
    }

    public NovasAdmissoes(int operacoes, int vendas, int tecnologia,
                          int administrativo, int totalAcumulado) {

        this.operacoes = operacoes;
        this.vendas = vendas;
        this.tecnologia = tecnologia;
        this.administrativo = administrativo;
        this.totalAcumulado = totalAcumulado;
    }

    public int getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(int operacoes) {
        this.operacoes = operacoes;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    public int getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(int tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(int administrativo) {
        this.administrativo = administrativo;
    }

    public int getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(int totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }
}