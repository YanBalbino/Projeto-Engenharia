package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.ProfileDTO;

public interface IProfileService {

	ProfileDTO create(ProfileDTO profile, UUID idUser);
	ProfileDTO findProfileDtoById(UUID id);
	List<ProfileDTO> findProfileDetailsByUser(UUID idUser);
	boolean updateProfileName(String name, UUID idUser);
	boolean updateProfileIconUrl(String iconUrl, UUID idUser);
	boolean updateProfileGenerosPreferidos(List<String> generosPreferidos, UUID idUser);
	boolean alterarProfileInfatil(UUID idUser);
	boolean delete(UUID id);
}
