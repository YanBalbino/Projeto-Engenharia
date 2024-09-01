package com.streamit.streaming_service.dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateSeriesDTO {

    private String titulo;

    private Integer anoProducao;

    private String genero;

    private String descricao;

    private String diretor;

    private List<UpdateSeasonDTO> seasons;

    private List<UpdateActorDTO> atores;
    
    private List<UUID> actorIds;
}