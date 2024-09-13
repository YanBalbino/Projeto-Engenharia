package com.streamit.streaming_service.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.repositories.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

	PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username){
	    return personRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException(ApiConstants.MESSAGE_RESOURCE_NOT_FOUND));
	}
}
