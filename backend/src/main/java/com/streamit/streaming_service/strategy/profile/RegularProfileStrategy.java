package com.streamit.streaming_service.strategy.profile;

import java.util.List;

import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;

public class RegularProfileStrategy implements AgeRestrictionStrategy {
	
    @Override
    public List<ReturnMediaDTO> filterMedia(List<ReturnMediaDTO> mediaList) {
        return mediaList; 
    }
    
    @Override
    public List<ReturnFilmDTO> filterFilm(List<ReturnFilmDTO> filmList) {
    	return filmList; 
    }
    
    @Override
    public List<ReturnSeriesDTO> filterSeries(List<ReturnSeriesDTO> seriesList) {
    	return seriesList; 
    }
}

