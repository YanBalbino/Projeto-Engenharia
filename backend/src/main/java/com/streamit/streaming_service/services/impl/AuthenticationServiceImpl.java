package com.streamit.streaming_service.services.impl;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.login.LoginResponseDTO;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.IAuthenticationService;
import com.streamit.streaming_service.services.ITokenJWTService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private ITokenJWTService tokenService;

	@Override
	public LoginResponseDTO login(LoginDTO loginDto) {
    	var email = userRepository.findByEmail(loginDto.getEmail());
    	if(email == null) {
    		throw new UsernameNotFoundException("O email " + loginDto.getEmail() + " não existe.");
    	}
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            PersonModel user = (PersonModel) auth.getPrincipal();
            UUID idUser = user.getId();
            String token = tokenService.generateToken(user);
            LoginResponseDTO responseDto = new LoginResponseDTO();
            responseDto.setIdUser(idUser);
            responseDto.setToken(token);
            return responseDto;
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Senha incorreta. Tente novamente.", ex);
        } catch (InternalAuthenticationServiceException ex) {
            throw new InternalAuthenticationServiceException("Erro interno de autenticação", ex);
        }
	}
}
