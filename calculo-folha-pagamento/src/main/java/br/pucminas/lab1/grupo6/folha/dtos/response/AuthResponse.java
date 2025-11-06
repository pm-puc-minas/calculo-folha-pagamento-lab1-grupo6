package br.pucminas.lab1.grupo6.folha.dtos.response;

import java.util.UUID;

import br.pucminas.lab1.grupo6.folha.domain.enums.Role;

public class AuthResponse {

    private String token;
    private String email;
    private Role role;
    private UUID userId;

    public AuthResponse(String token, String email, Role role, UUID id) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.userId = id;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public UUID getUserId() {
        return userId;
    }

    

}
