package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.dtos.media.CreateMediaDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.MediaModel;

public class MediaMapper {
	
    public static void toUpdateEntity(UpdateMediaDTO dto, MediaModel media) {
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
        media.setImgUrl(dto.getImgUrl());
        media.setFaixaEtaria(dto.getFaixaEtaria());

        if (dto.getAtores() != null) {
            for (UpdateActorDTO actorDto : dto.getAtores()) {
                ActorModel actor = ActorMapper.findActorModelById(actorDto.getId(), media.getAtores());
                if (actor != null) {
                    ActorMapper.toUpdateEntity(actorDto, actor);
                }
            }
        }
    }
	
    public static void toEntity(CreateMediaDTO dto, MediaModel media) {
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
        media.setImgUrl(dto.getImgUrl());
        media.setFaixaEtaria(dto.getFaixaEtaria());
        
        if (dto.getAtores() != null) {
            List<ActorModel> actorModels = new ArrayList<>();
            for (CreateActorDTO actorDTO : dto.getAtores()) {
                ActorModel actor = ActorMapper.toEntity(actorDTO, media);
                actorModels.add(actor);
            }
            media.setAtores(actorModels);
        }
    }

    public static ReturnMediaDTO toDto(MediaModel media) {
        ReturnMediaDTO dto = new ReturnMediaDTO();
        dto.setId(media.getId());
        dto.setTitulo(media.getTitulo());
        dto.setAnoProducao(media.getAnoProducao());
        dto.setGenero(media.getGenero());
        dto.setDescricao(media.getDescricao());
        dto.setDiretor(media.getDiretor());
        dto.setImgUrl(media.getImgUrl());
        dto.setFaixaEtaria(media.getFaixaEtaria());
        
        List<ReturnActorDTO> actorDtos = new ArrayList<>();
        for (ActorModel actor : media.getAtores()) {
            actorDtos.add(ActorMapper.toDto(actor));
        }
        dto.setAtores(actorDtos);
        return dto;
    }
}

