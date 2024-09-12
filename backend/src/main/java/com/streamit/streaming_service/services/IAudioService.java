package com.streamit.streaming_service.services;

import java.util.UUID;

import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;

public interface IAudioService {
	ReturnAudioDTO findById(UUID id);
	ReturnAudioDTO update (UpdateAudioDTO subtitleDto);
	void delete(UUID id);
}
