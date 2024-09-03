package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.model.ActorModel;

public class ActorMapper {
	
    public static void toUpdateEntity(List<UpdateActorDTO> atoresDto, List<ActorModel> atoresModel) {
        if (atoresDto != null) {
            for (UpdateActorDTO atorDto : atoresDto) {
                UUID atorId = atorDto.getId();
                for (ActorModel atorModel : atoresModel) {
                    if (atorModel.getId().equals(atorId)) {
                        atorModel.setNome(atorDto.getNome());
                        atorModel.setImagemUrl(atorDto.getImagemUrl());
                        break;
                    }
                }
            }
        }
    }

    public static ReturnActorDTO toDto(ActorModel actor) {
        ReturnActorDTO dto = new ReturnActorDTO();
        dto.setId(actor.getId());
        dto.setImagemUrl(actor.getImagemUrl());
        dto.setNome(actor.getNome());
        return dto;
    }
}

