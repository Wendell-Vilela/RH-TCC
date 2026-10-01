package model;

public class FuncionariosAtivos {

    private int headcountTotal;
    private int efetivos;
    private double percentualEfetivos;
    private int terceirizados;
    private double percentualTerceirizados;
    private double crescimentoMensal;
    private double taxaTurnover;
    private double tempoMedioCasa;
    private String maiorArea;
    private int colaboradoresMaiorArea;
    private String descricao;
    private String ultimaAtualizacao;
    private int metaFuncionarios;

    public FuncionariosAtivos() {
    }

    public FuncionariosAtivos(int headcountTotal, int efetivos, double percentualEfetivos,
                              int terceirizados, double percentualTerceirizados,
                              double crescimentoMensal, double taxaTurnover,
                              double tempoMedioCasa, String maiorArea,
                              int colaboradoresMaiorArea, String descricao,
                              String ultimaAtualizacao, int metaFuncionarios) {

        this.headcountTotal = headcountTotal;
        this.efetivos = efetivos;
        this.percentualEfetivos = percentualEfetivos;
        this.terceirizados = terceirizados;
        this.percentualTerceirizados = percentualTerceirizados;
        this.crescimentoMensal = crescimentoMensal;
        this.taxaTurnover = taxaTurnover;
        this.tempoMedioCasa = tempoMedioCasa;
        this.maiorArea = maiorArea;
        this.colaboradoresMaiorArea = colaboradoresMaiorArea;
        this.descricao = descricao;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.metaFuncionarios = metaFuncionarios;
    }

    public int getHeadcountTotal() {
        return headcountTotal;
    }

    public void setHeadcountTotal(int headcountTotal) {
        this.headcountTotal = headcountTotal;
    }

    public int getEfetivos() {
        return efetivos;
    }

    public void setEfetivos(int efetivos) {
        this.efetivos = efetivos;
    }

    public double getPercentualEfetivos() {
        return percentualEfetivos;
    }

    public void setPercentualEfetivos(double percentualEfetivos) {
        this.percentualEfetivos = percentualEfetivos;
    }

    public int getTerceirizados() {
        return terceirizados;
    }

    public void setTerceirizados(int terceirizados) {
        this.terceirizados = terceirizados;
    }

    public double getPercentualTerceirizados() {
        return percentualTerceirizados;
    }

    public void setPercentualTerceirizados(double percentualTerceirizados) {
        this.percentualTerceirizados = percentualTerceirizados;
    }

    public double getCrescimentoMensal() {
        return crescimentoMensal;
    }

    public void setCrescimentoMensal(double crescimentoMensal) {
        this.crescimentoMensal = crescimentoMensal;
    }

    public double getTaxaTurnover() {
        return taxaTurnover;
    }

    public void setTaxaTurnover(double taxaTurnover) {
        this.taxaTurnover = taxaTurnover;
    }

    public double getTempoMedioCasa() {
        return tempoMedioCasa;
    }

    public void setTempoMedioCasa(double tempoMedioCasa) {
        this.tempoMedioCasa = tempoMedioCasa;
    }

    public String getMaiorArea() {
        return maiorArea;
    }

    public void setMaiorArea(String maiorArea) {
        this.maiorArea = maiorArea;
    }

    public int getColaboradoresMaiorArea() {
        return colaboradoresMaiorArea;
    }

    public void setColaboradoresMaiorArea(int colaboradoresMaiorArea) {
        this.colaboradoresMaiorArea = colaboradoresMaiorArea;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public int getMetaFuncionarios() {
        return metaFuncionarios;
    }

    public void setMetaFuncionarios(int metaFuncionarios) {
        this.metaFuncionarios = metaFuncionarios;
    }
}