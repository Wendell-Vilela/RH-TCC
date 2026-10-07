package model;

import java.util.ArrayList;
import java.util.List;

public class Rotatividade {

    private List<Double> turnoverMensal;
    private List<String> meses;

    private double absenteismoRH;
    private double absenteismoOperacoes;
    private double absenteismoComercial;
    private double absenteismoFinanceiro;

    private double metaAbsenteismo;
    private String alerta;

    public Rotatividade() {
        turnoverMensal = new ArrayList<>();
        meses = new ArrayList<>();
    }

    public Rotatividade(List<Double> turnoverMensal,
                        List<String> meses,
                        double absenteismoRH,
                        double absenteismoOperacoes,
                        double absenteismoComercial,
                        double absenteismoFinanceiro,
                        double metaAbsenteismo,
                        String alerta) {

        this.turnoverMensal = turnoverMensal;
        this.meses = meses;
        this.absenteismoRH = absenteismoRH;
        this.absenteismoOperacoes = absenteismoOperacoes;
        this.absenteismoComercial = absenteismoComercial;
        this.absenteismoFinanceiro = absenteismoFinanceiro;
        this.metaAbsenteismo = metaAbsenteismo;
        this.alerta = alerta;
    }

    public List<Double> getTurnoverMensal() {
        return turnoverMensal;
    }

    public void setTurnoverMensal(List<Double> turnoverMensal) {
        this.turnoverMensal = turnoverMensal;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    public double getAbsenteismoRH() {
        return absenteismoRH;
    }

    public void setAbsenteismoRH(double absenteismoRH) {
        this.absenteismoRH = absenteismoRH;
    }

    public double getAbsenteismoOperacoes() {
        return absenteismoOperacoes;
    }

    public void setAbsenteismoOperacoes(double absenteismoOperacoes) {
        this.absenteismoOperacoes = absenteismoOperacoes;
    }

    public double getAbsenteismoComercial() {
        return absenteismoComercial;
    }

    public void setAbsenteismoComercial(double absenteismoComercial) {
        this.absenteismoComercial = absenteismoComercial;
    }

    public double getAbsenteismoFinanceiro() {
        return absenteismoFinanceiro;
    }

    public void setAbsenteismoFinanceiro(double absenteismoFinanceiro) {
        this.absenteismoFinanceiro = absenteismoFinanceiro;
    }

    public double getMetaAbsenteismo() {
        return metaAbsenteismo;
    }

    public void setMetaAbsenteismo(double metaAbsenteismo) {
        this.metaAbsenteismo = metaAbsenteismo;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public void adicionarTurnover(double valor) {
        turnoverMensal.add(valor);
    }

    public void adicionarMes(String mes) {
        meses.add(mes);
    }
}