package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public class Inss extends Desconto {

    private static final Double PRIMEIRA_ALIQUOTA = 0.075;
    private static final Double SEGUNDA_ALIQUOTA = 0.09;
    private static final Double TERCEIRA_ALIQUOTA = 0.12;
    private static final Double QUARTA_ALIQUOTA = 0.14;

    private static final Double FAIXA_1 = 1302.00;
    private static final Double FAIXA_2 = 2571.29;
    private static final Double FAIXA_3 = 3856.94;
    private static final Double FAIXA_4 = 7507.49;


    public Inss(Funcionario funcionario, FolhaRequest folhaRequest){
        this.valorDescontado = calcular(funcionario, folhaRequest);
    }

    @Override
    public Double calcular(Funcionario funcionario, FolhaRequest folhaRequest) {
        
        double salarioBruto = funcionario.getSalarioBruto();
        double[] aliquotas = {PRIMEIRA_ALIQUOTA, SEGUNDA_ALIQUOTA, TERCEIRA_ALIQUOTA, QUARTA_ALIQUOTA};
        double[] faixasSalario = {FAIXA_1, FAIXA_2, FAIXA_3, FAIXA_4};

        double contribuicaoInss = 0.0;
        double limiteInferior = 0.0;
        for(int i = 0; i < faixasSalario.length; i++){
            double valorNaFaixa = (Math.min(faixasSalario[i], salarioBruto) - limiteInferior);
            contribuicaoInss += valorNaFaixa * aliquotas[i];

            if(salarioBruto <= faixasSalario[i]){
                break;
            }
            
            limiteInferior = faixasSalario[i];
        }

        return contribuicaoInss;
    }
}
