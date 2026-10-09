package model;

public class Custo {

    private double custoPorColaborador;
    private double custoTreinamento;
    private double projecaoAnual;

    private double folha;
    private double beneficios;
    private double treinamento;
    private double recrutamento;

    private double janeiro;
    private double fevereiro;
    private double marco;
    private double abril;
    private double maio;
    private double junho;

    public Custo() {
    }

    public Custo(double custoPorColaborador, double custoTreinamento, double projecaoAnual,
                 double folha, double beneficios, double treinamento, double recrutamento,
                 double janeiro, double fevereiro, double marco, double abril,
                 double maio, double junho) {

        this.custoPorColaborador = custoPorColaborador;
        this.custoTreinamento = custoTreinamento;
        this.projecaoAnual = projecaoAnual;
        this.folha = folha;
        this.beneficios = beneficios;
        this.treinamento = treinamento;
        this.recrutamento = recrutamento;
        this.janeiro = janeiro;
        this.fevereiro = fevereiro;
        this.marco = marco;
        this.abril = abril;
        this.maio = maio;
        this.junho = junho;
    }

    public double getCustoPorColaborador() {
        return custoPorColaborador;
    }

    public void setCustoPorColaborador(double custoPorColaborador) {
        this.custoPorColaborador = custoPorColaborador;
    }

    public double getCustoTreinamento() {
        return custoTreinamento;
    }

    public void setCustoTreinamento(double custoTreinamento) {
        this.custoTreinamento = custoTreinamento;
    }

    public double getProjecaoAnual() {
        return projecaoAnual;
    }

    public void setProjecaoAnual(double projecaoAnual) {
        this.projecaoAnual = projecaoAnual;
    }

    public double getFolha() {
        return folha;
    }

    public void setFolha(double folha) {
        this.folha = folha;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(double beneficios) {
        this.beneficios = beneficios;
    }

    public double getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(double treinamento) {
        this.treinamento = treinamento;
    }

    public double getRecrutamento() {
        return recrutamento;
    }

    public void setRecrutamento(double recrutamento) {
        this.recrutamento = recrutamento;
    }

    public double getJaneiro() {
        return janeiro;
    }

    public void setJaneiro(double janeiro) {
        this.janeiro = janeiro;
    }

    public double getFevereiro() {
        return fevereiro;
    }

    public void setFevereiro(double fevereiro) {
        this.fevereiro = fevereiro;
    }

    public double getMarco() {
        return marco;
    }

    public void setMarco(double marco) {
        this.marco = marco;
    }

    public double getAbril() {
        return abril;
    }

    public void setAbril(double abril) {
        this.abril = abril;
    }

    public double getMaio() {
        return maio;
    }

    public void setMaio(double maio) {
        this.maio = maio;
    }

    public double getJunho() {
        return junho;
    }

    public void setJunho(double junho) {
        this.junho = junho;
    }
}