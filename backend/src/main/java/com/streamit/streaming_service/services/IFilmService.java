package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.FilmDTO;
import com.streamit.streaming_service.dtos.UpdateFilmDTO;
import com.streamit.streaming_service.model.FilmModel;

public interface IFilmService {

	FilmModel create(FilmDTO filmDto);
	FilmModel findById(UUID id);
	List<FilmModel> findAll();
	FilmModel update(UUID id, UpdateFilmDTO filmDto);
	void delete(UUID id);
}
