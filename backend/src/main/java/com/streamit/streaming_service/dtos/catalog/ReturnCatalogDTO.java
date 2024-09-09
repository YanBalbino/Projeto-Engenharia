package com.streamit.streaming_service.dtos.catalog;

import java.util.List;

import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;

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
public class ReturnCatalogDTO {
    private List<ReturnFilmDTO> filmes;
    private List<ReturnSeriesDTO> series;
}
