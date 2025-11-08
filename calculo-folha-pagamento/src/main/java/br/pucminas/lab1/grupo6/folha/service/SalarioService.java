package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@Service
public class SalarioService {

    public double calcularSalarioPorHora(Funcionario funcionario, FolhaRequest request) {
         double salarioBruto = funcionario.getSalarioBruto() == null ? 0.0 : funcionario.getSalarioBruto();
         double horasTrabalhadasPorMes = request.getJornadaMensal();

         if(horasTrabalhadasPorMes <= 0) {
              double cargaDiaria = request.getCargaDiaria();
                int diasTrabalhados = request.getDiasTrabalhados();
                if(cargaDiaria > 0 && diasTrabalhados > 0) {
                    horasTrabalhadasPorMes = cargaDiaria * diasTrabalhados;
                } else {
                throw new IllegalArgumentException("Jornada mensal não pode ser zero.");
           }
        }
        double valorHora = salarioBruto / horasTrabalhadasPorMes;
        return Math.round(valorHora * 100.0) / 100.0;
    }

    public double calcularSalarioLiquido(double salarioBruto, double inss, double irrf, double valeTransporte, double valeAlimentacao) {
        return salarioBruto - inss - irrf - valeTransporte - valeAlimentacao;
    }
}
