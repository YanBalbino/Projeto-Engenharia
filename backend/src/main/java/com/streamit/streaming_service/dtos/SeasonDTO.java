package com.streamit.streaming_service.dtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeasonDTO {
	
    @NotNull(message = "Número da temporada é obrigatório.")
    private Integer seasonNumber;

    @NotEmpty(message = "Deve haver pelo menos um episódio.")
    private List<EpisodeDTO> episodes;
}
