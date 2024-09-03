package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.model.AudioModel;

public class AudioMapper {
	
    public static void toUpdateEntity(List<UpdateAudioDTO> audiosDto, List<AudioModel> audiosModel) {
        if (audiosDto != null) {
            for (UpdateAudioDTO audioDto : audiosDto) {
                UUID audioId = audioDto.getId();
                for (AudioModel audioModel : audiosModel) {
                    if (audioModel.getId().equals(audioId)) {
                        audioModel.setIdioma(audioDto.getIdioma());
                        audioModel.setUrl(audioDto.getAudioUrl());
                        break;
                    }
                }
            }
        }
    }
	
    public static List<AudioModel> toEntityList(List<CreateAudioDTO> audioDTOs) {
        List<AudioModel> audios = new ArrayList<>();
        for (CreateAudioDTO audioDTO : audioDTOs) {
            AudioModel audio = new AudioModel();
            audio.setIdioma(audioDTO.getIdioma());
            audio.setUrl(audioDTO.getAudioUrl());
            audios.add(audio);
        }
        return audios;
    }

    public static ReturnAudioDTO toDto(AudioModel audio) {
        ReturnAudioDTO dto = new ReturnAudioDTO();
        dto.setId(audio.getId());
        dto.setIdioma(audio.getIdioma());
        dto.setUrl(audio.getUrl());
        return dto;
    }
}

