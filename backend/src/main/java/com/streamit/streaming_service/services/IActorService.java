package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;

public interface IActorService {

	List<ReturnActorDTO> findAll();
	ReturnActorDTO findById(UUID id);
	ReturnActorDTO update(UUID id, UpdateActorDTO dto);
	void delete(UUID id);
}
