package br.pucminas.lab1.grupo6.folha.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@Component
public class FuncionarioEventListener {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioEventListener.class);

    @EventListener
    public void handleFuncionarioCadastrado(Funcionario funcionario) {
        String nome = funcionario.getNome();
        String email = funcionario.getEmail();
        
        logger.info("Novo funcionário cadastrado com sucesso. Nome: {}, Email: {}", nome, email);
    }
}