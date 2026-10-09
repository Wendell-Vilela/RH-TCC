=package model;

public class VGI {

    private double turnover;
    private double absenteismo;
    private double desempenhoMedio;
    private double custosRH;

    private double metaTurnover;
    private double metaAbsenteismo;
    private double metaDesempenho;
    private double metaCustosRH;

    private String tendenciaTurnover;
    private String tendenciaAbsenteismo;
    private String tendenciaDesempenho;
    private String tendenciaCustosRH;

    public VGI() {
    }

    public VGI(double turnover, double absenteismo, double desempenhoMedio, double custosRH,
               double metaTurnover, double metaAbsenteismo, double metaDesempenho, double metaCustosRH,
               String tendenciaTurnover, String tendenciaAbsenteismo,
               String tendenciaDesempenho, String tendenciaCustosRH) {

        this.turnover = turnover;
        this.absenteismo = absenteismo;
        this.desempenhoMedio = desempenhoMedio;
        this.custosRH = custosRH;

        this.metaTurnover = metaTurnover;
        this.metaAbsenteismo = metaAbsenteismo;
        this.metaDesempenho = metaDesempenho;
        this.metaCustosRH = metaCustosRH;

        this.tendenciaTurnover = tendenciaTurnover;
        this.tendenciaAbsenteismo = tendenciaAbsenteismo;
        this.tendenciaDesempenho = tendenciaDesempenho;
        this.tendenciaCustosRH = tendenciaCustosRH;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getAbsenteismo() {
        return absenteismo;
    }

    public void setAbsenteismo(double absenteismo) {
        this.absenteismo = absenteismo;
    }

    public double getDesempenhoMedio() {
        return desempenhoMedio;
    }

    public void setDesempenhoMedio(double desempenhoMedio) {
        this.desempenhoMedio = desempenhoMedio;
    }

    public double getCustosRH() {
        return custosRH;
    }

    public void setCustosRH(double custosRH) {
        this.custosRH = custosRH;
    }

    public double getMetaTurnover() {
        return metaTurnover;
    }

    public void setMetaTurnover(double metaTurnover) {
        this.metaTurnover = metaTurnover;
    }

    public double getMetaAbsenteismo() {
        return metaAbsenteismo;
    }

    public void setMetaAbsenteismo(double metaAbsenteismo) {
        this.metaAbsenteismo = metaAbsenteismo;
    }

    public double getMetaDesempenho() {
        return metaDesempenho;
    }

    public void setMetaDesempenho(double metaDesempenho) {
        this.metaDesempenho = metaDesempenho;
    }

    public double getMetaCustosRH() {
        return metaCustosRH;
    }

    public void setMetaCustosRH(double metaCustosRH) {
        this.metaCustosRH = metaCustosRH;
    }

    public String getTendenciaTurnover() {
        return tendenciaTurnover;
    }

    public void setTendenciaTurnover(String tendenciaTurnover) {
        this.tendenciaTurnover = tendenciaTurnover;
    }

    public String getTendenciaAbsenteismo() {
        return tendenciaAbsenteismo;
    }

    public void setTendenciaAbsenteismo(String tendenciaAbsenteismo) {
        this.tendenciaAbsenteismo = tendenciaAbsenteismo;
    }

    public String getTendenciaDesempenho() {
        return tendenciaDesempenho;
    }

    public void setTendenciaDesempenho(String tendenciaDesempenho) {
        this.tendenciaDesempenho = tendenciaDesempenho;
    }

    public String getTendenciaCustosRH() {
        return tendenciaCustosRH;
    }

    public void setTendenciaCustosRH(String tendenciaCustosRH) {
        this.tendenciaCustosRH = tendenciaCustosRH;
    }
}