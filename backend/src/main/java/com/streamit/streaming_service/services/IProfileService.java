package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CreateProfileDTO;

public interface IProfileService {

	CreateProfileDTO create(CreateProfileDTO profile, UUID idUser);
	CreateProfileDTO findById(UUID id);
	List<CreateProfileDTO> findAll();
	boolean update(CreateProfileDTO profile, UUID idUser);
	boolean delete(UUID id);
}
