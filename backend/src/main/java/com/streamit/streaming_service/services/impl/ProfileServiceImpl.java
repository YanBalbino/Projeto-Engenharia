package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.profile.CreateProfileDTO;
import com.streamit.streaming_service.dtos.profile.ReturnProfileDTO;
import com.streamit.streaming_service.dtos.profile.UpdateProfileDTO;
import com.streamit.streaming_service.exceptions.MaxProfilesLimitReachedException;
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
	public ReturnProfileDTO create(CreateProfileDTO profile, UUID idUser) {
		UserModel user = userServiceImpl.findUserModelById(idUser);
		if(user.getPerfis().size() == 4) {
			throw new MaxProfilesLimitReachedException("Limite de perfis atingido para o usuário " + user.getNome());
		}
		ProfileModel entity = ProfileMapper.toModel(profile, user);
		ProfileModel entitySaved = profileRepository.save(entity);
		return ProfileMapper.toDTO(entitySaved);
	}

	@Override
	public ReturnProfileDTO findProfileDtoById(UUID id) {
		ProfileModel entity = findProfileModelById(id);
		return ProfileMapper.toDTO(entity);
	}

	public ProfileModel findProfileModelById(UUID id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com id " + id));
	}

	@Override
	public List<ReturnProfileDTO> findProfileDetailsByUser(UUID idUser) {
		List<ProfileModel> entities = profileRepository.findProfileDetailsByUser(idUser);
		List<ReturnProfileDTO> profiles = new ArrayList<>();
		for(ProfileModel entity : entities) {
			profiles.add(ProfileMapper.toDTO(entity));
		}
		return profiles;
	}

	@Override
	public ReturnProfileDTO updateProfile(UpdateProfileDTO profileDTO) {
	    ProfileModel entity = findProfileModelById(profileDTO.getId());
	    
	    if (profileDTO.getNome() != null) {
	        entity.setNome(profileDTO.getNome());
	    }
	    
	    if (profileDTO.getIconUrl() != null) {
	        entity.setIconUrl(profileDTO.getIconUrl());
	    }
	    
	    if (profileDTO.getGenerosPreferidos() != null) {
	        entity.setGenerosPreferidos(profileDTO.getGenerosPreferidos());
	    }
	    
	    if (profileDTO.getPerfilInfantil() != null) {
	        entity.setPerfilInfantil(profileDTO.getPerfilInfantil());
	    }

		ProfileModel entitySaved = profileRepository.save(entity);
		return ProfileMapper.toDTO(entitySaved);
	}

	@Override
	public void delete(UUID id) {
		ProfileModel entity = findProfileModelById(id);
		profileRepository.delete(entity);
	}
	
}
