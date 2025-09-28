package br.pucminas.lab1.grupo6.folha.domain.desconto;

import java.math.BigDecimal;

public abstract class Desconto implements ICalcular {

    protected BigDecimal valorDescontado;

    public Desconto() {}

    public BigDecimal getValorDescontado() {
        return valorDescontado;
    }

    public void setValorDescontado(BigDecimal valorDescontado) {
        this.valorDescontado = valorDescontado;
    }

}
