package model;

public class TaxaTurnover {

    private String departamento;
    private int colaboradores;
    private int desligamentos;
    private double taxaSetor;

    public TaxaTurnover() {
    }

    public TaxaTurnover(String departamento, int colaboradores,
                                int desligamentos, double taxaSetor) {
        this.departamento = departamento;
        this.colaboradores = colaboradores;
        this.desligamentos = desligamentos;
        this.taxaSetor = taxaSetor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(int colaboradores) {
        this.colaboradores = colaboradores;
    }

    public int getDesligamentos() {
        return desligamentos;
    }

    public void setDesligamentos(int desligamentos) {
        this.desligamentos = desligamentos;
    }

    public double getTaxaSetor() {
        return taxaSetor;
    }

    public void setTaxaSetor(double taxaSetor) {
        this.taxaSetor = taxaSetor;
    }
}