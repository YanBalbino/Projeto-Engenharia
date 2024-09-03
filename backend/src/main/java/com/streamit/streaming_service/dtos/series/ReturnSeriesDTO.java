package com.streamit.streaming_service.dtos.series;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.season.ReturnSeasonDTO;

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
public class ReturnSeriesDTO {

    private UUID id;
    private ReturnMediaDTO media;
    private List<ReturnSeasonDTO> seasons;
    private List<ReturnActorDTO> atores;
}

