package br.pucminas.lab1.grupo6.folha.domain.desconto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@SpringBootTest
public class InssTest {

    @Test
    void deveCalcularINSS_quandoSalarioMenorQuePrimeiraFaixa(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(1000.00);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double esperado = 1000.00 * 0.075; 
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveCalcularINSS_quandoSalarioNoLimiteDaPrimeiraFaixa(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(1302.00);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double esperado = 1302.00 * 0.075;
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveCalcularINSS_quandoSalarioEntrePrimeiraESegundaFaixa(){
        // Entre 1302.00 e 2571.29
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(2000.00);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double faixa1 = 1302.00 * 0.075; 
        double faixa2Parcial = (funcionario.getSalarioBruto() - 1302.00) * 0.09; 
        double esperado = faixa1 + faixa2Parcial; 
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveCalcularINSS_quandoSalarioNoLimiteDaSegundaFaixa(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(2571.29);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double faixa1 = 1302.00 * 0.075;
        double faixa2 = (2571.29 - 1302.00) * 0.09; 
        double esperado = faixa1 + faixa2;
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveCalcularINSS_quandoSalarioNoLimiteDaTerceiraFaixa(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(3856.94);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double faixa1 = 1302.00 * 0.075;
        double faixa2 = (2571.29 - 1302.00) * 0.09; 
        double faixa3 = (3856.94 - 2571.29) * 0.12; 
        double esperado = faixa1 + faixa2 + faixa3; 
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveCalcularINSS_quandoSalarioNoTeto(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(7507.49);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double esperado = 877.24;
        assertEquals(esperado, atual, 0.01);
    }

    @Test
    void deveCalcularINSS_quandoSalarioAcimaDoTeto(){
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(8000.00);

        Inss inss = new Inss(funcionario, folhaRequest);
        double atual = inss.calcular(funcionario, folhaRequest);

        double esperado = 877.24;
        assertEquals(esperado, atual, 0.01);
    }
}
