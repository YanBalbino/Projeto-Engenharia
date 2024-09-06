package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.model.ActorModel;

public interface IActorService {

	List<ActorModel> findAll();
	ReturnActorDTO findById(UUID id);
	
}
