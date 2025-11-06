package br.pucminas.lab1.grupo6.folha.exceptions;

public class UserNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Usuário não encontrado.";

  public UserNotFoundException(String msg) {
    super(msg);
  }

  public UserNotFoundException() {
    super(DEFAULT_MESSAGE);
  }
}
