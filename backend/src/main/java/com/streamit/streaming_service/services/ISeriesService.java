package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;

public interface ISeriesService {

	ReturnSeriesDTO create(CreateSeriesDTO seriesDto);
	ReturnSeriesDTO findById(UUID id);
	List<ReturnSeriesDTO> findAll();
	ReturnSeriesDTO update(UUID id, UpdateSeriesDTO seriesDto);
	void delete(UUID id);
}
