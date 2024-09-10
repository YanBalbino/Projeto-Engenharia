package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;

public interface IEpisodeService {

	ReturnEpisodeDTO create(CreateEpisodeDTO episodeDto, UUID idSeason);
	ReturnEpisodeDTO findById(UUID id);
	List<ReturnEpisodeDTO> findAllBySeries(UUID idSeries);
	List<ReturnEpisodeDTO> findAllBySeason(UUID idSeason);
	ReturnEpisodeDTO update(UUID id, UpdateEpisodeDTO episodeDto);
	void delete(UUID id);
}
