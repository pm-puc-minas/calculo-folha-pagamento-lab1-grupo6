package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.enums.Aliquotas;
import br.pucminas.lab1.grupo6.folha.domain.enums.Faixas;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class Inss extends Desconto {

    public Inss(Funcionario funcionario, FolhaRequest folhaRequest){
        this.valorDescontado = calcular(funcionario, folhaRequest);
    }

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        
        double salarioBruto = funcionario.getSalarioBruto();

        double contribuicaoInss = 0.0;
        double limiteInferior = 0.0;

        Faixas[] faixasSalario = Faixas.values();
        Aliquotas[] aliquotas = Aliquotas.values();

        for(int i = 0; i < faixasSalario.length; i++) {
            double limiteFaixa = faixasSalario[i].getFaixa();
            double aliquota = aliquotas[i].getAliquota();

            double valorMinimo = Math.min(limiteFaixa, salarioBruto);
            double valorNaFaixa = (valorMinimo - limiteInferior);

            contribuicaoInss += valorNaFaixa * aliquota;

            if(salarioBruto <= limiteFaixa){
                break;
            }
            
            limiteInferior = limiteFaixa;
        }

        return contribuicaoInss;
    }
}
