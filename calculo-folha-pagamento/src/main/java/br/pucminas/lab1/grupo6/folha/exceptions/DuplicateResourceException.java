package br.pucminas.lab1.grupo6.folha.exceptions;

public class DuplicateResourceException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Email já cadastrado";

  public DuplicateResourceException() {
    super(DEFAULT_MESSAGE);
  }

  public DuplicateResourceException(String msg) {
    super(msg);
  }

}
