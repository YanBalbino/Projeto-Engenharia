package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.SubscriptionDTO;
import com.streamit.streaming_service.model.SubscriptionModel;

public class SubscriptionMapper {

    public static SubscriptionDTO toDto(SubscriptionModel model) {
        if (model == null) {
            return null;
        }

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(model.getId());
        dto.setDataInicio(model.getDataInicio());
        dto.setDataTermino(model.getDataTermino());
        dto.setStatusAtivo(model.isStatusAtivo());
        return dto;
    }

    public static SubscriptionModel toModel(SubscriptionDTO dto) {
        if (dto == null) {
            return null;
        }

        SubscriptionModel model = new SubscriptionModel();
        model.setId(dto.getId());
        model.setDataInicio(dto.getDataInicio());
        model.setDataTermino(dto.getDataTermino());
        model.setStatusAtivo(dto.isStatusAtivo());
        return model;
    }
}
