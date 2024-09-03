package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;

public interface IFilmService {

	ReturnFilmDTO create(CreateFilmDTO filmDto);
	ReturnFilmDTO findById(UUID id);
	List<ReturnFilmDTO> findAll();
	ReturnFilmDTO update(UUID id, UpdateFilmDTO filmDto);
	void delete(UUID id);
}
