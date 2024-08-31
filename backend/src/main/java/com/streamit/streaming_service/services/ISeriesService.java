package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.SeriesDTO;
import com.streamit.streaming_service.model.SeriesModel;

public interface ISeriesService {

	SeriesModel create(SeriesDTO seriesDto);
	SeriesModel findById(UUID id);
	List<SeriesModel> findAll();
	SeriesModel update(UUID id, SeriesDTO seriesDto);
	void delete(UUID id);
}
