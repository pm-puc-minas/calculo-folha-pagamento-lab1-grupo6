// package br.pucminas.lab1.grupo6.folha.service;

// import static org.mockito.Mockito.timeout;
// import static org.mockito.Mockito.verify;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.time.YearMonth;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.SpyBean;
// import org.springframework.test.context.ActiveProfiles;

// import br.pucminas.lab1.grupo6.folha.domain.enums.Cargo;
// import br.pucminas.lab1.grupo6.folha.domain.enums.GrauInsalubridade;
// import br.pucminas.lab1.grupo6.folha.domain.enums.Periculosidade;
// import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
// import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
// import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
// import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
// import br.pucminas.lab1.grupo6.folha.listeners.FolhaDePagamentoEventListener;
// import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

// @SpringBootTest
// @ActiveProfiles("dev")
// public class FolhaDePagamentoServiceIntegrationTest {

//     @Autowired
//     private FolhaDePagamentoService folhaService;

//     @Autowired
//     private FuncionarioRepository funcionarioRepository;

//     @SpyBean
//     private FolhaDePagamentoEventListener folhaEventListener;
    
//     private Funcionario funcionarioTeste;

//     @BeforeEach
//     void setUp() {
//         funcionarioRepository.deleteAll(); 

//         Funcionario f = new Funcionario(null, "Funcionario Folha Evento", "98765432100", 
//                                         Cargo.GERENTE, 10000.0, Periculosidade.NAO, 
//                                         GrauInsalubridade.NENHUM, "gerente.evento@email.com", 
//                                         "123", Role.ADMIN);
//         funcionarioTeste = funcionarioRepository.save(f);
//     }

//     @Test
//     void devePublicarEvento_quandoFolhaDePagamentoForGerada() {
//         FolhaRequest request = new FolhaRequest();
//         request.setIdFuncionario(funcionarioTeste.getId());
//         request.setMes(YearMonth.of(2024, 10));
//         request.setDiasTrabalhados(20);
//         request.setCargaDiaria(8);
//         request.setJornadaMensal(160.0);
//         request.setValorPensaoAlimenticia(0.0);
//         request.setNumeroDeDependentes(0);
//         request.setValeTransporteRecebido(0.0);
//         request.setValorValeAlimentacaoDiario(0.0);

//         FolhaDePagamento folhaGerada = folhaService.gerarFolhaDePagamento(request);

//         assertNotNull(folhaGerada);
        
//         verify(folhaEventListener, timeout(1000).times(1))
//             .handleFolhaDePagamentoGerada(folhaGerada);
//     }
// }