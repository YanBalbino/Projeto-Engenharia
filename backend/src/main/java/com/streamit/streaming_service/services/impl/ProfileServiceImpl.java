package com.streamit.streaming_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.dtos.UpdateProfileDTO;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ProfileMapper;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.ProfileRepository;
import com.streamit.streaming_service.services.IProfileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfileService {

	private ProfileRepository profileRepository;
	private UserServiceImpl userServiceImpl;

	@Override
	public ProfileDTO create(ProfileDTO profile, UUID idUser) {
		UserModel user = userServiceImpl.findUserModelById(idUser);
		ProfileModel entity = ProfileMapper.toModel(profile, user);
		ProfileModel entitySaved = profileRepository.save(entity);
		return ProfileMapper.toDTO(entitySaved);
	}

	@Override
	public ProfileDTO findProfileDtoById(UUID id) {
		ProfileModel entity = findProfileModelById(id);
		return ProfileMapper.toDTO(entity);
	}

	public ProfileModel findProfileModelById(UUID id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com id " + id));
	}

	@Override
	public List<ProfileDTO> findProfileDetailsByUser(UUID idUser) {
		List<ProfileDTO> profiles = profileRepository.findProfileDetailsByUser(idUser);
		return profiles;
	}

	@Override
	public boolean updateProfile(UpdateProfileDTO updateProfileDTO, UUID idUser) {
	    ProfileModel entity = findProfileModelById(idUser);
	    
	    if (updateProfileDTO.getNome() != null) {
	        entity.setNome(updateProfileDTO.getNome());
	    }
	    
	    if (updateProfileDTO.getIconUrl() != null) {
	        entity.setIconUrl(updateProfileDTO.getIconUrl());
	    }
	    
	    if (updateProfileDTO.getGenerosPreferidos() != null) {
	        entity.setGenerosPreferidos(updateProfileDTO.getGenerosPreferidos());
	    }
	    
	    if (updateProfileDTO.getPerfilInfantil() != null) {
	        entity.setPerfilInfantil(updateProfileDTO.getPerfilInfantil());
	    }

	    profileRepository.save(entity);
	    return true;
	}


	@Override
	public boolean delete(UUID id) {
		ProfileModel entity = findProfileModelById(id);
		profileRepository.delete(entity);
		return true;
	}

}
