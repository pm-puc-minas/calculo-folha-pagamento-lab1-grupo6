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
    private DescontoFactory descontoFactory; //Uso de Factory já? Bom.

    public FolhaDePagamento gerarFolhaDePagamento(Funcionario funcionario, FolhaRequest request) {
        double valeTransporte = (request.getValeTransporteRecebido() != null && request.getValeTransporteRecebido() > 0)
                ? descontoFactory.criarValeTransporte(funcionario, request).getValorDescontado()
                : 0.0;

        double valeAlimentacao = (request.getValorValeAlimentacaoDiario() != null && request.getValorValeAlimentacaoDiario() > 0)
                ? descontoFactory.criarValeAlimentacao(funcionario, request).getValorDescontado()
                : 0.0;

        double inss = descontoFactory.criarInss(funcionario, request).getValorDescontado();
        double fgts = descontoFactory.criarFgts(funcionario, request).getValorDescontado();
        double irrf = descontoFactory.criarIrrf(funcionario, request).getValorDescontado();

        int horasTrabalhadasPorMes = (int) request.getCargaDiaria() * request.getDiasTrabalhados();
        double salarioLiquido = funcionario.getSalarioBruto()
                - inss
                - irrf
                - valeTransporte
                + valeAlimentacao;

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
                horasTrabalhadasPorMes
        );
    }
}
