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

    private UpdateMediaDTO media;

    private List<UpdateSeasonDTO> seasons;

    private List<UpdateActorDTO> atores;
    
    private List<UUID> actorIds;
}