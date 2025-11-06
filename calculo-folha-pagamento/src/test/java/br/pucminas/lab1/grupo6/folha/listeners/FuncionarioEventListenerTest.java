package br.pucminas.lab1.grupo6.folha.listeners;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

class FuncionarioEventListenerTest {

    @Test
    void deveLogarQuandoFuncionarioCadastrado() {
        Logger logger = (Logger) LoggerFactory.getLogger(FuncionarioEventListener.class);
        ListAppender<ILoggingEvent> appender = new ListAppender<>();
        appender.start();
        logger.addAppender(appender);

        var funcionario = new Funcionario();
        funcionario.setNome("Ana");
        funcionario.setEmail("ana@empresa.com");

        new FuncionarioEventListener().handleFuncionarioCadastrado(funcionario);

        boolean hasMessage = appender.list.stream()
            .anyMatch(e -> e.getLevel() == Level.INFO && e.getFormattedMessage().contains("Ana") && e.getFormattedMessage().contains("ana@empresa.com"));

        assertTrue(hasMessage);
        logger.detachAppender(appender);
    }
}
