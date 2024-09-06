package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;
import com.streamit.streaming_service.model.SubtitleModel;

public class SubtitleMapper {

    public static SubtitleModel toEntity(CreateSubtitleDTO subtitleDTO) {
        SubtitleModel subtitle = new SubtitleModel();
        subtitle.setIdioma(subtitleDTO.getIdioma());
        subtitle.setUrl(subtitleDTO.getLegendaUrl());
        return subtitle;
    }

    public static void toUpdateEntity(UpdateSubtitleDTO subtitleDto, SubtitleModel subtitleModel) {
        if (subtitleDto != null) {
            subtitleModel.setIdioma(subtitleDto.getIdioma());
            subtitleModel.setUrl(subtitleDto.getLegendaUrl());
        }
    }

    public static ReturnSubtitleDTO toDto(SubtitleModel subtitle) {
        ReturnSubtitleDTO dto = new ReturnSubtitleDTO();
        dto.setId(subtitle.getId());
        dto.setIdioma(subtitle.getIdioma());
        dto.setUrl(subtitle.getUrl());
        return dto;
    }
    
    public static SubtitleModel findSubtitleModelById(UUID id, List<SubtitleModel> subtitles) {
        for (SubtitleModel subtitle : subtitles) {
            if (subtitle.getId().equals(id)) {
                return subtitle;
            }
        }
        return null;
    }
}


