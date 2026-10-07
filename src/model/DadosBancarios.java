package model;

public class DadosBancarios {

    private String banco;
    private String codigoBanco;
    private String agencia;
    private String conta;
    private String tipoConta;
    private String pix;

    public DadosBancarios() {
    }

    public DadosBancarios(String banco, String codigoBanco, String agencia,
                          String conta, String tipoConta, String pix) {

        this.banco = banco;
        this.codigoBanco = codigoBanco;
        this.agencia = agencia;
        this.conta = conta;
        this.tipoConta = tipoConta;
        this.pix = pix;
    }
    
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }
}