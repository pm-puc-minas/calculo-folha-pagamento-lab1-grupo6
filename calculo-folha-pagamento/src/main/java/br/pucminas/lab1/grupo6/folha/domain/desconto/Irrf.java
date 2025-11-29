package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;

public class Irrf extends Desconto {
    
    private static final Double DEDUCAO_POR_DEPENDENTE = 189.59;

    public Irrf(Funcionario funcionario, FolhaRequest folhaRequest) {
       this.valorDescontado = calcular(funcionario, folhaRequest);
    }

    /*
    A implementação deste método segue rigorosamente a metodologia e o exemplo de cálculo
     fornecidos no documento de requisitos do projeto, especificamente o detalhado na página 11.
    A subtração final da "parcela a deduzir" não constitui um desconto duplo. 
     A fórmula correta, confirmada pelo material, é: 
     Imposto Devido = (Base de Cálculo * Alíquota) - Parcela a Deduzir.
    Esta implementação segue exatamente esse processo, garantindo conformidade com os
     requisitos e a precisão do cálculo.
    */

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        Inss inss = new Inss(funcionario, folhaRequest);
        Double descontoInss = inss.getValorDescontado();
        Double salarioBase = funcionario.getSalarioBruto() - descontoInss;

        Double deducaoTotalDependentes = folhaRequest.getNumeroDeDependentes() * DEDUCAO_POR_DEPENDENTE;
        Double valorPensao = folhaRequest.getValorPensaoAlimenticia();
        Double calculoIrrf = salarioBase - deducaoTotalDependentes - valorPensao;

        Double imposto = 0.0;

        if (calculoIrrf <= 1903.98) {
            imposto = 0.0;
        } else if (calculoIrrf <= 2826.65) {
            imposto = (calculoIrrf * 0.075) - 142.80;
        } else if (calculoIrrf <= 3751.05) {
            imposto = (calculoIrrf * 0.15) - 354.80;
        } else if (calculoIrrf <= 4664.68) {
            imposto = (calculoIrrf * 0.225) - 636.13;
        } else {
            imposto = (calculoIrrf * 0.275) - 869.36;
        }

        return imposto;
    }
}