package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;

public interface ISubtitleService {

	ReturnSubtitleDTO findById(UUID id);
	List<ReturnSubtitleDTO> findAllByFilm(UUID filmId);
	List<ReturnSubtitleDTO> findAllByEpisode(UUID seriesId);
	ReturnSubtitleDTO update (UpdateSubtitleDTO subtitleDto);
	void delete(UUID id);
}
