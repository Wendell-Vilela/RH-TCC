package model;

public class CustoMensal {

    private double investimentoTotal;
    private double salarioTotal;
    private double encargoTotal;
    private double beneficioTotal;

    public CustoMensal() {
    }

    public CustoMensal(double investimentoTotal, double salarioTotal,
            double encargoTotal, double beneficioTotal) {

        this.investimentoTotal = investimentoTotal;
        this.salarioTotal = salarioTotal;
        this.encargoTotal = encargoTotal;
        this.beneficioTotal = beneficioTotal;
    }

    public double getInvestimentoTotal() {
        return investimentoTotal;
    }

    public void setInvestimentoTotal(double investimentoTotal) {
        this.investimentoTotal = investimentoTotal;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public double getEncargoTotal() {
        return encargoTotal;
    }

    public void setEncargoTotal(double encargoTotal) {
        this.encargoTotal = encargoTotal;
    }

    public double getBeneficioTotal() {
        return beneficioTotal;
    }

    public void setBeneficioTotal(double beneficioTotal) {
        this.beneficioTotal = beneficioTotal;
    }
}