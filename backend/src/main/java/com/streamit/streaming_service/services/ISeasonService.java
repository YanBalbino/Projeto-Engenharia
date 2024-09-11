package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.season.ReturnSeasonDTO;
import com.streamit.streaming_service.dtos.season.UpdateSeasonDTO;

public interface ISeasonService {

	ReturnSeasonDTO create(CreateSeasonDTO seasonDto, UUID idSeries);
	ReturnSeasonDTO findById(UUID id);
	List<ReturnSeasonDTO> findAllBySeries(UUID seriesId);
	ReturnSeasonDTO update(UUID id, UpdateSeasonDTO seasonDto);
	ReturnSeasonDTO addEpisode(UUID id, CreateEpisodeDTO episodeDto);
	void delete(UUID id);
}
