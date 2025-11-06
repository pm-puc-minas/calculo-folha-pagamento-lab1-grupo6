package br.pucminas.lab1.grupo6.folha.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.service.FuncionarioService;

@ExtendWith(MockitoExtension.class)
class FuncionarioControllerTest {

    @Mock
    private FuncionarioService service;

    @InjectMocks
    private FuncionarioController controller;

    @Test
    void deveCriarFuncionarioERetornarOk() {
        var funcionario = new Funcionario();

        when(service.Insert(funcionario)).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = controller.Create(funcionario);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(funcionario, response.getBody());
    }
}
