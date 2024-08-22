package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;
import com.streamit.streaming_service.model.UserModel;

public class UserMapper {
    
    public static ReturnUserDTO toDtoReturn(UserModel user) {
        if (user == null) {
        	throw new IllegalArgumentException("UserModel não pode ser nulo.");
        }

        ReturnUserDTO dto = new ReturnUserDTO();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());

        List<ProfileDTO> profileDTOs = user.getPerfis().stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());

        dto.setPerfis(profileDTOs);

        return dto;
    }
}
