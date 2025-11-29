package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;

public class Fgts extends Desconto {

    private static final Double PORCENTAGEM_DESCONTO = 0.08;

    public Fgts(Funcionario funcionario, FolhaRequest folhaRequest) {
        this.valorDescontado = calcular(funcionario, folhaRequest);   
    }

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        Double oitoPorCentroDoSalario = (funcionario.getSalarioBruto() * PORCENTAGEM_DESCONTO);
        return oitoPorCentroDoSalario;
    }
}
