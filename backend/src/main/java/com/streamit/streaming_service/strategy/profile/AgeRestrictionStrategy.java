package com.streamit.streaming_service.strategy.profile;

import java.util.List;

import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;

public interface AgeRestrictionStrategy {
    List<ReturnMediaDTO> filterMedia(List<ReturnMediaDTO> mediaList);
    List<ReturnFilmDTO> filterFilm(List<ReturnFilmDTO> filmList);
    List<ReturnSeriesDTO> filterSeries(List<ReturnSeriesDTO> seriesList);
}

