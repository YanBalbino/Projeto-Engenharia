package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;

public interface IFilmService {

	ReturnFilmDTO create(CreateFilmDTO filmDto);
	ReturnFilmDTO findById(UUID id);
	List<ReturnFilmDTO> findByGenre(String genre, Pageable pageable);
	List<ReturnFilmDTO> findAll(Pageable pageable);
	ReturnFilmDTO update(UUID id, UpdateFilmDTO filmDto);
	void delete(UUID id);
}
