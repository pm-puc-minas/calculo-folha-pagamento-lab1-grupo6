package br.pucminas.lab1.grupo6.folha.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;

@Component
public class FolhaDePagamentoEventListener {

    private static final Logger logger = LoggerFactory.getLogger(FolhaDePagamentoEventListener.class);

    @EventListener
    public void handleFolhaDePagamentoGerada(FolhaDePagamento folha) {
        
        logger.info(
            "Folha de Pagamento gerada. Funcionário: {}, Mês: {}, Salário Líquido: R$ {}",
            folha.getFuncionario().getNome(),
            folha.getMes(),
            folha.getSalarioLiquido()
        );
    }
}