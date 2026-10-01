package model;

public class GestaoFerias {

    private String departamento;
    private int ate12Meses;
    private int de12a18Meses;
    private int riscoMais18Meses;

    public GestaoFerias() {
    }

    public GestaoFerias(String departamento, int ate12Meses,
            int de12a18Meses, int riscoMais18Meses) {

        this.departamento = departamento;
        this.ate12Meses = ate12Meses;
        this.de12a18Meses = de12a18Meses;
        this.riscoMais18Meses = riscoMais18Meses;
    }

    // Getters e Setters

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getAte12Meses() {
        return ate12Meses;
    }

    public void setAte12Meses(int ate12Meses) {
        this.ate12Meses = ate12Meses;
    }

    public int getDe12a18Meses() {
        return de12a18Meses;
    }

    public void setDe12a18Meses(int de12a18Meses) {
        this.de12a18Meses = de12a18Meses;
    }

    public int getRiscoMais18Meses() {
        return riscoMais18Meses;
    }

    public void setRiscoMais18Meses(int riscoMais18Meses) {
        this.riscoMais18Meses = riscoMais18Meses;
    }
}