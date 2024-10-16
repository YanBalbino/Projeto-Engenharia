package com.streamit.streaming_service.services.impl;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.login.LoginResponseDTO;
import com.streamit.streaming_service.enums.UserRole;
import com.streamit.streaming_service.model.AdminModel;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.PersonRepository;
import com.streamit.streaming_service.services.IAdminService;
import com.streamit.streaming_service.services.IAuthenticationService;
import com.streamit.streaming_service.services.ITokenJWTService;
import com.streamit.streaming_service.services.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private IUserService userService;
    private IAdminService adminService;
    private PersonRepository personRepository;
    private AuthenticationManager authenticationManager;
    private ITokenJWTService tokenService;

	@Override
	public LoginResponseDTO login(LoginDTO loginDto) {
    	var email = personRepository.findByEmail(loginDto.getEmail());
    	if(email == null) {
    		throw new UsernameNotFoundException("O email " + loginDto.getEmail() + " não existe.");
    	}
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            PersonModel person = (PersonModel) auth.getPrincipal();
            String token = tokenService.generateToken(person);
            LoginResponseDTO responseDto = new LoginResponseDTO();
            if(person.getRole().equals(UserRole.USER)) {
            	UserModel user = userService.findUserModelByEmail(loginDto.getEmail());
            	responseDto.setIdUser(user.getId());
            	responseDto.setRole(UserRole.USER);
            }else {
            	AdminModel admin = adminService.findAdminModelByEmail(loginDto.getEmail());
            	responseDto.setIdUser(admin.getId());
            	responseDto.setRole(UserRole.ADMIN);
            }
            responseDto.setToken(token);
            return responseDto;
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Senha incorreta. Tente novamente.", ex);
        } catch (InternalAuthenticationServiceException ex) {
            throw new InternalAuthenticationServiceException("Erro interno de autenticação", ex);
        }
	}
}
