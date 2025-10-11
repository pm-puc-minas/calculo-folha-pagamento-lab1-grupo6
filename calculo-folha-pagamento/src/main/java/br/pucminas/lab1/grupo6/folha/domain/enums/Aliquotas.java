package br.pucminas.lab1.grupo6.folha.domain.enums;

public enum Aliquotas {
    PRIMEIRA_ALIQUOTA(0.075),
    SEGUNDA_ALIQUOTA(0.09),
    TERCEIRA_ALIQUOTA(0.12),
    QUARTA_ALIQUOTA(0.14);

    private final double aliquota;

    Aliquotas(double valor) {
        this.aliquota = valor;
    }
    
    public double getAliquota() {
        return aliquota;
    }

    



}
