package com.streamit.streaming_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreateFilmDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.FilmMapper;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.repositories.FilmRepository;
import com.streamit.streaming_service.services.IFilmService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements IFilmService {

    private FilmRepository filmRepository;

    @Override
    public FilmModel create(CreateFilmDTO filmDto) {
    	if(filmRepository.findByVideoUrl(filmDto.getVideoURL()) != null) {
    		throw new ResourceAlreadyExistsException("Filme já cadastrado.");
    	}
    	FilmModel entity = FilmMapper.toModel(filmDto, new FilmModel());
        return filmRepository.save(entity); 
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
	public FilmModel update(UUID id, CreateFilmDTO filmDto) {
		FilmModel entity = findById(id);
		List<FilmModel> entities = filmRepository.findAll();
		for(FilmModel film : entities) {
			if(film.getVideoUrl().equals(filmDto.getVideoURL()) && !entity.getId().equals(film.getId())) {
				throw new ResourceAlreadyExistsException("Filme já cadastrado.");
			}
		}
		FilmMapper.toModel(filmDto, entity);
    	return filmRepository.save(entity);
	}

	@Override
	public void delete(UUID id) {
		FilmModel entity = findById(id);
		filmRepository.delete(entity);
	}
}
