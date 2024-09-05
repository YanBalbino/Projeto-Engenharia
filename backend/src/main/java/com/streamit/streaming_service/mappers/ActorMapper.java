package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.SeriesModel;

public class ActorMapper {
	
	public static List<ActorModel> toEntityListToFilm(List<CreateActorDTO> atoresDto, FilmModel film) {
	    List<ActorModel> atores = new ArrayList<>();
        for (CreateActorDTO actorDTO : atoresDto) {
            ActorModel actor = new ActorModel();
            actor.setNome(actorDTO.getNome());
            actor.setImagemUrl(actorDTO.getImagemUrl());
            actor.setFilme(List.of(film)); 
            atores.add(actor);
        }
	    return atores;
	}

	public static List<ActorModel> toEntityListToSeries(List<CreateActorDTO> atoresDto, SeriesModel series) {
	    List<ActorModel> atores = new ArrayList<>();
        for (CreateActorDTO actorDTO : atoresDto) {
            ActorModel actor = new ActorModel();
            actor.setNome(actorDTO.getNome());
            actor.setImagemUrl(actorDTO.getImagemUrl());
            actor.setSerie(List.of(series));
            atores.add(actor);
        }
	    return atores;
	}

	
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

