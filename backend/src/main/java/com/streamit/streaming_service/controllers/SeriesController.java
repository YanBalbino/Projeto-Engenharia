package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.ISeriesService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/series")
@AllArgsConstructor
public class SeriesController {

	private final ISeriesService seriesService;

	@PostMapping
	public ResponseEntity<ApiResponse<ReturnSeriesDTO>> createSeries(
			@Valid @RequestBody CreateSeriesDTO createSeriesDTO) {
		ReturnSeriesDTO createdSeries = seriesService.create(createSeriesDTO);
		ApiResponse<ReturnSeriesDTO> response = ResponseUtil.success(createdSeries,
				ApiConstants.MESSAGE_RESOURCE_CREATED, ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_SERIES);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnSeriesDTO> getSeriesById(@PathVariable UUID id) {
		ReturnSeriesDTO series = seriesService.findById(id);
		return new ResponseEntity<>(series, HttpStatus.OK);
	}

	@GetMapping("/genre")
	public ResponseEntity<List<ReturnSeriesDTO>> getSeriesByGenre(@RequestParam("genre") String genre,
			Pageable pageable) {
		List<ReturnSeriesDTO> seriesList = seriesService.findByGenre(genre, pageable);
		return new ResponseEntity<>(seriesList, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ReturnSeriesDTO>> getAllSeries(Pageable pageable) {
		List<ReturnSeriesDTO> seriesList = seriesService.findAll(pageable);
		return new ResponseEntity<>(seriesList, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ReturnSeriesDTO>> updateSeries(@PathVariable UUID id,
			@RequestBody UpdateSeriesDTO createSeriesDTO) {
		ReturnSeriesDTO updatedSeries = seriesService.update(id, createSeriesDTO);
		ApiResponse<ReturnSeriesDTO> response = ResponseUtil.success(updatedSeries,
				ApiConstants.MESSAGE_RESOURCE_UPDATED, ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SERIES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteSeries(@PathVariable UUID id) {
		seriesService.delete(id);
		ApiResponse<Void> response = ResponseUtil.success(null, ApiConstants.MESSAGE_RESOURCE_DELETED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SERIES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
