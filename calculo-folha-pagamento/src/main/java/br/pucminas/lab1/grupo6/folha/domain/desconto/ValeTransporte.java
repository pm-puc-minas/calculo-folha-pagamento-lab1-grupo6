package br.pucminas.lab1.grupo6.folha.domain.desconto;

import java.math.BigDecimal;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class ValeTransporte extends Desconto {
    
    private static final Double PORCENTAGEM_DESCONTO = 0.06;

    public ValeTransporte(Funcionario funcionario, FolhaRequest folhaRequest) {
        setValorDescontado(calcular(funcionario, folhaRequest));
    }

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        Double seisPorCentoDoSalario = (funcionario.getSalarioBase() * PORCENTAGEM_DESCONTO);
        
        if (seisPorCentoDoSalario < folhaRequest.getValeTransporteRecebido()) {
            return seisPorCentoDoSalario;
        } else {
            return folhaRequest.getValeTransporteRecebido();
        }
    }

}
