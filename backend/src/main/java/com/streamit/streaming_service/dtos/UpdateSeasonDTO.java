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
public class UpdateSeasonDTO {
	
    private Integer seasonNumber;

    private List<UpdateEpisodeDTO> episodes;
}
