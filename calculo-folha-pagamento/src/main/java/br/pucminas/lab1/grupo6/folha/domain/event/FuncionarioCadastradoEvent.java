package br.pucminas.lab1.grupo6.folha.domain.event;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import org.springframework.context.ApplicationEvent;

public class FuncionarioCadastradoEvent extends ApplicationEvent {

    private final Funcionario funcionario;

    public FuncionarioCadastradoEvent(Object source, Funcionario funcionario) {
        super(source);
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
}