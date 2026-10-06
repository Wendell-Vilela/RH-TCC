package model;

public class Documentos {

    private String cpf;
    private String rg;
    private String passaporte;
    private String validadePassaporte;
    private String pis;
    private String ctps;

    public Documentos() {
    }

    public Documentos(String cpf, String rg, String passaporte,
                      String validadePassaporte, String pis, String ctps) {

        this.cpf = cpf;
        this.rg = rg;
        this.passaporte = passaporte;
        this.validadePassaporte = validadePassaporte;
        this.pis = pis;
        this.ctps = ctps;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getValidadePassaporte() {
        return validadePassaporte;
    }

    public void setValidadePassaporte(String validadePassaporte) {
        this.validadePassaporte = validadePassaporte;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }
}