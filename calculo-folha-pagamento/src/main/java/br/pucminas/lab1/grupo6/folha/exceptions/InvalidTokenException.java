package br.pucminas.lab1.grupo6.folha.exceptions;

public class InvalidTokenException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Token inválido ou expirado.";

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException() {
        super(DEFAULT_MESSAGE);
    }
}
