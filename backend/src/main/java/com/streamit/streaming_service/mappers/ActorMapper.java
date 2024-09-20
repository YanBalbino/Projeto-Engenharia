package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.MediaModel;

public class ActorMapper {

    public static ActorModel toEntity(CreateActorDTO actorDTO, MediaModel media) {
        ActorModel actor = new ActorModel();
        actor.setNome(actorDTO.getNome());
        actor.setImagemUrl(actorDTO.getImagemUrl());
        actor.setMidias(List.of(media));
        return actor;
    }

    public static void toUpdateEntity(UpdateActorDTO actorDto, ActorModel actorModel) {
        if (actorDto != null) {
            actorModel.setNome(actorDto.getNome());
            actorModel.setImagemUrl(actorDto.getImagemUrl());
        }
    }

    public static ReturnActorDTO toDto(ActorModel actor) {
        ReturnActorDTO dto = new ReturnActorDTO();
        dto.setId(actor.getId());
        dto.setImagemUrl(actor.getImagemUrl());
        dto.setNome(actor.getNome());
        return dto;
    }
    
    public static ActorModel findActorModelById(UUID id, List<ActorModel> actors) {
        for (ActorModel actor : actors) {
            if (actor.getId().equals(id)) {
                return actor;
            }
        }
        return null;
    }
}


