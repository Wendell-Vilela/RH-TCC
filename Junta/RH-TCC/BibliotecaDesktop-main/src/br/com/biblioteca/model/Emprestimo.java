package br.com.biblioteca.model;

import java.util.Date;

public class Emprestimo {

  public static final String ABERTO = "ABERTO";
  public static final String DEVOLVIDO = "DEVOLVIDO";
  public static final String ATRASADO = "ATRASADO";

  private int id;
  private Cliente cliente;
  private Exemplar exemplar;
  private Date dataEmprestimo;
  private Date dataPrevistaDevolucao;
  private Date dataDevolucao;
  private String status;

  public Emprestimo() {
    status = ABERTO;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Exemplar getExemplar() {
    return exemplar;
  }

  public void setExemplar(Exemplar exemplar) {
    this.exemplar = exemplar;
  }

  public Date getDataEmprestimo() {
    return dataEmprestimo;
  }

  public void setDataEmprestimo(Date dataEmprestimo) {
    this.dataEmprestimo = dataEmprestimo;
  }

  public Date getDataPrevistaDevolucao() {
    return dataPrevistaDevolucao;
  }

  public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
    this.dataPrevistaDevolucao = dataPrevistaDevolucao;
  }

  public Date getDataDevolucao() {
    return dataDevolucao;
  }

  public void setDataDevolucao(Date dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
