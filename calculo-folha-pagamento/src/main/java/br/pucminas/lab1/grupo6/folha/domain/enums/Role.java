package br.pucminas.lab1.grupo6.folha.domain.enums;

public enum Role {
  ADMIN(1),
  USER(2);

  private final int role;

  Role(int role) {
    this.role = role;
  }

  public int getRole() {
    return this.role;
  }
}
