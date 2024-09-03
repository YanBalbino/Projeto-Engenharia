package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.profile.CreateProfileDTO;
import com.streamit.streaming_service.dtos.profile.ReturnProfileDTO;

public interface IProfileService {

	ReturnProfileDTO create(CreateProfileDTO profile, UUID idUser);
	ReturnProfileDTO findProfileDtoById(UUID id);
	List<ReturnProfileDTO> findProfileDetailsByUser(UUID idUser);
	ReturnProfileDTO updateProfile(CreateProfileDTO profileDTO, UUID id);
	void delete(UUID id);
}
