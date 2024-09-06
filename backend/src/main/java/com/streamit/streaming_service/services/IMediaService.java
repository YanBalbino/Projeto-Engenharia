package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;

public interface IMediaService {

	ReturnMediaDTO findById(UUID id);
	List<ReturnMediaDTO> findAll();
	ReturnMediaDTO update(UUID id, UpdateMediaDTO mediaDto);
}
