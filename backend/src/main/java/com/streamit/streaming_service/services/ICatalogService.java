package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CatalogDTO;
import com.streamit.streaming_service.dtos.CreateFilmDTO;
import com.streamit.streaming_service.dtos.CreateSeriesDTO;
import com.streamit.streaming_service.model.CatalogModel;

public interface ICatalogService {

	CatalogModel create(CatalogDTO catalogDto);
	CatalogModel findById(UUID id);
	List<CatalogModel> findAll();
	CatalogModel addFilm(UUID id, List<CreateFilmDTO> films);
	CatalogModel removeFilm(UUID id, List<CreateFilmDTO> films);
	CatalogModel addSeries(UUID id, List<CreateSeriesDTO> series);
	CatalogModel removeSeries(UUID id, List<CreateSeriesDTO> series);
	void delete(UUID id);
}
