package br.pucminas.lab1.grupo6.folha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;


import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.exceptions.ResourceNotFoundException;
import br.pucminas.lab1.grupo6.folha.repositories.FolhaDePagamentoRepository;
import br.pucminas.lab1.grupo6.folha.security.AuthenticatedUser;
import jakarta.transaction.Transactional;

@Service
public class FolhaDePagamentoService {

    @Autowired
    private DescontoFactory descontoFactory; //Uso de Factory já? Bom.8

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private SalarioService salarioService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private FolhaDePagamentoRepository folhaDePagamentoRepository;

    @Transactional
    public FolhaDePagamento gerarFolhaDePagamento(FolhaRequest request, AuthenticatedUser authenticatedUser) {
        
        Funcionario funcionario = funcionarioService.findById(authenticatedUser.getUserEntity().getId());

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
        double salarioBruto = funcionario.getSalarioBruto();
        double salarioLiquido = salarioService.calcularSalarioLiquido(salarioBruto, inss, irrf, valeTransporte, valeAlimentacao);
        
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

        folhaDePagamentoRepository.save(folhaGerada);
        funcionario.adicionarFolhaDePagamento(folhaGerada);
        eventPublisher.publishEvent(folhaGerada);

        return folhaGerada;
    }

    public List<FolhaDePagamento> getAllFolhasPorFuncionarioAutenticado(AuthenticatedUser authenticatedUser) {
        Funcionario funcionario = funcionarioService.findById(authenticatedUser.getUserEntity().getId());
        List<FolhaDePagamento> folhas = funcionario.getFolhasDePagamento();
        if (folhas == null || folhas.isEmpty()) {
                throw new ResourceNotFoundException("Nenhuma folha de pagamento encontrada. [id do funcionário: " + authenticatedUser.getUserEntity().getId() + "]");
        }
        return folhas;
    }
}
