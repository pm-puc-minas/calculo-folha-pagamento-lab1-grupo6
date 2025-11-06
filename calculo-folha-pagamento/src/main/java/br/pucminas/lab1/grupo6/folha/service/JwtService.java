package br.pucminas.lab1.grupo6.folha.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.pucminas.lab1.grupo6.folha.domain.user.User;
import br.pucminas.lab1.grupo6.folha.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(User user) {
        return Jwts.builder()
                    .claim("email", user.getEmail())
                    .claim("role", user.getRole())
                    .setSubject(user.getId().toString())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(getSignInKey())
                    .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractEmail(String token) {
        try {
            Claims claims = extractAllClaims(token);

            System.out.println("Claims extraídos: " + claims);
            return claims.get("email", String.class);
        } catch (Exception e) {
            System.out.println("Erro ao extrair email: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            throw e;
        }
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        Date exp = extractExpiration(token);
        return exp.before(new Date());
    }

    public boolean validateToken(String token, User user) {
        if (token == null || user == null) {
            throw new InvalidTokenException();
        }
        try {
            String subject = extractUserId(token);
            return subject.equals(user.getId().toString()) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidTokenException();
        }
    }
}