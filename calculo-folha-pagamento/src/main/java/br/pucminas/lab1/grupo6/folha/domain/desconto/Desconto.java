package br.pucminas.lab1.grupo6.folha.domain.desconto;

import java.math.BigDecimal;

public abstract class Desconto implements ICalcular {

    protected Double valorDescontado;

    public Desconto() {}

    public Double getValorDescontado() {
        return valorDescontado;
    }

    public void setValorDescontado(Double valorDescontado) {
        this.valorDescontado = valorDescontado;
    }

}
