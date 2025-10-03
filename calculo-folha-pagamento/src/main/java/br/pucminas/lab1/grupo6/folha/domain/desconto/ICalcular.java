package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

public interface ICalcular {
    Double calcular(Funcionario funcionario, FolhaRequest folhaRequest);
}
