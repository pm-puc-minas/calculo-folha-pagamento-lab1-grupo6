package br.pucminas.lab1.grupo6.folha.domain.desconto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@SpringBootTest
public class FgtsTest {

    @Test
    void deveRetornarOitoPorCentoDoSalarioBruto(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(1000.0);

        Fgts fgts = new Fgts(funcionario, folhaRequest);
        double atual = fgts.calcular(funcionario, folhaRequest);

        double esperado = funcionario.getSalarioBruto() * 0.08;
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveRetornarZero_quandoSalarioBrutoForZero() {
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(0.0);

        Fgts fgts = new Fgts(funcionario, folhaRequest);
        double atual = fgts.calcular(funcionario, folhaRequest);

        assertEquals(0.0, atual, 0.01);
    }
}
