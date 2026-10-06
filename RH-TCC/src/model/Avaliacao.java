package model;

import java.util.ArrayList;
import java.util.List;

public class Avaliacao {

    private String area;
    private String funcionario;

    private double autoavaliacao;
    private double avaliacaoGestor;
    private double avaliacaoPares;

    private List<Feedback> feedbacks;


    public Avaliacao() {
        feedbacks = new ArrayList<>();
    }


    public Avaliacao(String area, String funcionario, double autoavaliacao, double avaliacaoGestor, double avaliacaoPares) {

        this.area = area;
        this.funcionario = funcionario;
        this.autoavaliacao = autoavaliacao;
        this.avaliacaoGestor = avaliacaoGestor;
        this.avaliacaoPares = avaliacaoPares;

        this.feedbacks = new ArrayList<>();
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }


    public double getAutoavaliacao() {
        return autoavaliacao;
    }

    public void setAutoavaliacao(double autoavaliacao) {
        this.autoavaliacao = autoavaliacao;
    }


    public double getAvaliacaoGestor() {
        return avaliacaoGestor;
    }

    public void setAvaliacaoGestor(double avaliacaoGestor) {
        this.avaliacaoGestor = avaliacaoGestor;
    }


    public double getAvaliacaoPares() {
        return avaliacaoPares;
    }

    public void setAvaliacaoPares(double avaliacaoPares) {
        this.avaliacaoPares = avaliacaoPares;
    }


    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }


    public void adicionarFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
}
