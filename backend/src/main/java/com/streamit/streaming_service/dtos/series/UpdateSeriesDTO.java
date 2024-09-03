package com.streamit.streaming_service.dtos.series;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;
import com.streamit.streaming_service.dtos.season.UpdateSeasonDTO;

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