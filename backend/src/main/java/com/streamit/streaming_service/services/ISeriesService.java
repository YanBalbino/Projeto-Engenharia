package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;

public interface ISeriesService {

	ReturnSeriesDTO create(CreateSeriesDTO seriesDto);
	ReturnSeriesDTO findById(UUID id);
	List<ReturnSeriesDTO> findByGenre(String genre, Pageable pageable);
	List<ReturnSeriesDTO> findAll(Pageable pageable);
	ReturnSeriesDTO update(UUID id, UpdateSeriesDTO seriesDto);
	ReturnSeriesDTO addSeason(UUID id, CreateSeasonDTO seasonDto);
	ReturnSeriesDTO addActor(UUID id, CreateActorDTO actorDto);
	void delete(UUID id);
}
