package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.UserModel;

public class ProfileMapper {

    public static ProfileModel toModel(ProfileDTO dto, UserModel user) {
        if (dto == null || user == null) {
            throw new IllegalArgumentException("DTO e UserModel não podem ser nulos.");
        }

        ProfileModel profile = new ProfileModel();
        profile.setNome(dto.getNome());
        profile.setPerfilInfantil(dto.getPerfilInfantil());
        profile.setGenerosPreferidos(dto.getGenerosPreferidos());
        profile.setUser(user);

        return profile;
    }

    public static ProfileDTO toDTO(ProfileModel model) {
        if (model == null) {
            throw new IllegalArgumentException("ProfileModel não pode ser nulo.");
        }

        ProfileDTO dto = new ProfileDTO();
        dto.setNome(model.getNome());
        dto.setPerfilInfantil(model.isPerfilInfantil());
        dto.setGenerosPreferidos(model.getGenerosPreferidos());
        dto.setUserId(model.getUser().getId());

        return dto;
    }
}

