package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ActorMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.MediaModel;
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
	public Page<ReturnActorDTO> findByName(String nome, Pageable pageable) {
	    Page<ActorModel> actorPage = actorRepository.findByNomeContainingIgnoreCase(nome, pageable);
	    
	    List<ReturnActorDTO> dtoList = actorPage.getContent().stream()
	        .map(ActorMapper::toDto)  // Mapeia cada ActorModel para ReturnActorDTO
	        .collect(Collectors.toList());

	    return new PageImpl<>(dtoList, pageable, actorPage.getTotalElements());
	}

	@Override
	public ReturnActorDTO update(UpdateActorDTO actorDto) {
		ActorModel entity = findModelById(actorDto.getId());
        
        List<ActorModel> entities = actorRepository.findAll();
        for (ActorModel actor : entities) {
            if (actor.getImagemUrl().equals(actorDto.getImagemUrl()) && !entity.getId().equals(actor.getId())) {
                throw new ResourceAlreadyExistsException("URL de imagem de ator já cadastrada.");
            }
        }
        
        ActorMapper.toUpdateEntity(actorDto, entity);
        
        ActorModel updatedActor = actorRepository.save(entity);

        return ActorMapper.toDto(updatedActor);
	}

	@Override
	public void delete(UUID id) {
		ActorModel entity = findModelById(id);
	    if (entity.getMidias() != null) {
	        for (MediaModel media : entity.getMidias()) {
	        	media.getAtores().remove(entity);
	        }
	    }
	    actorRepository.delete(entity);
	}
	
	@Override
    public List<ReturnActorDTO> findActorsByMediaId(UUID filmId) {
        List<ActorModel> entities = actorRepository.findActorsByMediaId(filmId);
        List<ReturnActorDTO> dtos = new ArrayList<>();
        for (ActorModel entity : entities) {
            dtos.add(ActorMapper.toDto(entity));
        }
        return dtos;
    }

}
