package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CreateProfileDTO;
import com.streamit.streaming_service.dtos.ReturnProfileDTO;

public interface IProfileService {

	ReturnProfileDTO create(CreateProfileDTO profile, UUID idUser);
	ReturnProfileDTO findProfileDtoById(UUID id);
	List<ReturnProfileDTO> findProfileDetailsByUser(UUID idUser);
	boolean updateProfile(CreateProfileDTO profileDTO, UUID id);
	boolean delete(UUID id);
}
