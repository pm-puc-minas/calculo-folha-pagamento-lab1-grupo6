package br.pucminas.lab1.grupo6.folha.domain.desconto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@SpringBootTest
public class IrrfTest {

    @Test
    void calculaFaixaIsenta_retornaZero() {
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBruto(2000.00);

        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setNumeroDeDependentes(0);
        folhaRequest.setValorPensaoAlimenticia(0.0);

        Irrf irrf = new Irrf(funcionario, folhaRequest);

        assertEquals(0.0, irrf.getValorDescontado(), 0.01);
    }

    @Test
    void calculaSegundaFaixa() {
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBruto(3000.00);

        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setNumeroDeDependentes(0);
        folhaRequest.setValorPensaoAlimenticia(0.0);
        
        double impostoEsperado = 62.45;

        Irrf irrf = new Irrf(funcionario, folhaRequest);

        assertEquals(impostoEsperado, irrf.getValorDescontado(), 0.01);
    }

    @Test
    void calculaTerceiraFaixa() {
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBruto(4000.00);

        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setNumeroDeDependentes(0);
        folhaRequest.setValorPensaoAlimenticia(0.0);

        double impostoEsperado = 187.27;

        Irrf irrf = new Irrf(funcionario, folhaRequest);

        assertEquals(impostoEsperado, irrf.getValorDescontado(), 0.01);
    }
    
    @Test
    void calculaComDependentes() {
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBruto(4000.00);

        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setNumeroDeDependentes(2);
        folhaRequest.setValorPensaoAlimenticia(0.0);

        double impostoEsperado = 130.39;

        Irrf irrf = new Irrf(funcionario, folhaRequest);

        assertEquals(impostoEsperado, irrf.getValorDescontado(), 0.01);
    }

    @Test
    void calculaComPensaoAlimenticia() {
        Funcionario funcionario = new Funcionario();
        funcionario.setSalarioBruto(5000.00);

        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setNumeroDeDependentes(0);
        folhaRequest.setValorPensaoAlimenticia(500.0);

        double impostoEsperado = 257.9766875;

        Irrf irrf = new Irrf(funcionario, folhaRequest);

        assertEquals(impostoEsperado, irrf.getValorDescontado(), 0.01);
    }
}