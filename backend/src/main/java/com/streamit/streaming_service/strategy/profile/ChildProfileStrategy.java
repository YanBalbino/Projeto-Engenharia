package com.streamit.streaming_service.strategy.profile;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.enums.FaixaEtaria;

public class ChildProfileStrategy implements AgeRestrictionStrategy {
	@Override
	public List<ReturnMediaDTO> filterMedia(List<ReturnMediaDTO> mediaList) {
		List<ReturnMediaDTO> filteredMedia = new ArrayList<>();
		for (ReturnMediaDTO media : mediaList) {
			if ((media.getFaixaEtaria().equals(FaixaEtaria.LIVRE))) {
				filteredMedia.add(media);
			}
		}
		return filteredMedia;
	}
	
	@Override
	public List<ReturnFilmDTO> filterFilm(List<ReturnFilmDTO> filmList) {
		List<ReturnFilmDTO> filteredFilm = new ArrayList<>();
		for (ReturnFilmDTO film : filmList) {
			if ((film.getMedia().getFaixaEtaria().equals(FaixaEtaria.LIVRE))) {
				filteredFilm.add(film);
			}
		}
		return filteredFilm;
	}
	
	@Override
	public List<ReturnSeriesDTO> filterSeries(List<ReturnSeriesDTO> seriesList) {
		List<ReturnSeriesDTO> filteredSeries = new ArrayList<>();
		for (ReturnSeriesDTO series : seriesList) {
			if ((series.getMedia().getFaixaEtaria().equals(FaixaEtaria.LIVRE))) {
				filteredSeries.add(series);
			}
		}
		return filteredSeries;
	}
}
