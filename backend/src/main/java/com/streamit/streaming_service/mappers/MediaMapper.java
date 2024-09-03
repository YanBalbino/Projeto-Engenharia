package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.media.CreateMediaDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;
import com.streamit.streaming_service.model.MediaModel;

public class MediaMapper {
	
    public static void toUpdateEntity(UpdateMediaDTO dto, MediaModel media) {
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
    }
	
    public static void toEntity(CreateMediaDTO dto, MediaModel media) {
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
    }

    public static ReturnMediaDTO toDto(MediaModel media) {
        ReturnMediaDTO dto = new ReturnMediaDTO();
        dto.setId(media.getId());
        dto.setTitulo(media.getTitulo());
        dto.setAnoProducao(media.getAnoProducao());
        dto.setGenero(media.getGenero());
        dto.setDescricao(media.getDescricao());
        return dto;
    }
}

