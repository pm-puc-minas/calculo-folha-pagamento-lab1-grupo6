package br.pucminas.lab1.grupo6.folha.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import br.pucminas.lab1.grupo6.folha.domain.enums.Cargo;
import br.pucminas.lab1.grupo6.folha.domain.enums.GrauInsalubridade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Periculosidade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.listeners.FuncionarioEventListener;

@SpringBootTest
@ActiveProfiles("dev")
public class FuncionarioServiceIntegrationTest {

    @Autowired
    private FuncionarioService funcionarioService;

    @SpyBean
    private FuncionarioEventListener funcionarioEventListener;

    @Test
    void devePublicarEvento_quandoNovoFuncionarioForInserido() {

        Funcionario f1 = new Funcionario(null, "Funcionario Teste Evento", "12345678900", 
                                         Cargo.ANALISTA, 3000.0, Periculosidade.NAO, 
                                         GrauInsalubridade.NENHUM, "evento@email.com", 
                                         "123", Role.USER);

        Funcionario funcionarioSalvo = funcionarioService.insert(f1);

        verify(funcionarioEventListener, timeout(1000).times(1))
            .handleFuncionarioCadastrado(funcionarioSalvo);
            
    }
}