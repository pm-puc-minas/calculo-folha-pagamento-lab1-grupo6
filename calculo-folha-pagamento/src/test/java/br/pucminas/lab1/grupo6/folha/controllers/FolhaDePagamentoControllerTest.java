package br.pucminas.lab1.grupo6.folha.controllers;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.security.AuthenticatedUser;
import br.pucminas.lab1.grupo6.folha.service.FolhaDePagamentoService;

@ExtendWith(MockitoExtension.class)
class FolhaDePagamentoControllerTest {

    @Mock
    private FolhaDePagamentoService service;

    @InjectMocks
    private FolhaDePagamentoController controller;

    @Test
    void deveGerarFolhaERetornarOk() {
        AuthenticatedUser user = new AuthenticatedUser(null);
        var request = new FolhaRequest();
        request.setMes(YearMonth.of(2025, 1));
        request.setDiasTrabalhados(20);
        request.setCargaDiaria(8);

        var funcionario = new Funcionario();
        var folha = new FolhaDePagamento(
                funcionario,
                request.getMes(),
                100.0,
                50.0,
                80.0,
                120.0,
                60.0,
                2000.0,
                request.getDiasTrabalhados(),
                (int) (request.getDiasTrabalhados() * request.getCargaDiaria())
        );

        when(service.gerarFolhaDePagamento(request, user)).thenReturn(folha);

        ResponseEntity<FolhaDePagamento> response = controller.gerarFolha(user, request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(folha, response.getBody());
    }
}
