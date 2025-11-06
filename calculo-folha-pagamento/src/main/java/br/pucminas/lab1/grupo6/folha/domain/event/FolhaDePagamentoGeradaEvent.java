package br.pucminas.lab1.grupo6.folha.domain.event;

import org.springframework.context.ApplicationEvent;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;

public class FolhaDePagamentoGeradaEvent extends ApplicationEvent {

    private final FolhaDePagamento folhaDePagamento;

    public FolhaDePagamentoGeradaEvent(Object source, FolhaDePagamento folhaDePagamento) {
        super(source);
        this.folhaDePagamento = folhaDePagamento;
    }

    public FolhaDePagamento getFolhaDePagamento() {
        return folhaDePagamento;
    }
}