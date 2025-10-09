package br.pucminas.lab1.grupo6.folha.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class UserTest {

  private User user;
  private Funcionario funcionario;

  @BeforeEach
  void setUp() {
    funcionario = new Funcionario();
    user = new User("teste@empresa.com", "12345", Role.ADMIN, funcionario);
  }

  @Test
  void deveCriarUsuarioComDadosCorretos() {
    assertEquals("teste@empresa.com", user.getEmail());
    assertEquals("12345", user.getPassword());
    assertEquals(Role.ADMIN, user.getRole());
    assertEquals(funcionario, user.getFuncionario());
  }

  @Test
  void devePermitirAlterarEmail() {
    user.setEmail("novo@empresa.com");
    assertEquals("novo@empresa.com", user.getEmail());
  }

  @Test
  void devePermitirAlterarSenha() {
    user.setPassword("novaSenha");
    assertEquals("novaSenha", user.getPassword());
  }

  @Test
  void devePermitirAlterarRole() {
    user.setRole(Role.USER);
    assertEquals(Role.USER, user.getRole());
  }

  @Test
  void deveValidarLoginCorreto() {
    assertTrue(user.validarLogin("teste@empresa.com", "12345"));
  }

  @Test
  void naoDeveValidarLoginComEmailErrado() {
    assertFalse(user.validarLogin("invalido@empresa.com", "12345"));
  }

  @Test
  void naoDeveValidarLoginComSenhaErrada() {
    assertFalse(user.validarLogin("teste@empresa.com", "senhaErrada"));
  }

  @Test
  void naoDeveValidarLoginComEmailESenhaErrados() {
    assertFalse(user.validarLogin("invalido@empresa.com", "senhaErrada"));
  }
}
