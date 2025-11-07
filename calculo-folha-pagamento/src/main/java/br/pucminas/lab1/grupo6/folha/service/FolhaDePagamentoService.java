package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;


import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.exceptions.UserNotFoundException;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;
import br.pucminas.lab1.grupo6.folha.security.AuthenticatedUser;

@Service
public class FolhaDePagamentoService {

    @Autowired
    private DescontoFactory descontoFactory; //Uso de Factory já? Bom.8

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private SalarioService salarioService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public FolhaDePagamento gerarFolhaDePagamento(FolhaRequest request, AuthenticatedUser authenticatedUser) {
        
        Funcionario funcionario = funcionarioRepository.findById(authenticatedUser.getUserEntity().getId()).orElseThrow(() -> new UserNotFoundException("Funcionário não encontrado"));

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
        double salarioLiquido = salarioService.calcularSalarioLiquido(horasTrabalhadasPorMes, inss, irrf, valeTransporte, valeAlimentacao);

        FolhaDePagamento folhaGerada = new FolhaDePagamento(
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

        eventPublisher.publishEvent(folhaGerada);

        return folhaGerada;
    }
}
