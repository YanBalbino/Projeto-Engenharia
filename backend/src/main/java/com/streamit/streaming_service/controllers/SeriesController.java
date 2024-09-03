package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;
import com.streamit.streaming_service.model.SeriesModel;
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
    public ResponseEntity<ApiResponse<SeriesModel>> createSeries(@Valid @RequestBody CreateSeriesDTO createSeriesDTO) {
        SeriesModel createdSeries = seriesService.create(createSeriesDTO);
        ApiResponse<SeriesModel> response = ResponseUtil.success(createdSeries, 
                ApiConstants.MESSAGE_RESOURCE_CREATED, 
                ApiConstants.HTTP_STATUS_CREATED, 
                ApiConstants.PATH_SERIES);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesModel> getSeriesById(@PathVariable UUID id) {
        SeriesModel series = seriesService.findById(id);
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SeriesModel>> getAllSeries() {
        List<SeriesModel> seriesList = seriesService.findAll();
        return new ResponseEntity<>(seriesList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SeriesModel>> updateSeries(@PathVariable UUID id, 
                                                                 @RequestBody UpdateSeriesDTO createSeriesDTO) {
        SeriesModel updatedSeries = seriesService.update(id, createSeriesDTO);
        ApiResponse<SeriesModel> response = ResponseUtil.success(updatedSeries, 
                ApiConstants.MESSAGE_RESOURCE_UPDATED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_SERIES_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSeries(@PathVariable UUID id) {
        seriesService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, 
                ApiConstants.MESSAGE_RESOURCE_DELETED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_SERIES_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
