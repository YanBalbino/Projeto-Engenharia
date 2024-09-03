package com.streamit.streaming_service.dtos.season;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateSeasonDTO {
	
	private UUID id;
	
    private Integer seasonNumber;

    private List<UpdateEpisodeDTO> episodes;
}
