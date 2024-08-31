package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.FilmDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.MediaMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.repositories.FilmRepository;
import com.streamit.streaming_service.services.IFilmService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements IFilmService {

    private FilmRepository filmRepository;
    private ActorServiceImpl actorServiceImpl;

    //supor que filmes sem atores podem ser criado, uasndo IA, por exemplo
    @Override
    public FilmModel create(FilmDTO filmDto) {
    	if(filmRepository.existsByTitle(filmDto.getTitulo())) {
    		throw new ResourceAlreadyExistsException("Filme já cadastrado.");
    	}
    	FilmModel entity = new FilmModel();
    	
    	FilmModel entityMapped = MediaMapper.toFilmEntity(filmDto, entity);
    	
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = filmDto.getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorServiceImpl.findById(actorId);
    			actors.add(actor);
    		}
    		if(entity.getAtores().isEmpty()) {
    			entity.setAtores(actors);
    		}else {
    			entity.getAtores().addAll(actors);
    		}
    		
    	}
        return filmRepository.save(entityMapped); 
    }

	@Override
	public FilmModel findById(UUID id) {
		return filmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id " + id));
	}

	@Override
	public List<FilmModel> findAll() {
		return filmRepository.findAll();
	}

	@Override
	public FilmModel update(UUID id, FilmDTO filmDto) {
		FilmModel entity = findById(id);
		List<FilmModel> entities = filmRepository.findAll();
		for(FilmModel film : entities) {
			if(film.getMedia().getTitulo().equals(filmDto.getTitulo()) && !entity.getId().equals(film.getId())) {
				throw new ResourceAlreadyExistsException("Filme já cadastrado com esse título.");
			}
		}
		MediaMapper.toFilmEntity(filmDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = filmDto.getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorServiceImpl.findById(actorId);
    			actors.add(actor);
    		}
    		if(entity.getAtores().isEmpty()) {
    			entity.setAtores(actors);
    		}else {
    			entity.getAtores().addAll(actors);
    		}
    	}
    	return filmRepository.save(entity);
	}

	@Override
	public void delete(UUID id) {
	    FilmModel film = findById(id);
	    if (film.getAtores() != null) {
	        for (ActorModel actor : film.getAtores()) {
	            actor.getFilme().remove(film);
	        }
	    }
	    filmRepository.delete(film);
	}

}
