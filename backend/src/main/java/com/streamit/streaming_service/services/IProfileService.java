package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.dtos.UpdateProfileDTO;

public interface IProfileService {

	ProfileDTO create(ProfileDTO profile, UUID idUser);
	ProfileDTO findProfileDtoById(UUID id);
	List<ProfileDTO> findProfileDetailsByUser(UUID idUser);
	boolean updateProfile(UpdateProfileDTO updateProfileDTO, UUID idUser);
	boolean delete(UUID id);
}
