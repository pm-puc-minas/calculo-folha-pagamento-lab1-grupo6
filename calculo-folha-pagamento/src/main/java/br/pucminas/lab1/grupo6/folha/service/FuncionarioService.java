package br.pucminas.lab1.grupo6.folha.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.exceptions.InvalidRequestException;
import br.pucminas.lab1.grupo6.folha.exceptions.UserNotFoundException;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Funcionario insert(Funcionario funcionario) {
        if (funcionario == null) {
            throw new InvalidRequestException("Funcionário inválido.");
        }
        Funcionario resposta = repository.save(funcionario);
        eventPublisher.publishEvent(resposta);

        return resposta;
    }

    public Funcionario findById(UUID funcionarioId) {
        if (funcionarioId == null) {
            throw new InvalidRequestException("Id inválido ou nulo.");
        }
        return repository.findById(funcionarioId).orElseThrow(() -> new UserNotFoundException("Funcionário não encontrado. [id: " + funcionarioId + "]"));
    }

}
