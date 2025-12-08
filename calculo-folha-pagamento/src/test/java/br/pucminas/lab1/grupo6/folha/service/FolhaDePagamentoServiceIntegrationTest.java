package br.pucminas.lab1.grupo6.folha.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.YearMonth;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.desconto.Fgts;
import br.pucminas.lab1.grupo6.folha.domain.desconto.Inss;
import br.pucminas.lab1.grupo6.folha.domain.desconto.Irrf;
import br.pucminas.lab1.grupo6.folha.domain.desconto.ValeAlimentacao;
import br.pucminas.lab1.grupo6.folha.domain.desconto.ValeTransporte;
import br.pucminas.lab1.grupo6.folha.domain.enums.Cargo;
import br.pucminas.lab1.grupo6.folha.domain.enums.GrauInsalubridade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Periculosidade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.repositories.FolhaDePagamentoRepository;

@ExtendWith(MockitoExtension.class)
class FolhaDePagamentoServiceIntegrationTest {

    @Mock
    private DescontoFactory descontoFactory;

    @Mock
    private Inss inss;

    @Mock
    private ValeTransporte valeTransporte;

    @Mock
    private ValeAlimentacao valeAlimentacao;

    @Mock
    private Fgts fgts;

    @Mock
    private Irrf irrf;

    @Mock
    private FuncionarioService funcionarioService;

    @Mock
    private SalarioService salarioService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private FolhaDePagamentoRepository folhaDePagamentoRepository;

    @InjectMocks
    private FolhaDePagamentoService folhaService;

    @Test
    void devePublicarEvento_quandoFolhaDePagamentoForGerada() {
        Funcionario funcionario = new Funcionario(
            UUID.randomUUID(),
            "Nome",
            "12345678900",
            Cargo.ANALISTA,
            2000.0,
            Periculosidade.NAO,
            GrauInsalubridade.NENHUM,
            "email@test.com",
            "pwd",
            Role.USER);
        UUID funcionarioId = UUID.randomUUID();

        when(funcionarioService.findById(funcionarioId)).thenReturn(funcionario);
        when(descontoFactory.criarInss(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(inss);
        when(descontoFactory.criarFgts(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(fgts);
        when(descontoFactory.criarIrrf(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(irrf);
        when(descontoFactory.criarValeTransporte(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(valeTransporte);
        when(descontoFactory.criarValeAlimentacao(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(valeAlimentacao);

        when(inss.getValorDescontado()).thenReturn(0.0);
        when(fgts.getValorDescontado()).thenReturn(0.0);
        when(irrf.getValorDescontado()).thenReturn(0.0);
        when(valeTransporte.getValorDescontado()).thenReturn(0.0);
        when(valeAlimentacao.getValorDescontado()).thenReturn(0.0);

        when(salarioService.calcularSalarioLiquido(any(Double.class), any(Double.class), any(Double.class), any(Double.class), any(Double.class)))
                .thenReturn(2000.0);

        FolhaRequest request = new FolhaRequest();
        request.setMes(YearMonth.of(2024, 10));
        request.setDiasTrabalhados(20);
        request.setCargaDiaria(8);
        request.setValeTransporteRecebido(1.0);
        request.setValorValeAlimentacaoDiario(1.0);

        FolhaDePagamento folhaGerada = folhaService.gerarFolhaDePagamento(request, funcionarioId);

        assertNotNull(folhaGerada);
        verify(folhaDePagamentoRepository, times(1)).save(folhaGerada);
        verify(eventPublisher, times(1)).publishEvent(folhaGerada);
    }
}