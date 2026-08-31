package br.com.biblioteca.model;

public class Editora {

  private int id;
  private String nome;
  private String cidade;

  public Editora() {}

  public Editora(String nome, String cidade) {
    this.nome = nome;
    this.cidade = cidade;
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

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String toString() {
    return nome;
  }
}
