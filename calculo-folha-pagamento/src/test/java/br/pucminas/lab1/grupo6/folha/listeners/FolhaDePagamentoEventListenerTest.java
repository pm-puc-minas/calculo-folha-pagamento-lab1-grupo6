package br.pucminas.lab1.grupo6.folha.listeners;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

class FolhaDePagamentoEventListenerTest {

    @Test
    void deveLogarQuandoFolhaGerada() {
        Logger logger = (Logger) LoggerFactory.getLogger(FolhaDePagamentoEventListener.class);
        ListAppender<ILoggingEvent> appender = new ListAppender<>();
        appender.start();
        logger.addAppender(appender);

        var funcionario = new Funcionario();
        funcionario.setNome("João");

        var folha = new FolhaDePagamento(
            funcionario,
            YearMonth.of(2025, 1),
            100.0,
            50.0,
            80.0,
            120.0,
            60.0,
            1234.56,
            20,
            160
        );

        new FolhaDePagamentoEventListener().handleFolhaDePagamentoGerada(folha);

        boolean hasMessage = appender.list.stream()
            .anyMatch(e -> e.getLevel() == Level.INFO
                && e.getFormattedMessage().contains("João")
                && e.getFormattedMessage().contains("2025-01")
                && e.getFormattedMessage().contains("1234.56"));

        assertTrue(hasMessage);
        logger.detachAppender(appender);
    }
}
