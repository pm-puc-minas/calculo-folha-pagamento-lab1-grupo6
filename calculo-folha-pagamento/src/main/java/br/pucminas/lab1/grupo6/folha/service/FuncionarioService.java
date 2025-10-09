package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario Insert(Funcionario funcionario) {
        Funcionario resposta = repository.save(funcionario);
        return resposta;
    }

}
