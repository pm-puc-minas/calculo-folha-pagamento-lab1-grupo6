package br.pucminas.lab1.grupo6.folha.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.listeners.FolhaDePagamentoEventListener;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;
import br.pucminas.lab1.grupo6.folha.security.AuthenticatedUser;

@ExtendWith(MockitoExtension.class)
class FolhaDePagamentoServiceIntegrationTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FolhaDePagamentoEventListener folhaEventListener;

    @InjectMocks
    private FolhaDePagamentoService folhaService;

    @Test
    void devePublicarEvento_quandoFolhaDePagamentoForGerada() {
        Funcionario funcionario = new Funcionario();
        when(funcionarioRepository.findById(UUID.randomUUID())).thenReturn(Optional.of(funcionario));

        FolhaRequest request = new FolhaRequest();request.setMes(YearMonth.of(2024, 10));
        request.setDiasTrabalhados(20);
        request.setCargaDiaria(8);
        request.setJornadaMensal(160.0);
        request.setValorPensaoAlimenticia(0.0);
        request.setNumeroDeDependentes(0);
        request.setValeTransporteRecebido(0.0);
        request.setValorValeAlimentacaoDiario(0.0);

        AuthenticatedUser user = new AuthenticatedUser(funcionario);
        FolhaDePagamento folhaGerada = folhaService.gerarFolhaDePagamento(request, user);

        assertNotNull(folhaGerada);
        verify(folhaEventListener, times(1)).handleFolhaDePagamentoGerada(folhaGerada);
    }
}