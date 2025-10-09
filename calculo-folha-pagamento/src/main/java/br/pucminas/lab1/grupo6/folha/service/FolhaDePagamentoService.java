package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@Service
public class FolhaDePagamentoService {

    @Autowired
    private DescontoFactory descontoFactory;

    public FolhaDePagamento gerarFolhaDePagamento(Funcionario funcionario, FolhaRequest request) {
        FolhaDePagamento folha = new FolhaDePagamento(
            funcionario,
            request.getMes(),
            descontoFactory.criarInss(funcionario, request).getValorDescontado(),
            descontoFactory.criarValeTransporte(funcionario, request).getValorDescontado(),
            descontoFactory.criarValeAlimentacao(funcionario, request).getValorDescontado(),
            descontoFactory.criarFgts(funcionario, request).getValorDescontado(),
            descontoFactory.criarIrrf(funcionario, request).getValorDescontado(),
            0.0, //alterar conforme correto
            request.getDiasTrabalhados(), //alterar conforme correto
            (int) Math.round(request.getCargaDiaria() * request.getDiasTrabalhados()) //alterar conforme correto
        );

        return folha;
    }
}
