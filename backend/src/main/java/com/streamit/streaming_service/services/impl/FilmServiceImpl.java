package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.FilmMapper;
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

    //supor que filmes sem atores podem ser criados, usando IA, por exemplo
    @Override
    public ReturnFilmDTO create(CreateFilmDTO filmDto) {
    	if(filmRepository.existsByTitle(filmDto.getMedia().getTitulo())) {
    		throw new ResourceAlreadyExistsException("Filme já cadastrado.");
    	}
    	FilmModel entity = new FilmModel();
    	
    	FilmModel entityMapped = FilmMapper.toEntity(filmDto, entity);
    	
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = filmDto.getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorServiceImpl.findModelById(actorId);
    			actors.add(actor);
    		}
    		if(entity.getAtores().isEmpty()) {
    			entity.setAtores(actors);
    		}else {
    			entity.getAtores().addAll(actors);
    		}
    		
    	}
    	ReturnFilmDTO entityDto = FilmMapper.toDto(filmRepository.save(entityMapped));
        return entityDto; 
    }

	@Override
	public ReturnFilmDTO findById(UUID id) {
		FilmModel entity = findModelById(id);
    	ReturnFilmDTO entityDto = FilmMapper.toDto(entity);
        return entityDto; 
	}
	
	public FilmModel findModelById(UUID id) {
		return filmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id " + id));
	}

	@Override
	public List<ReturnFilmDTO> findAll() {
		List<FilmModel> entities = filmRepository.findAll();
		List<ReturnFilmDTO> dtos = new ArrayList<>();
		for(FilmModel entity : entities) {
			ReturnFilmDTO entityDto = FilmMapper.toDto(entity);
			dtos.add(entityDto);
		}
		return dtos;
	}

	@Override
	public ReturnFilmDTO update(UUID id, UpdateFilmDTO filmDto) {
		FilmModel entity = findModelById(id);
		List<FilmModel> entities = filmRepository.findAll();
		for(FilmModel film : entities) {
			if(film.getMedia().getTitulo().equals(filmDto.getMedia().getTitulo()) && !entity.getId().equals(film.getId())) {
				throw new ResourceAlreadyExistsException("Filme já cadastrado com esse título.");
			}
		}
		FilmMapper.toUpdateEntity(filmDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = filmDto.getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorServiceImpl.findModelById(actorId);
    			actors.add(actor);
    		}
    		if(entity.getAtores().isEmpty()) {
    			entity.setAtores(actors);
    		}else {
    			entity.getAtores().addAll(actors);
    		}
    	}
    	ReturnFilmDTO entityDto = FilmMapper.toDto(filmRepository.save(entity));
        return entityDto; 
	}

	@Override
	public void delete(UUID id) {
	    FilmModel film = findModelById(id);
	    filmRepository.delete(film);
	}
	
    public FilmModel getFilmByAudioId(UUID audioId) {
        return filmRepository.findFilmByAudioId(audioId)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado para o áudio com id " + audioId));
    }
    
    public FilmModel getFilmBySubtitleId(UUID audioId) {
    	return filmRepository.findFilmBySubtitleId(audioId)
    			.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado para a legenda com id " + audioId));
    }
}
