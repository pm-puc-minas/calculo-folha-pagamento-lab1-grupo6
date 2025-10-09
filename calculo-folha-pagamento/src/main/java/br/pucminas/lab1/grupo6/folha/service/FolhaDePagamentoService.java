package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@Service
public class FolhaDePagamentoService {

    private final DescontoFactory descontoFactory;

    public FolhaDePagamentoService(DescontoFactory descontoFactory) {
        this.descontoFactory = descontoFactory;
    }


    public FolhaDePagamento gerarFolhaDePagamento(Funcionario funcionario, FolhaRequest request) {
        var inss = descontoFactory.criarInss(funcionario, request).getValorDescontado();
        var valeTransporte = descontoFactory.criarValeTransporte(funcionario, request).getValorDescontado();
        var valeAlimentacao = descontoFactory.criarValeAlimentacao(funcionario, request).getValorDescontado();
        var fgts = descontoFactory.criarFgts(funcionario, request).getValorDescontado();
        var irrf = descontoFactory.criarIrrf(funcionario, request).getValorDescontado();

        var horasTrabalhadasPorMes = Math.round(request.getCargaDiaria() * request.getDiasTrabalhados());
        var salarioLiquido = funcionario.getSalarioBruto() - inss - irrf - valeTransporte + valeAlimentacao;

        return new FolhaDePagamento(
                funcionario,
                request.getMes(),
                inss,
                valeTransporte,
                valeAlimentacao,
                fgts,
                irrf,
                salarioLiquido,
                request.getDiasTrabalhados(),
                (int) horasTrabalhadasPorMes);
    }
}
