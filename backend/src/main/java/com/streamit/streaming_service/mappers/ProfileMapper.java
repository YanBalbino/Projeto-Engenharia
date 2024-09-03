package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.profile.CreateProfileDTO;
import com.streamit.streaming_service.dtos.profile.ReturnProfileDTO;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.UserModel;

public class ProfileMapper {

    public static ProfileModel toModel(CreateProfileDTO dto, UserModel user) {
        if (dto == null || user == null) {
            throw new IllegalArgumentException("CreateProfileDTO e UserModel não podem ser nulos.");
        }

        ProfileModel profile = new ProfileModel();
        profile.setNome(dto.getNome());
        profile.setPerfilInfantil(dto.getPerfilInfantil());
        profile.setGenerosPreferidos(dto.getGenerosPreferidos());
        profile.setIconUrl(dto.getIconUrl());
        profile.setUser(user);

        return profile;
    }

    public static ReturnProfileDTO toDTO(ProfileModel model) {
        if (model == null) {
            throw new IllegalArgumentException("ProfileModel não pode ser nulo.");
        }

        ReturnProfileDTO dto = new ReturnProfileDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setIconUrl(model.getIconUrl());
        dto.setPerfilInfantil(model.isPerfilInfantil());
        dto.setGenerosPreferidos(model.getGenerosPreferidos());

        return dto;
    }
}

