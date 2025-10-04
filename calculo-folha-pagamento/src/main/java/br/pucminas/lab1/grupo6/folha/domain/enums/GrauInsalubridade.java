package br.pucminas.lab1.grupo6.folha.domain.enums;

public enum GrauInsalubridade {
  NENHUM(0.0),
  BAIXO(0.10),
  MEDIO(0.20),
  ALTO(0.40);

  private final double percentual;

  GrauInsalubridade(double percentual) {
    this.percentual = percentual;
  }

  public double getPercentual() {
    return this.percentual;
  }
}
