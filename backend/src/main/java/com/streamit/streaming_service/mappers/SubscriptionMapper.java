package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.subscription.ReturnSubscriptionDTO;
import com.streamit.streaming_service.model.SubscriptionModel;

public class SubscriptionMapper {

    public static ReturnSubscriptionDTO toDto(SubscriptionModel model) {
        if (model == null) {
            return null;
        }

        ReturnSubscriptionDTO dto = new ReturnSubscriptionDTO();
        dto.setId(model.getId());
        dto.setDataInicio(model.getDataInicio());
        dto.setDataTermino(model.getDataTermino());
        dto.setStatusAtivo(model.isStatusAtivo());
        return dto;
    }

}
