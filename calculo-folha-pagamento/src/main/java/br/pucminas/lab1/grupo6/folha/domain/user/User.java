package br.pucminas.lab1.grupo6.folha.domain.user;

import java.util.UUID;

import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Users_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(unique = true)
    private String email;
    private String password;
    private Role role;

    public User() {}

    public User(UUID id, String email, String password, Role role) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.role = role;
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

    public UUID getId() {
      return id;
    }

    public void setId(UUID id) {
      this.id = id;
    }

}
