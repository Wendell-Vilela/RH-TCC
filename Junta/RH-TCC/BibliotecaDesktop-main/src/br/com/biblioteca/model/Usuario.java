package br.com.biblioteca.model;

/**
 * Representa a pessoa autorizada a entrar no sistema.
 * Os atributos sao private para proteger o estado do objeto. O acesso ocorre
 * pelos getters e setters, um exemplo pratico de encapsulamento.
 */
public class Usuario {

  private int id;
  private String nome;
  private String login;
  private String senhaHash;
  private String senhaSalt;
  private String perfil;
  private boolean ativo;

  public Usuario() {
    this.ativo = true;
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

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenhaHash() {
    return senhaHash;
  }

  public void setSenhaHash(String senhaHash) {
    this.senhaHash = senhaHash;
  }

  public String getSenhaSalt() {
    return senhaSalt;
  }

  public void setSenhaSalt(String senhaSalt) {
    this.senhaSalt = senhaSalt;
  }

  public String getPerfil() {
    return perfil;
  }

  public void setPerfil(String perfil) {
    this.perfil = perfil;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public String toString() {
    return nome;
  }
}
