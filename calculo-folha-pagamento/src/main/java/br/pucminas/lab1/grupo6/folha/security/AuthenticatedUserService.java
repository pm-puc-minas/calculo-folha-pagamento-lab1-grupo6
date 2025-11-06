package br.pucminas.lab1.grupo6.folha.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.user.User;
import br.pucminas.lab1.grupo6.folha.repositories.UserRepository;

@Service
public class AuthenticatedUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("User not found."));
        return new AuthenticatedUser(user);
    }

    public UserDetails loadUserById(UUID Id) {
        User user = userRepository.findById(Id).orElseThrow(() -> new RuntimeException("User not found."));
        return new AuthenticatedUser(user);
    }

}
