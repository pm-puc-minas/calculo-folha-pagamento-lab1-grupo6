package br.pucminas.lab1.grupo6.folha.domain.desconto;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;

public interface ICalcular {
    Double calcular(Funcionario funcionario, FolhaRequest folhaRequest);
}
