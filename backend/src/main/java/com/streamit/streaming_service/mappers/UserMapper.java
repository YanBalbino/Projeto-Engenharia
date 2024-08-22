package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.streamit.streaming_service.dtos.CreateUserDTO;
import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.UserModel;

public class UserMapper {

    public static UserModel toModel(CreateUserDTO dto) {
        if (dto == null) {
        	throw new IllegalArgumentException("CreateUserDTO não pode ser nulo.");
        }

        UserModel user = new UserModel();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        user.setMetodoPagamento(dto.getMetodoPagamento());
        user.setDataCadastro(dto.getDataCadastro());
        List<ProfileModel> profiles = dto.getPerfis().stream()
                .map(profileDTO -> ProfileMapper.toModel(profileDTO, user))
                .collect(Collectors.toList());

        user.setPerfis(profiles);
        return user;
    }

    public static CreateUserDTO toDtoCreate(UserModel user) {
        if (user == null) {
        	throw new IllegalArgumentException("UserModel não pode ser nulo.");
        }

        CreateUserDTO dto = new CreateUserDTO();
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setMetodoPagamento(user.getMetodoPagamento());
        dto.setDataCadastro(user.getDataCadastro());
        List<ProfileDTO> profileDTOs = user.getPerfis().stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());

        dto.setPerfis(profileDTOs);
        return dto;
    }
    
    public static ReturnUserDTO toDtoReturn(UserModel user) {
        if (user == null) {
        	throw new IllegalArgumentException("UserModel não pode ser nulo.");
        }

        ReturnUserDTO dto = new ReturnUserDTO();
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());

        List<ProfileDTO> profileDTOs = user.getPerfis().stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());

        dto.setPerfis(profileDTOs);

        return dto;
    }
}
