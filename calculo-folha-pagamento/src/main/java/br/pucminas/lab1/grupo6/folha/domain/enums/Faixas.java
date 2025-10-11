package br.pucminas.lab1.grupo6.folha.domain.enums;

public enum Faixas {

    PRIMEIRA_FAIXA(1302.00),
    SEGUNDA_FAIXA( 2571.29),
    TERCEIRA_FAIXA(3856.94),
    QUARTA_FAIXA(7507.49);

    private final double faixa;

    Faixas(double valor) {
        this.faixa = valor;
    }

    public double getFaixa() {
        return faixa;
    }

}
