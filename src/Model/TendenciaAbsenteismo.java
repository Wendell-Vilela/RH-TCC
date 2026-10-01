package model;

import java.util.ArrayList;
import java.util.List;

public class TendenciaAbsenteismo {

    private List<Double> scores;

    public TendenciaAbsenteismo() {
        this.scores = new ArrayList<>();
    }

    public TendenciaAbsenteismo(List<Double> scores) {
        this.scores = scores;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    public void adicionarScore(double score) {
        this.scores.add(score);
    }
}