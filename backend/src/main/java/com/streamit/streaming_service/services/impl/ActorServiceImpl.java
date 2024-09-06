package com.streamit.streaming_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ActorMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.repositories.ActorRepository;
import com.streamit.streaming_service.services.IActorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements IActorService {

	ActorRepository actorRepository;

	public ActorModel findModelById(UUID id) {
		ActorModel entity = actorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ator não encontrado com id " + id));
		return entity;
	}
	
	@Override
	public ReturnActorDTO findById(UUID id) {
		ActorModel entity = findModelById(id);
		ReturnActorDTO entityDto = ActorMapper.toDto(entity);
		return entityDto;
	}

	@Override
	public List<ActorModel> findAll() {
		List<ActorModel> entities = actorRepository.findAll();
		return entities;
	}

}
