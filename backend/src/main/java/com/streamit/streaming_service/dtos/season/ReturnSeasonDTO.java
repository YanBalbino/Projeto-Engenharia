package com.streamit.streaming_service.dtos.season;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;

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
public class ReturnSeasonDTO {

    private UUID id;
    private Integer seasonNumber;
    private List<ReturnEpisodeDTO> episodes;
}

