package br.pucminas.lab1.grupo6.folha.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Recurso não encontrado.";

  public ResourceNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
