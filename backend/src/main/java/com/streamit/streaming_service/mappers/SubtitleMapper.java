package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;
import com.streamit.streaming_service.model.SubtitleModel;

public class SubtitleMapper {
	
    public static void toUpdateEntity(List<UpdateSubtitleDTO> legendasDto, List<SubtitleModel> legendasModel) {
        if (legendasDto != null) {
            for (UpdateSubtitleDTO legendaDto : legendasDto) {
                UUID legendaId = legendaDto.getId();
                for (SubtitleModel legendaModel : legendasModel) {
                    if (legendaModel.getId().equals(legendaId)) {
                        legendaModel.setIdioma(legendaDto.getIdioma());
                        legendaModel.setUrl(legendaDto.getLegendaUrl());
                        break;
                    }
                }
            }
        }
    }
	
    public static List<SubtitleModel> toEntityList(List<CreateSubtitleDTO> subtitleDTOs) {
        List<SubtitleModel> subtitles = new ArrayList<>();
        for (CreateSubtitleDTO subtitleDTO : subtitleDTOs) {
            SubtitleModel subtitle = new SubtitleModel();
            subtitle.setIdioma(subtitleDTO.getIdioma());
            subtitle.setUrl(subtitleDTO.getLegendaUrl());
            subtitles.add(subtitle);
        }
        return subtitles;
    }

    public static ReturnSubtitleDTO toDto(SubtitleModel subtitle) {
        ReturnSubtitleDTO dto = new ReturnSubtitleDTO();
        dto.setId(subtitle.getId());
        dto.setIdioma(subtitle.getIdioma());
        dto.setUrl(subtitle.getUrl());
        return dto;
    }
}

