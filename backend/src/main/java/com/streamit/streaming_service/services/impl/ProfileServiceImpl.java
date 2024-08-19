package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreateProfileDTO;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ProfileMapper;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.ProfileRepository;
import com.streamit.streaming_service.services.IProfileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfileService{

	private ProfileRepository profileRepository;
	private UserServiceImpl userServiceImpl;
	
	@Override
	public CreateProfileDTO create(CreateProfileDTO profile, UUID idUser) {
		UserModel user = userServiceImpl.findUserModelById(idUser);
		ProfileModel entity = ProfileMapper.toModel(profile, user);
		ProfileModel entitySaved = profileRepository.save(entity);
		return ProfileMapper.toDTO(entitySaved);
	}

	@Override
	public CreateProfileDTO findById(UUID id) {
		ProfileModel entity = findProfileModelById(id);
		return ProfileMapper.toDTO(entity);
	}
	
	public ProfileModel findProfileModelById(UUID id) {
		return profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com id " + id));
	}

	@Override
	public List<CreateProfileDTO> findAll() { // alterar para retornar apenas perfis de determinado usuário
		List<ProfileModel> entities = profileRepository.findAll();
		List<CreateProfileDTO> dtos = new ArrayList<CreateProfileDTO>();
		for(ProfileModel profile : entities) {
			dtos.add(ProfileMapper.toDTO(profile));
		}
		return dtos;
	}

	@Override
	public boolean update(CreateProfileDTO profile, UUID idUser) {
		// TODO
		return false;
	}

	@Override
	public boolean delete(UUID id) {
		ProfileModel entity = findProfileModelById(id);
		profileRepository.delete(entity);
		return true;
	}

}
