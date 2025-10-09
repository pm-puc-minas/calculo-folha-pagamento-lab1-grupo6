package br.pucminas.lab1.grupo6.folha.domain.user;

import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class User {
  private String email;
  private String password;
  private Funcionario funcionario;
  private Role role;

  public User(String email, String password, Role role, Funcionario funcionario) {
    this.email = email;
    this.password = password;
    this.role = role;
    this.funcionario = funcionario;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public Role getRole() {
    return this.role;
  }

  public Funcionario getFuncionario() {
    return this.funcionario;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public boolean validarLogin(String email, String password) {
    return this.email.equals(email) && this.password.equals(password);
  }

}
