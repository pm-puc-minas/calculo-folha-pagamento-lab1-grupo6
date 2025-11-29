package br.pucminas.lab1.grupo6.folha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;

@Repository
public interface FolhaDePagamentoRepository extends JpaRepository<FolhaDePagamento, Integer> {
}
