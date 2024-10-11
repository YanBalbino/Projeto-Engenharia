package com.streamit.streaming_service.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
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

	@GetMapping("/media/{mediaId}")
	public ResponseEntity<ReturnSeriesDTO> getFilmByMediaId(@PathVariable UUID mediaId) {
		ReturnSeriesDTO series = seriesService.findByMedia(mediaId);
		return new ResponseEntity<>(series, HttpStatus.OK);
	}

	@GetMapping("/genre/{profileId}")
	public ResponseEntity<Page<ReturnSeriesDTO>> getSeriesByGenre(UUID profileId, @RequestParam("genre") String genre,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ReturnSeriesDTO> seriesList = seriesService.findByGenre(profileId, genre, pageable);
		return new ResponseEntity<>(seriesList, HttpStatus.OK);
	}

	@GetMapping("/{profileId}")
	public ResponseEntity<Page<ReturnSeriesDTO>> getAllSeries(UUID profileId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ReturnSeriesDTO> seriesList = seriesService.findAll(profileId, pageable);
		return new ResponseEntity<>(seriesList, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ApiResponse<ReturnSeriesDTO>> updateSeries(@RequestBody UpdateSeriesDTO createSeriesDTO) {
		ReturnSeriesDTO updatedSeries = seriesService.update(createSeriesDTO);
		ApiResponse<ReturnSeriesDTO> response = ResponseUtil.success(updatedSeries,
				ApiConstants.MESSAGE_RESOURCE_UPDATED, ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SERIES);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}/season")
	public ResponseEntity<ApiResponse<ReturnSeriesDTO>> addSeason(@PathVariable UUID id,
			@Valid @RequestBody CreateSeasonDTO seasonDto) {
		ReturnSeriesDTO updatedSeries = seriesService.addSeason(id, seasonDto);
		ApiResponse<ReturnSeriesDTO> response = ResponseUtil.success(updatedSeries, ApiConstants.MESSAGE_RESOURCE_ADDED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SERIES_ID_SEASON);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}/actor")
	public ResponseEntity<ApiResponse<ReturnSeriesDTO>> addActor(@PathVariable UUID id,
			@Valid @RequestBody CreateActorDTO actorDto) {
		ReturnSeriesDTO updatedSeries = seriesService.addActor(id, actorDto);
		ApiResponse<ReturnSeriesDTO> response = ResponseUtil.success(updatedSeries, ApiConstants.MESSAGE_RESOURCE_ADDED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SERIES_ID_ACTOR);
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
