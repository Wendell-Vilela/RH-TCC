package model;

public class Dependentes {

    private String nome;
    private String parentesco;
    private String nascimento;
    private String assistencia;

    public Dependentes() {
    }

    public Dependentes(String nome, String parentesco,
                      String nascimento, String assistencia) {

        this.nome = nome;
        this.parentesco = parentesco;
        this.nascimento = nascimento;
        this.assistencia = assistencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getAssistencia() {
        return assistencia;
    }

    public void setAssistencia(String assistencia) {
        this.assistencia = assistencia;
    }
}