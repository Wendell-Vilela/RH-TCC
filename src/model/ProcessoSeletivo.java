package model;

public class ProcessoSeletivo {

    private int id;
    private String candidato;
    private String vaga;
    private String recrutador;
    private String etapaAtual;
    private String status;

    public ProcessoSeletivo() {
    }

    public ProcessoSeletivo(int id, String candidato, String vaga,
            String recrutador, String etapaAtual, String status) {

        this.id = id;
        this.candidato = candidato;
        this.vaga = vaga;
        this.recrutador = recrutador;
        this.etapaAtual = etapaAtual;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getRecrutador() {
        return recrutador;
    }

    public void setRecrutador(String recrutador) {
        this.recrutador = recrutador;
    }

    public String getEtapaAtual() {
        return etapaAtual;
    }

    public void setEtapaAtual(String etapaAtual) {
        this.etapaAtual = etapaAtual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}