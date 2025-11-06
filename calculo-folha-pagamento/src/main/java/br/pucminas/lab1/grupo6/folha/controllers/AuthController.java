package br.pucminas.lab1.grupo6.folha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.LoginRequest;
import br.pucminas.lab1.grupo6.folha.dtos.response.AuthResponse;
import br.pucminas.lab1.grupo6.folha.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse loginResponse = authService.authenticate(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Funcionario funcionario) {
        AuthResponse registerResponse = authService.register(funcionario);
        return ResponseEntity.ok().body(registerResponse);
    }
}
