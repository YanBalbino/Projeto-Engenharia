package com.streamit.streaming_service.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.services.ITokenJWTService;

@Service
public class TokenJWTServiceImpl implements ITokenJWTService{

    @Value("${api.streamit.token.secret}")
    private String secret;
    
    @Override
    public String generateToken(PersonModel user) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("streamit-api")
                .withSubject(user.getEmail().toString()) 
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
        return token;
    }
    
    @Override
    public String validateToken(String token) throws JWTVerificationException{
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("streamit-api")
                .build()
                .verify(token)
                .getSubject(); 
    }
    
    @Override
    public Instant generateExpirationDate() {
        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
