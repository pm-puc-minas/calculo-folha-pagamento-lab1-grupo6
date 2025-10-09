package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.desconto.DescontoFactory;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@Service
public class FolhaDePagamentoService {

    @Autowired
    private DescontoFactory descontoFactory;

    public FolhaDePagamento gerarFolhaDePagamento(Funcionario funcionario, FolhaRequest request) {
        var salarioBruto = funcionario.getSalarioBruto() == null ? 0.0 : funcionario.getSalarioBruto();
        var descontoInss = switch (funcionario.getCargo()) {
            case "Estagiário" -> salarioBruto * 0.12;
            case "Analista" -> salarioBruto * 0.15;
            case "Gerente" -> salarioBruto * 0.18;
            case "Diretor" -> salarioBruto * 0.20;
            default -> salarioBruto * 0.10;
        };
        int horasTrabalhadas = (int) Math.round(request.getCargaDiaria() * request.getDiasTrabalhados());
        var folhaDePagamento = new FolhaDePagamento();
        folhaDePagamento.setFuncionario(funcionario);
        folhaDePagamento.setMes(request.getMes());
        folhaDePagamento.setHorasTrabalhadas(horasTrabalhadas);
        folhaDePagamento.setDiasTrabalhados(request.getDiasTrabalhados());
        folhaDePagamento.setValorDeDescontoINSS(descontoInss);

        return folhaDePagamento;
}
}
