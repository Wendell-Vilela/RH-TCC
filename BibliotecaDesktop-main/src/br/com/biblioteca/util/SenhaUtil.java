package br.com.biblioteca.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/** Gera salt individual e hash SHA-256 sem armazenar senha em texto puro. */
public final class SenhaUtil {

  private SenhaUtil() {}

  public static String gerarSalt() {
    byte[] bytes = new byte[16];
    new SecureRandom().nextBytes(bytes);
    return paraHexadecimal(bytes);
  }

  public static String gerarHash(String senha, String salt) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest((salt + senha).getBytes("UTF-8"));
      return paraHexadecimal(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("SHA-256 indisponivel.", e);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("UTF-8 indisponivel.", e);
    }
  }

  public static boolean conferir(
    String senha,
    String salt,
    String hashEsperado
  ) {
    if (senha == null || salt == null || hashEsperado == null) return false;
    return gerarHash(senha, salt).equalsIgnoreCase(hashEsperado);
  }

  private static String paraHexadecimal(byte[] bytes) {
    StringBuilder texto = new StringBuilder();
    int i;
    for (i = 0; i < bytes.length; i++) {
      String parte = Integer.toHexString(bytes[i] & 0xff);
      if (parte.length() == 1) texto.append('0');
      texto.append(parte);
    }
    return texto.toString();
  }
}
