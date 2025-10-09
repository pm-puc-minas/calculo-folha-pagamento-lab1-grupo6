package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

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
