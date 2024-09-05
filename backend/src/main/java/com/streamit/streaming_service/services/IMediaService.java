package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.media.CreateMediaDTO;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;

public interface IMediaService {

	ReturnMediaDTO findModelById(UUID id);
	List<ReturnMediaDTO> findAll();
	ReturnMediaDTO update(UUID id, CreateMediaDTO mediaDto);
}
