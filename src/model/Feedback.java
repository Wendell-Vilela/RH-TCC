package model;

import java.time.LocalDate;

public class Feedback {

    private String competencia;
    private String avaliador;
    private LocalDate data;
    private String feedback;


    public Feedback() {
    }


    public Feedback(
            String competencia,
            String avaliador,
            LocalDate data,
            String feedback) {

        this.competencia = competencia;
        this.avaliador = avaliador;
        this.data = data;
        this.feedback = feedback;
    }


    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }


    public String getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(String avaliador) {
        this.avaliador = avaliador;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
