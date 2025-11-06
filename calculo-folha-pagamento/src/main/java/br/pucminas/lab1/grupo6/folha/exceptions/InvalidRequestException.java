package br.pucminas.lab1.grupo6.folha.exceptions;

public class InvalidRequestException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Dados da operação ausentes ou inválidos.";

    public InvalidRequestException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidRequestException(String msg) {
        super(msg);
    }
}
