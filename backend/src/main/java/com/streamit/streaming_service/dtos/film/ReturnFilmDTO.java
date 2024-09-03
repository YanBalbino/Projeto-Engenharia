package com.streamit.streaming_service.dtos.film;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReturnFilmDTO {

    private UUID id;
    private ReturnMediaDTO media;
    private Integer duracao;
    private String videoUrl;
    private List<ReturnSubtitleDTO> legendasDisponiveis;
    private List<ReturnAudioDTO> audiosDisponiveis;
    private List<ReturnActorDTO> atores;
}

