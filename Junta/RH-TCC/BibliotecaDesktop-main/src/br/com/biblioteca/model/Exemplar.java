package br.com.biblioteca.model;

public class Exemplar {

  public static final String DISPONIVEL = "DISPONIVEL";
  public static final String EMPRESTADO = "EMPRESTADO";
  public static final String INATIVO = "INATIVO";

  private int id;
  private Livro livro;
  private String codigo;
  private String status;

  public Exemplar() {
    status = DISPONIVEL;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String toString() {
    return codigo + (livro == null ? "" : " - " + livro.getTitulo());
  }
}
