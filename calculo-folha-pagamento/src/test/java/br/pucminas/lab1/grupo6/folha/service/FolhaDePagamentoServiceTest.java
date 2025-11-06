// package br.pucminas.lab1.grupo6.folha.service;

// import java.time.YearMonth;
// import java.util.Optional;
// import java.util.UUID;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import static org.mockito.Mockito.when;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.context.ApplicationEventPublisher;

// import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
// import br.pucminas.lab1.grupo6.folha.domain.desconto.Fgts;
// import br.pucminas.lab1.grupo6.folha.domain.desconto.Inss;
// import br.pucminas.lab1.grupo6.folha.domain.desconto.Irrf;
// import br.pucminas.lab1.grupo6.folha.domain.desconto.ValeAlimentacao;
// import br.pucminas.lab1.grupo6.folha.domain.desconto.ValeTransporte;
// import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
// import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
// import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

// @ExtendWith(MockitoExtension.class)
// @SuppressWarnings("null")
// class FolhaDePagamentoServiceTest {

//     @Mock
//     private DescontoFactory descontoFactory;
//     @Mock
//     private Inss inss;
//     @Mock
//     private ValeTransporte valeTransporte;
//     @Mock
//     private ValeAlimentacao valeAlimentacao;
//     @Mock
//     private Fgts fgts;
//     @Mock
//     private Irrf irrf;

//     @Mock
//     private FuncionarioRepository funcionarioRepository;

//     @Mock
//     private SalarioService salarioService;

//     @Mock
//     private ApplicationEventPublisher eventPublisher;

//     @InjectMocks
//     private FolhaDePagamentoService service;

//     @Test
//     void deveGerarFolhaDePagamentoComDadosCalculados() {
//         var funcionario = new Funcionario();
//         funcionario.setSalarioBruto(5000.0);

//         var request = new FolhaRequest();
//         request.setMes(YearMonth.of(2024, 6));
//         request.setDiasTrabalhados(22);
//         request.setCargaDiaria(8);
//         UUID id = UUID.randomUUID();
//         request.setIdFuncionario(id);
//         request.setValeTransporteRecebido(1.0);
//         request.setValorValeAlimentacaoDiario(1.0);

//         when(funcionarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(funcionario));

//         when(descontoFactory.criarInss(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(inss);
//         when(descontoFactory.criarValeTransporte(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(valeTransporte);
//         when(descontoFactory.criarValeAlimentacao(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(valeAlimentacao);
//         when(descontoFactory.criarFgts(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(fgts);
//         when(descontoFactory.criarIrrf(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(irrf);

//         when(inss.getValorDescontado()).thenReturn(500.0);
//         when(valeTransporte.getValorDescontado()).thenReturn(200.0);
//         when(valeAlimentacao.getValorDescontado()).thenReturn(300.0);
//         when(fgts.getValorDescontado()).thenReturn(400.0);
//         when(irrf.getValorDescontado()).thenReturn(250.0);

//         when(salarioService.calcularSalarioLiquido(eq(176.0), eq(500.0), eq(250.0), eq(200.0), eq(300.0)))
//                 .thenReturn(4350.0);

//         var folha = service.gerarFolhaDePagamento(request);

//         assertNotNull(folha);
//         assertEquals(funcionario, folha.getFuncionario());
//         assertEquals(YearMonth.of(2024, 6), folha.getMes());
//         assertEquals(500.0, folha.getValorDeDescontoINSS());
//         assertEquals(200.0, folha.getValorDeDescontoVT());
//         assertEquals(300.0, folha.getValorDeDescontoVA());
//         assertEquals(400.0, folha.getValorDeDescontoFGTS());
//         assertEquals(250.0, folha.getValorDeDescontoIRRF());
//         assertEquals(4350.0, folha.getSalarioLiquido());
//         assertEquals(22, folha.getDiasTrabalhados());
//         assertEquals(176, folha.getHorasTrabalhadas());
//     }

//     @Test
//     void deveGerarFolhaDePagamentoQuandoNaoHaDescontos() {
//         var funcionario = new Funcionario();
//         funcionario.setSalarioBruto(2000.0);

//         var request = new FolhaRequest();
//         request.setMes(YearMonth.of(2024, 1));
//         request.setDiasTrabalhados(20);
//         request.setCargaDiaria(8);
//         UUID id = UUID.randomUUID();
//         request.setIdFuncionario(id);

//         when(funcionarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(funcionario));

//         when(descontoFactory.criarInss(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(inss);
//         when(descontoFactory.criarFgts(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(fgts);
//         when(descontoFactory.criarIrrf(any(Funcionario.class), any(FolhaRequest.class))).thenReturn(irrf);

//         when(inss.getValorDescontado()).thenReturn(0.0);
//         when(fgts.getValorDescontado()).thenReturn(0.0);
//         when(irrf.getValorDescontado()).thenReturn(0.0);

//         when(salarioService.calcularSalarioLiquido(eq(160.0), eq(0.0), eq(0.0), eq(0.0), eq(0.0)))
//                 .thenReturn(2000.0);

//         var folha = service.gerarFolhaDePagamento(request);

//         assertNotNull(folha);
//         assertEquals(2000.0, folha.getSalarioLiquido());
//         assertEquals(160, folha.getHorasTrabalhadas());
//         assertEquals(20, folha.getDiasTrabalhados());
//     }
// }
