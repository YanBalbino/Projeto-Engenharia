package com.streamit.streaming_service.services;

import java.util.UUID;

import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;

public interface ISubtitleService {

	ReturnSubtitleDTO findById(UUID id);
	ReturnSubtitleDTO update (UpdateSubtitleDTO subtitleDto);
	void delete(UUID id);
}
