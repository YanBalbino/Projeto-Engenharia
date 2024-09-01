package com.streamit.streaming_service.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class UpdateFilmDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UpdateMediaDTO media;
    
    private Integer duracao;

    private String videoUrl;

    private List<UpdateSubtitleDTO> legendasDisponiveis;

    private List<UpdateAudioDTO> audiosDisponiveis;

    private List<UpdateActorDTO> atores;
    
    private List<UUID> actorIds;
}
