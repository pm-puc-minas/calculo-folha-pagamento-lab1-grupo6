package br.pucminas.lab1.grupo6.folha.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.pucminas.lab1.grupo6.folha.domain.user.User;

public class AuthenticatedUser implements UserDetails {

    private User user;
    
    public AuthenticatedUser(User user) {
        this.user = user;
    }

    public User getUserEntity() { return user; }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Fallback to USER if role is missing to avoid NPE on legacy records
        String roleName = user.getRole() != null ? user.getRole().name() : "USER";
        return List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
    }
    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getEmail(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
