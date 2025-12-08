package br.pucminas.lab1.grupo6.folha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.domain.user.User;
import br.pucminas.lab1.grupo6.folha.dtos.request.LoginRequest;
import br.pucminas.lab1.grupo6.folha.dtos.response.AuthResponse;
import br.pucminas.lab1.grupo6.folha.exceptions.DuplicateResourceException;
import br.pucminas.lab1.grupo6.folha.exceptions.InvalidCredentialsException;
import br.pucminas.lab1.grupo6.folha.exceptions.InvalidRequestException;
import br.pucminas.lab1.grupo6.folha.exceptions.UserNotFoundException;
import br.pucminas.lab1.grupo6.folha.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse authenticate(LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new InvalidRequestException("Email e senha devem ser informados");
        }
        String email = loginRequest.getEmail().trim();

        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return new AuthResponse(jwtService.generateToken(user), user.getEmail(), user.getRole(), user.getId());
    }

    public AuthResponse register(Funcionario funcionario) {
        if (funcionario == null) {
            throw new InvalidRequestException();
        }

        if (funcionario.getRole() == null) {
            funcionario.setRole(br.pucminas.lab1.grupo6.folha.domain.enums.Role.USER);
        }

        funcionario.setPassword(passwordEncoder.encode(funcionario.getPassword()));

        Funcionario resposta;
        try {
            resposta = userRepository.save(funcionario);
        } catch (RuntimeException e) {
            throw new DuplicateResourceException();
        }

        return new AuthResponse(jwtService.generateToken(resposta), resposta.getEmail(), resposta.getRole(), resposta.getId());
    }
}