package com.streamit.streaming_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.login.LoginResponseDTO;
import com.streamit.streaming_service.services.IAuthenticationService;
import com.streamit.streaming_service.services.ITokenJWTService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class AuthenticationController {

	private final IAuthenticationService authenticationService;
	private final ITokenJWTService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDto) {
		LoginResponseDTO responseDto = authenticationService.login(loginDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
    @GetMapping("/recover-id") // remover
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String cleanedToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            String userId = tokenService.validateToken(cleanedToken);
            return ResponseEntity.ok(userId); 
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }
}
