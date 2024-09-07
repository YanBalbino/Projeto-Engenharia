package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.SeriesMapper;
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
    public ReturnSeriesDTO create(CreateSeriesDTO seriesDto) {
        if (seriesRepository.existsByTitle(seriesDto.getMedia().getTitulo())) {
            throw new ResourceAlreadyExistsException("Série já cadastrada.");
        }
        SeriesModel entity = new SeriesModel();
        
        SeriesModel entityMapped = SeriesMapper.toEntity(seriesDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = seriesDto.getActorIds();
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
    	ReturnSeriesDTO entityDto = SeriesMapper.toDto(seriesRepository.save(entityMapped));
        return entityDto; 
    }
    
    public SeriesModel findModelById(UUID id) {
    	return seriesRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Série não encontrada com id " + id));
    }

	@Override
	public ReturnSeriesDTO findById(UUID id) {
		SeriesModel entity = findModelById(id);
		ReturnSeriesDTO entityDto = SeriesMapper.toDto(entity);
        return entityDto; 
	}

	@Override
	public List<ReturnSeriesDTO> findAll() {
		List<SeriesModel> entities = seriesRepository.findAll();
		List<ReturnSeriesDTO> dtos = new ArrayList<>();
		for(SeriesModel entity : entities) {
			ReturnSeriesDTO entityDto = SeriesMapper.toDto(entity);
			dtos.add(entityDto);
		}
		return dtos;
	}

	@Override
	public ReturnSeriesDTO update(UUID id, UpdateSeriesDTO seriesDto) {
	    SeriesModel entity = findModelById(id);

	    List<SeriesModel> entities = seriesRepository.findAll();
	    for (SeriesModel series : entities) {
	        if (series.getMedia().getTitulo().equals(seriesDto.getMedia().getTitulo()) && !entity.getId().equals(series.getId())) {
	            throw new ResourceAlreadyExistsException("Série já cadastrada com esse título.");
	        }
	    }
	    SeriesMapper.toUpdateEntity(seriesDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = seriesDto.getActorIds();
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
    	ReturnSeriesDTO entityDto = SeriesMapper.toDto(seriesRepository.save(entity));
        return entityDto; 
	}

	@Override
	public void delete(UUID id) {
		SeriesModel entity = findModelById(id);
	    seriesRepository.delete(entity);
	}
}
