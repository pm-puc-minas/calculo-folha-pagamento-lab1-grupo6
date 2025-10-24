package br.pucminas.lab1.grupo6.folha.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.lab1.grupo6.folha.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
