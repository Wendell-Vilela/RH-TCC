package br.com.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

/** Livro e a obra; suas unidades fisicas sao objetos da classe Exemplar. */
public class Livro {

  private int id;
  private String titulo;
  private String isbn;
  private int anoPublicacao;
  private Editora editora;
  private Categoria categoria;
  private List<Autor> autores;

  public Livro() {
    autores = new ArrayList<Autor>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getAnoPublicacao() {
    return anoPublicacao;
  }

  public void setAnoPublicacao(int anoPublicacao) {
    this.anoPublicacao = anoPublicacao;
  }

  public Editora getEditora() {
    return editora;
  }

  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public List<Autor> getAutores() {
    return autores;
  }

  public void setAutores(List<Autor> autores) {
    this.autores = autores;
  }

  public String toString() {
    return titulo;
  }
}
