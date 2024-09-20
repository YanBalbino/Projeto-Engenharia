package com.streamit.streaming_service.dtos.series;

import java.util.List;

import com.streamit.streaming_service.dtos.media.CreateMediaDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateSeriesDTO {

    private CreateMediaDTO media;

    @NotEmpty(message = "Deve haver pelo menos uma temporada.")
    private List<CreateSeasonDTO> seasons;
}