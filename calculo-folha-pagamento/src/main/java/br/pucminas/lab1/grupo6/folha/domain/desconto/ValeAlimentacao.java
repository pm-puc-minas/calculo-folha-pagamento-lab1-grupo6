package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;

public class ValeAlimentacao extends Desconto {
    
    public ValeAlimentacao(Funcionario funcionario, FolhaRequest folhaRequest) {
        this.valorDescontado = calcular(funcionario, folhaRequest);
    }

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        return folhaRequest.getDiasTrabalhados() * folhaRequest.getValorValeAlimentacaoDiario();
    }
}
