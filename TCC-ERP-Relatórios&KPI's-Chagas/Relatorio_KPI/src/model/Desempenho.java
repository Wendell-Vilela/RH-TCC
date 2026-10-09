package model;

import java.util.ArrayList;
import java.util.List;

public class Desempenho {

    private double desempenhoRH;
    private double desempenhoComercial;
    private double desempenhoFinanceiro;
    private double desempenhoOperacoes;

    private double comunicacao;
    private double entrega;
    private double lideranca;
    private double qualidade;

    private List<String[]> colaboradores;

    public Desempenho() {
        colaboradores = new ArrayList<>();
    }

    public Desempenho(double desempenhoRH, double desempenhoComercial,
                      double desempenhoFinanceiro, double desempenhoOperacoes,
                      double comunicacao, double entrega,
                      double lideranca, double qualidade,
                      List<String[]> colaboradores) {

        this.desempenhoRH = desempenhoRH;
        this.desempenhoComercial = desempenhoComercial;
        this.desempenhoFinanceiro = desempenhoFinanceiro;
        this.desempenhoOperacoes = desempenhoOperacoes;
        this.comunicacao = comunicacao;
        this.entrega = entrega;
        this.lideranca = lideranca;
        this.qualidade = qualidade;
        this.colaboradores = colaboradores;
    }

    public double getDesempenhoRH() {
        return desempenhoRH;
    }

    public void setDesempenhoRH(double desempenhoRH) {
        this.desempenhoRH = desempenhoRH;
    }

    public double getDesempenhoComercial() {
        return desempenhoComercial;
    }

    public void setDesempenhoComercial(double desempenhoComercial) {
        this.desempenhoComercial = desempenhoComercial;
    }

    public double getDesempenhoFinanceiro() {
        return desempenhoFinanceiro;
    }

    public void setDesempenhoFinanceiro(double desempenhoFinanceiro) {
        this.desempenhoFinanceiro = desempenhoFinanceiro;
    }

    public double getDesempenhoOperacoes() {
        return desempenhoOperacoes;
    }

    public void setDesempenhoOperacoes(double desempenhoOperacoes) {
        this.desempenhoOperacoes = desempenhoOperacoes;
    }

    public double getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(double comunicacao) {
        this.comunicacao = comunicacao;
    }

    public double getEntrega() {
        return entrega;
    }

    public void setEntrega(double entrega) {
        this.entrega = entrega;
    }

    public double getLideranca() {
        return lideranca;
    }

    public void setLideranca(double lideranca) {
        this.lideranca = lideranca;
    }

    public double getQualidade() {
        return qualidade;
    }

    public void setQualidade(double qualidade) {
        this.qualidade = qualidade;
    }

    public List<String[]> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<String[]> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void adicionarColaborador(String nome, String nota, String status) {
        colaboradores.add(new String[]{nome, nota, status});
    }
}