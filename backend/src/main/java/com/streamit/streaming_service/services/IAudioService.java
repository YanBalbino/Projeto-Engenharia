package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;

public interface IAudioService {
	ReturnAudioDTO findById(UUID id);
	List<ReturnAudioDTO> findAllByFilm(UUID filmId);
	List<ReturnAudioDTO> findAllByEpisode(UUID seriesId);
	ReturnAudioDTO update (UpdateAudioDTO subtitleDto);
	void delete(UUID id);
}
