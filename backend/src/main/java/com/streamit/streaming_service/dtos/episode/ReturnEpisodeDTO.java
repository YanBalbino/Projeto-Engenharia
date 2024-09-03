package com.streamit.streaming_service.dtos.episode;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
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
public class ReturnEpisodeDTO {

    private UUID id;
    private Integer numeroEpisodio;
    private String tituloEpisodio;
    private Integer duracaoEpisodio;
    private String videoUrl;
    private List<ReturnSubtitleDTO> legendasDisponiveis;
    private List<ReturnAudioDTO> audiosDisponiveis;
}

