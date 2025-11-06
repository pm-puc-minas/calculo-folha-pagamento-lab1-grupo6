package br.pucminas.lab1.grupo6.folha.exceptions;

public class InvalidCredentialsException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Credenciais inválidas. Verifique seu email e senha.";

  public InvalidCredentialsException() {
    super(DEFAULT_MESSAGE);
  }

  public InvalidCredentialsException(String msg) {
    super(msg);
  }
}
