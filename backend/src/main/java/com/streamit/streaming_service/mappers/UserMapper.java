package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.profile.ReturnProfileDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.UserModel;

public class UserMapper {

	public static ReturnUserDTO toDtoReturn(UserModel user) {
		if (user == null) {
			throw new IllegalArgumentException("Usuário não pode ser nulo.");
		}

		ReturnUserDTO dto = new ReturnUserDTO();
		dto.setId(user.getId());
		dto.setNome(user.getPerson().getNome());
		dto.setEmail(user.getPerson().getEmail());
		dto.setRole(user.getPerson().getRole());
		
		if(user.getPerfis() != null) {
			List<ReturnProfileDTO> profilesDtos = new ArrayList<>();
			for(ProfileModel profile : user.getPerfis()) {
				profilesDtos.add(ProfileMapper.toDTO(profile));
			}
			dto.setPerfis(profilesDtos);
		}
		
		return dto;
	}

}
