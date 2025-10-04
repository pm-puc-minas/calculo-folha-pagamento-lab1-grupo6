package br.pucminas.lab1.grupo6.folha.domain.enums;

public enum Periculosidade {
  SIM(0.30),
  NAO(0.0);

  private final double percentual;

  Periculosidade(double percentual) {
    this.percentual = percentual;
  }

  public double getPercentual() {
    return percentual;
  }
}
