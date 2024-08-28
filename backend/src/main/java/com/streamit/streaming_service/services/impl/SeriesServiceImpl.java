package com.streamit.streaming_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreateSeriesDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.SeriesMapper;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.repositories.SeriesRepository;
import com.streamit.streaming_service.services.ISeriesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeriesServiceImpl implements ISeriesService {

    private SeriesRepository seriesRepository;

	@Override
	public SeriesModel create(CreateSeriesDTO seriesDto) {
		if(seriesRepository.findByVideoUrl(seriesDto.getVideoURL()) != null) {
    		throw new ResourceAlreadyExistsException("Série já cadastrada.");
    	}
		SeriesModel seriesModel = SeriesMapper.toModel(seriesDto);
        return seriesRepository.save(seriesModel); 
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
	public SeriesModel update(UUID id, CreateSeriesDTO seriesDto) {
		findById(id);
		return create(seriesDto);
	}

	@Override
	public void delete(UUID id) {
		SeriesModel entity = findById(id);
		seriesRepository.delete(entity);
	}
}
