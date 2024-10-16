package com.streamit.streaming_service.services.impl;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.model.AdminModel;
import com.streamit.streaming_service.repositories.AdminRepository;
import com.streamit.streaming_service.services.IAdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements IAdminService {

	AdminRepository adminRepository;
	
    @Override
    public AdminModel findAdminModelByEmail(String email) {
    	return adminRepository.findByEmail(email)
    			.orElseThrow(() -> new ResourceNotFoundException("Admin não encontrado com email " + email));
    }

}