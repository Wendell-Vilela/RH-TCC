package br.com.biblioteca.model;

public class Autor {

  private int id;
  private String nome;
  private String nacionalidade;

  public Autor() {}

  public Autor(String nome, String nacionalidade) {
    this.nome = nome;
    this.nacionalidade = nacionalidade;
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

  public String getNacionalidade() {
    return nacionalidade;
  }

  public void setNacionalidade(String nacionalidade) {
    this.nacionalidade = nacionalidade;
  }

  public String toString() {
    return nome;
  }
}
