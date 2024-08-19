package com.streamit.streaming_service.services;

import com.streamit.streaming_service.dtos.FilmDTO;

public interface IFilmService {

	FilmDTO create(FilmDTO filmDto);
}
