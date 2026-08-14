package br.com.biblioteca.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Validador {

  private static final String FORMATO_DATA = "dd/MM/yyyy";

  private Validador() {}

  public static boolean vazio(String valor) {
    return valor == null || valor.trim().length() == 0;
  }

  public static boolean emailValido(String email) {
    if (vazio(email)) return true; // email e opcional em alguns cadastros
    int arroba = email.indexOf('@');
    int ponto = email.lastIndexOf('.');
    return arroba > 0 && ponto > arroba + 1 && ponto < email.length() - 1;
  }

  public static Date converterData(String texto) throws ParseException {
    SimpleDateFormat formato = new SimpleDateFormat(FORMATO_DATA);
    formato.setLenient(false);
    return formato.parse(texto.trim());
  }

  public static String formatarData(Date data) {
    if (data == null) return "";
    return new SimpleDateFormat(FORMATO_DATA).format(data);
  }
}
