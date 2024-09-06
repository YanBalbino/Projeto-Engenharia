package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.model.AudioModel;

public class AudioMapper {

    public static AudioModel toEntity(CreateAudioDTO audioDTO) {
        AudioModel audio = new AudioModel();
        audio.setIdioma(audioDTO.getIdioma());
        audio.setUrl(audioDTO.getAudioUrl());
        return audio;
    }

    public static void toUpdateEntity(UpdateAudioDTO audioDto, AudioModel audioModel) {
        if (audioDto != null) {
            audioModel.setIdioma(audioDto.getIdioma());
            audioModel.setUrl(audioDto.getAudioUrl());
        }
    }

    public static ReturnAudioDTO toDto(AudioModel audio) {
        ReturnAudioDTO dto = new ReturnAudioDTO();
        dto.setId(audio.getId());
        dto.setIdioma(audio.getIdioma());
        dto.setUrl(audio.getUrl());
        return dto;
    }
    
    public static AudioModel findAudioModelById(UUID id, List<AudioModel> audios) {
        for (AudioModel audio : audios) {
            if (audio.getId().equals(id)) {
                return audio;
            }
        }
        return null;
    }
}


