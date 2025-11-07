package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Funcionario insert(Funcionario funcionario) {
        Funcionario resposta = repository.save(funcionario);

        eventPublisher.publishEvent(resposta);

        return resposta;
    }

}
