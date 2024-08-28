package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.CreateFilmDTO;
import com.streamit.streaming_service.model.FilmModel;

public class FilmMapper {

	public static FilmModel toModel(CreateFilmDTO filmDto) {
		FilmModel filmModel = new FilmModel();
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
}
