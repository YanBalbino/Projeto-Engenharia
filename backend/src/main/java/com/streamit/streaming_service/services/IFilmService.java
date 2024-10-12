package com.streamit.streaming_service.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.model.FilmModel;

public interface IFilmService {

	ReturnFilmDTO create(String titulo, CreateFilmDTO filmDto);
	ReturnFilmDTO findById(UUID id);
	Page<ReturnFilmDTO> findByGenre(String genre, Pageable pageable, UUID profileId);
	Page<ReturnFilmDTO> findAll(Pageable pageable, UUID profileId);
	ReturnFilmDTO findByMedia(UUID mediaId);
	ReturnFilmDTO update(UpdateFilmDTO filmDto);
	ReturnFilmDTO addAudio(UUID id, CreateAudioDTO audioDto);
	ReturnFilmDTO addSubtitle(UUID id, CreateSubtitleDTO subtitleDto);
	ReturnFilmDTO addActor(UUID id, CreateActorDTO actorDto);
	void delete(UUID id);
	FilmModel getFilmByAudioId(UUID id);
	FilmModel getFilmBySubtitleId(UUID id);
}
