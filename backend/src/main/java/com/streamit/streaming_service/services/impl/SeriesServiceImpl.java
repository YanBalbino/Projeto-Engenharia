package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.SeriesDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.MediaMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.repositories.SeriesRepository;
import com.streamit.streaming_service.services.ISeriesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeriesServiceImpl implements ISeriesService {

    private SeriesRepository seriesRepository;
    private ActorServiceImpl actorServiceImpl;

    @Override
    public SeriesModel create(SeriesDTO seriesDto) {
        if (seriesRepository.existsByTitle(seriesDto.getTitulo())) {
            throw new ResourceAlreadyExistsException("Série já cadastrada.");
        }
        SeriesModel entity = new SeriesModel();
        
        SeriesModel entityMapped = MediaMapper.toSeriesEntity(seriesDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = seriesDto.getActorIds();
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
        return seriesRepository.save(entityMapped);
    }


	@Override
	public SeriesModel findById(UUID id) {
		return seriesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Série não encontrada com id " + id));
	}

	@Override
	public List<SeriesModel> findAll() {
		return seriesRepository.findAll();
	}

	@Override
	public SeriesModel update(UUID id, SeriesDTO seriesDto) {
	    SeriesModel entity = findById(id);

	    List<SeriesModel> entities = seriesRepository.findAll();
	    for (SeriesModel series : entities) {
	        if (series.getMedia().getTitulo().equals(seriesDto.getTitulo()) && !entity.getId().equals(series.getId())) {
	            throw new ResourceAlreadyExistsException("Série já cadastrada com esse título.");
	        }
	    }
	    MediaMapper.toSeriesEntity(seriesDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = seriesDto.getActorIds();
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
	    return seriesRepository.save(entity);
	}


	@Override
	public void delete(UUID id) {
		SeriesModel entity = findById(id);
	    if (entity.getAtores() != null) {
	        for (ActorModel actor : entity.getAtores()) {
	            actor.getSerie().remove(entity);
	        }
	    }
	    seriesRepository.delete(entity);
	}
}
