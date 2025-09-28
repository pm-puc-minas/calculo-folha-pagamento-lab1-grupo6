package br.pucminas.lab1.grupo6.folha.domain.desconto;

import java.math.BigDecimal;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class ValeTransporte extends Desconto {
    
    private static final BigDecimal PORCENTAGEM_DESCONTO = new BigDecimal("0.06");

    public ValeTransporte(Funcionario funcionario, FolhaRequest folhaRequest) {
        setValorDescontado(calcular(funcionario, folhaRequest));
    }

    @Override
    public BigDecimal calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        BigDecimal seisPorCentoDoSalario = funcionario.getSalarioBase().multiply(PORCENTAGEM_DESCONTO);

        if (seisPorCentoDoSalario.compareTo(new BigDecimal(folhaRequest.getValeTransporteRecebido())) < 0) {
            return seisPorCentoDoSalario;
        } else {
            return new BigDecimal(folhaRequest.getValeTransporteRecebido());
        }
    }

}
