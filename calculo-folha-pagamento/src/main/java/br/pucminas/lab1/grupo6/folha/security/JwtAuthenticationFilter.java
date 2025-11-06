package br.pucminas.lab1.grupo6.folha.security;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.pucminas.lab1.grupo6.folha.exceptions.InvalidTokenException;
import br.pucminas.lab1.grupo6.folha.exceptions.UserNotFoundException;
import br.pucminas.lab1.grupo6.folha.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService; 

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7).trim();

        UUID userId;
        try {
            userId = UUID.fromString(jwtService.extractUserId(token));
        } catch (JwtException e) {  
            throw e;
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;
            try {
                userDetails = authenticatedUserService.loadUserById(userId);
            } catch (RuntimeException e) {
                throw new UserNotFoundException("Usuário não encontrado.");
            }
            
            try {
                if (jwtService.validateToken(token, ((AuthenticatedUser) userDetails).getUserEntity())) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (RuntimeException e) {
                throw new InvalidTokenException();
            }
        }

        filterChain.doFilter(request, response);
    }

}
