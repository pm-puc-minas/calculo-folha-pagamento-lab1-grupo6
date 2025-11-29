package br.pucminas.lab1.grupo6.folha.domain.desconto;

import org.springframework.stereotype.Component;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;

@Component
public class DescontoFactory {

    public ValeAlimentacao criarValeAlimentacao(Funcionario funcionario, FolhaRequest folhaRequest) {
        return new ValeAlimentacao(funcionario, folhaRequest);
    }

    public Fgts criarFgts(Funcionario funcionario, FolhaRequest folhaRequest) {
        return new Fgts(funcionario, folhaRequest);
    }

    public Inss criarInss(Funcionario funcionario, FolhaRequest folhaRequest) {
        return new Inss(funcionario, folhaRequest);
    }

    public ValeTransporte criarValeTransporte(Funcionario funcionario, FolhaRequest folhaRequest) {
        return new ValeTransporte(funcionario, folhaRequest);
    }

    public Irrf criarIrrf(Funcionario funcionario, FolhaRequest folhaRequest) {
        return new Irrf(funcionario, folhaRequest);
    }

}
