package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;

public interface IActorService {

	ReturnActorDTO findById(UUID id);
	List<ReturnActorDTO> findByName(String name, Pageable pageable);
	ReturnActorDTO update(UpdateActorDTO dto);
	void delete(UUID id);
}
