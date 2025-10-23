package br.pucminas.lab1.grupo6.folha.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;

@SpringBootTest
@ActiveProfiles("test")
public class SalarioServiceTest {
    @Autowired
    private SalarioService salarioService;

    @Test
    void deveCalcularSalarioPorHoraPorJornada() {
        var funcionario = new Funcionario();
        funcionario.setSalarioBruto(3000.0);
        var request = new FolhaRequest();
        request.setJornadaMensal(160.0);
        request.setCargaDiaria(8);
        request.setDiasTrabalhados(20);

        double hora = salarioService.calcularSalarioPorHora(funcionario, request);
        assertEquals(18.75, hora, 0.001);
    }

}
