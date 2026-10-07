package model;

public class FuncionarioAbsenteismo {

    private int id;

    private String nome;

    private int faltas;

    private double taxaAbs;

    public FuncionarioAbsenteismo() {

    }

    public FuncionarioAbsenteismo(
        int id,
        String nome,
        int faltas,
        double taxaAbs
    ) {
        this.id = id;
        this.nome = nome;
        this.faltas = faltas;
        this.taxaAbs = taxaAbs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public double getTaxaAbs() {
        return taxaAbs;
    }

    public void setTaxaAbs(double taxaAbs) {
        this.taxaAbs = taxaAbs;
    }
}