package com.ambulance.ambulance.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ambulance.ambulance.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {
    @Value("${api.security.issuer}")
    private String apiIssuer;

    @Value("${api.security.secret}")
    private String apiSecret;

    @Value("${api.security.timeExpiration}")
    private Short apiTimeExpiration;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.apiSecret);
            return JWT.create()
                    .withIssuer(this.apiIssuer)
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(this.generateExpritationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public Instant generateExpritationDate() {
        return LocalDateTime.now().plusHours(this.apiTimeExpiration).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        String subject = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer(this.apiIssuer)
                    .build()
                    .verify(token);
            subject = verifier.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }

        if (subject == null) {
            throw new RuntimeException("Invalid token. Subject not found");
        }

        return subject;
    }
}
