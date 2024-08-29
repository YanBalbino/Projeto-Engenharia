package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.CreateFilmDTO;
import com.streamit.streaming_service.dtos.CreateSeriesDTO;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.SeriesModel;

public class MediaMapper {

	public static FilmModel filmToModel(CreateFilmDTO filmDto, FilmModel filmModel) {
		filmModel.setTitulo(filmDto.getTitulo());
		filmModel.setAnoProducao(filmDto.getAnoProducao());
		filmModel.setGenero(filmDto.getGenero());
		filmModel.setDuracao(filmDto.getDuracao());
		filmModel.setLegendasDisponiveis(filmDto.getLegendasDisponiveis());
		filmModel.setAudiosDisponiveis(filmDto.getAudiosDisponiveis());
		filmModel.setDescricao(filmDto.getDescricao());
		filmModel.setAtores(filmDto.getAtores());
		filmModel.setDiretor(filmDto.getDiretor());
		filmModel.setVideoUrl(filmDto.getVideoURL());
		return filmModel;
	}
	
	public static SeriesModel seriesToModel(CreateSeriesDTO seriesDto, SeriesModel seriesModel) {
		seriesModel.setTitulo(seriesDto.getTitulo());
		seriesModel.setAnoProducao(seriesDto.getAnoProducao());
		seriesModel.setGenero(seriesDto.getGenero());
		seriesModel.setDuracao(seriesDto.getDuracao());
		seriesModel.setLegendasDisponiveis(seriesDto.getLegendasDisponiveis());
		seriesModel.setAudiosDisponiveis(seriesDto.getAudiosDisponiveis());
		seriesModel.setDescricao(seriesDto.getDescricao());
		seriesModel.setAtores(seriesDto.getAtores());
		seriesModel.setDiretor(seriesDto.getDiretor());
		seriesModel.setVideoUrl(seriesDto.getVideoURL());
		seriesModel.setNumeroEpisodios(seriesDto.getNumeroEpisodios());
		seriesModel.setNumeroTemporadas(seriesDto.getNumeroTemporadas());
		return seriesModel;
	}
}
