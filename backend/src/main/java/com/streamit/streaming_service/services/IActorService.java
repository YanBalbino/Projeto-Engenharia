package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.model.ActorModel;

public interface IActorService {

	List<ActorModel> findAll();
	ActorModel findById(UUID id);
}
