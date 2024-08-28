package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.CreateSeriesDTO;
import com.streamit.streaming_service.model.SeriesModel;

public class SeriesMapper {

	public static SeriesModel toModel(CreateSeriesDTO seriesDto) {
		SeriesModel seriesModel = new SeriesModel();
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
