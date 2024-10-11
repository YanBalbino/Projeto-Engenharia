package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.model.ActorModel;

public interface IActorService {

	ReturnActorDTO findById(UUID id);
	Page<ReturnActorDTO> findByName(String name, Pageable pageable);
	ReturnActorDTO update(UpdateActorDTO dto);
	void delete(UUID id);
	ActorModel findModelById(UUID actorId);
	List<ReturnActorDTO> findActorsByMediaId(UUID mediaId);
}
