package br.pucminas.lab1.grupo6.folha.domain.desconto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@SpringBootTest
public class ValeAlimentacaoTest {
    
    @Test
    void calculaValorCorretoDoValeAlimentacao() {
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setDiasTrabalhados(22);
        folhaRequest.setValorValeAlimentacaoDiario(25.0);

        ValeAlimentacao valeAlimentacao = new ValeAlimentacao(funcionario, folhaRequest);

        assertEquals(550.0, valeAlimentacao.getValorDescontado(), 0.01);
    }

    @Test
    void retornaZero_quandoDiasTrabalhadosZero() {
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setDiasTrabalhados(0);
        folhaRequest.setValorValeAlimentacaoDiario(25.0);

        ValeAlimentacao valeAlimentacao = new ValeAlimentacao(funcionario, folhaRequest);

        assertEquals(0.0, valeAlimentacao.getValorDescontado(), 0.01);
    }

    @Test
    void retornaZero_quandoValorDiarioZero() {
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        folhaRequest.setDiasTrabalhados(22);
        folhaRequest.setValorValeAlimentacaoDiario(0.0);

        ValeAlimentacao valeAlimentacao = new ValeAlimentacao(funcionario, folhaRequest);

        assertEquals(0.0, valeAlimentacao.getValorDescontado(), 0.01);
    }
}