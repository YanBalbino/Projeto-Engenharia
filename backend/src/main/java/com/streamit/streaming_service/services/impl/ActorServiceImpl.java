package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ActorMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.SeriesModel;
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
    public List<ReturnActorDTO> findByName(String nome, Pageable pageable) {
        Page<ActorModel> actorList = actorRepository.findByNomeContainingIgnoreCase(nome, pageable);
        List<ReturnActorDTO> dtoList = new ArrayList<>();
        for (ActorModel actor : actorList.getContent()) {
            ReturnActorDTO dto = ActorMapper.toDto(actor);
            dtoList.add(dto);
        }
        return dtoList;
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
	    if (entity.getFilme() != null) {
	        for (FilmModel film : entity.getFilme()) {
	        	film.getAtores().remove(entity);
	        }
	    }
	    if (entity.getSerie() != null) {
	    	for (SeriesModel series: entity.getSerie()) {
	    		series.getAtores().remove(entity);
	    	}
	    }
	    actorRepository.delete(entity);
	}
	
	@Override
    public List<ReturnActorDTO> findAllByFilm(UUID filmId) {
        List<ActorModel> entities = actorRepository.findActorsByFilmId(filmId);
        List<ReturnActorDTO> dtos = new ArrayList<>();
        for (ActorModel entity : entities) {
            dtos.add(ActorMapper.toDto(entity));
        }
        return dtos;
    }

	@Override
    public List<ReturnActorDTO> findAllBySeries(UUID seriesId) {
        List<ActorModel> entities = actorRepository.findActorsBySeriesId(seriesId);
        List<ReturnActorDTO> dtos = new ArrayList<>();
        for (ActorModel entity : entities) {
            dtos.add(ActorMapper.toDto(entity));
        }
        return dtos;
    }

}
