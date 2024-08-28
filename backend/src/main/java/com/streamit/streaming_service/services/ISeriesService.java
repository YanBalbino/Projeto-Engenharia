package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CreateSeriesDTO;
import com.streamit.streaming_service.model.SeriesModel;

public interface ISeriesService {

	SeriesModel create(CreateSeriesDTO seriesDto);
	SeriesModel findById(UUID id);
	List<SeriesModel> findAll();
	SeriesModel update(UUID id, CreateSeriesDTO seriesDto);
	void delete(UUID id);
}
