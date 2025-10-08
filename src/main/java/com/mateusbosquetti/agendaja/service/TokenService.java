package com.mateusbosquetti.agendaja.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class TokenService {

    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final Algorithm alg = Algorithm.HMAC256(SECRET);

    public String generateToken(UserAuthentication user) {
        return JWT.create()
                .withIssuer("AgendaJa")
                .withSubject(user.getUsername())
                .withExpiresAt(expireInstant())
                .sign(alg);
    }

    public String validateToken(String token) {
        try {

        return JWT.require(alg)
                .withIssuer("AgendaJa")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    private static Instant expireInstant() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
