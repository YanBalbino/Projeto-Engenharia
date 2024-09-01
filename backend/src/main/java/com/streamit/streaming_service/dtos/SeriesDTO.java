package com.streamit.streaming_service.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeriesDTO {

    private MediaDTO media;

    @NotEmpty(message = "Deve haver pelo menos uma temporada.")
    private List<SeasonDTO> seasons;

    private List<ActorDTO> atores;
    
    private List<UUID> actorIds;
}