package com.streamit.streaming_service.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateEpisodeDTO {

    private Integer numeroEpisodio;

    private String tituloEpisodio;

    private Integer duracaoEpisodio;

    private String videoUrl;

    private List<UpdateSubtitleDTO> legendasDisponiveis;

    private List<UpdateAudioDTO> audiosDisponiveis;
}

