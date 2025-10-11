package br.pucminas.lab1.grupo6.folha.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.YearMonth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.desconto.Fgts;
import br.pucminas.lab1.grupo6.folha.domain.desconto.Inss;
import br.pucminas.lab1.grupo6.folha.domain.desconto.Irrf;
import br.pucminas.lab1.grupo6.folha.domain.desconto.ValeAlimentacao;
import br.pucminas.lab1.grupo6.folha.domain.desconto.ValeTransporte;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@ExtendWith(MockitoExtension.class)
class FolhaDePagamentoServiceTest {

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

    private FolhaDePagamentoService service;

    @BeforeEach
    void setUp() {
        service = new FolhaDePagamentoService();
    }

    @Test
    void deveGerarFolhaDePagamentoComDadosCalculados() {
        var funcionario = new Funcionario();
        funcionario.setSalarioBruto(5000.0);

        var request = new FolhaRequest();
        request.setMes(YearMonth.of(2024, 6));
        request.setDiasTrabalhados(22);
        request.setCargaDiaria(8.0);

        when(descontoFactory.criarInss(funcionario, request)).thenReturn(inss);
        when(descontoFactory.criarValeTransporte(funcionario, request)).thenReturn(valeTransporte);
        when(descontoFactory.criarValeAlimentacao(funcionario, request)).thenReturn(valeAlimentacao);
        when(descontoFactory.criarFgts(funcionario, request)).thenReturn(fgts);
        when(descontoFactory.criarIrrf(funcionario, request)).thenReturn(irrf);

        when(inss.getValorDescontado()).thenReturn(500.0);
        when(valeTransporte.getValorDescontado()).thenReturn(200.0);
        when(valeAlimentacao.getValorDescontado()).thenReturn(300.0);
        when(fgts.getValorDescontado()).thenReturn(400.0);
        when(irrf.getValorDescontado()).thenReturn(250.0);

        var folha = service.gerarFolhaDePagamento(funcionario, request);

        assertNotNull(folha);
        assertEquals(funcionario, folha.getFuncionario());
        assertEquals(YearMonth.of(2024, 6), folha.getMes());
        assertEquals(500.0, folha.getValorDeDescontoINSS());
        assertEquals(200.0, folha.getValorDeDescontoVT());
        assertEquals(300.0, folha.getValorDeDescontoVA());
        assertEquals(400.0, folha.getValorDeDescontoFGTS());
        assertEquals(250.0, folha.getValorDeDescontoIRRF());
        assertEquals(4350.0, folha.getSalarioLiquido());
        assertEquals(22, folha.getDiasTrabalhados());
        assertEquals(176, folha.getHorasTrabalhadas());
    }

    @Test
    void deveGerarFolhaDePagamentoQuandoNaoHaDescontos() {
        var funcionario = new Funcionario();
        funcionario.setSalarioBruto(2000.0);

        var request = new FolhaRequest();
        request.setMes(YearMonth.of(2024, 1));
        request.setDiasTrabalhados(20);
        request.setCargaDiaria(8.0);

        when(descontoFactory.criarInss(funcionario, request)).thenReturn(inss);
        when(descontoFactory.criarValeTransporte(funcionario, request)).thenReturn(valeTransporte);
        when(descontoFactory.criarValeAlimentacao(funcionario, request)).thenReturn(valeAlimentacao);
        when(descontoFactory.criarFgts(funcionario, request)).thenReturn(fgts);
        when(descontoFactory.criarIrrf(funcionario, request)).thenReturn(irrf);

        when(inss.getValorDescontado()).thenReturn(0.0);
        when(valeTransporte.getValorDescontado()).thenReturn(0.0);
        when(valeAlimentacao.getValorDescontado()).thenReturn(0.0);
        when(fgts.getValorDescontado()).thenReturn(0.0);
        when(irrf.getValorDescontado()).thenReturn(0.0);

        var folha = service.gerarFolhaDePagamento(funcionario, request);

        assertNotNull(folha);
        assertEquals(2000.0, folha.getSalarioLiquido());
        assertEquals(160, folha.getHorasTrabalhadas());
        assertEquals(20, folha.getDiasTrabalhados());
    }
}
