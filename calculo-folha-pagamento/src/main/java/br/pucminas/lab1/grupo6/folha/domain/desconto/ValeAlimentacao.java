package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class ValeAlimentacao extends Desconto {
    
    public ValeAlimentacao(Funcionario funcionario, FolhaRequest folhaRequest) {
        setValorDescontado(calcular(funcionario, folhaRequest));
    }

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        return folhaRequest.getDiasTrabalhados() * folhaRequest.getValorValeAlimentacaoDiario();
    }
}
