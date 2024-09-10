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
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.season.ReturnSeasonDTO;
import com.streamit.streaming_service.dtos.season.UpdateSeasonDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.ISeasonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/seasons")
@AllArgsConstructor
public class SeasonController {

    private final ISeasonService seasonService;

    @PostMapping("/{seriesId}")
    public ResponseEntity<ApiResponse<ReturnSeasonDTO>> createSeason(@Valid @RequestBody CreateSeasonDTO createSeasonDTO, @PathVariable("seriesId") UUID seriesId) {
        ReturnSeasonDTO createdSeason = seasonService.create(createSeasonDTO, seriesId);
        ApiResponse<ReturnSeasonDTO> response = ResponseUtil.success(createdSeason, ApiConstants.MESSAGE_RESOURCE_CREATED,
                ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_SEASONS);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnSeasonDTO> getSeasonById(@PathVariable UUID id) {
        ReturnSeasonDTO season = seasonService.findById(id);
        return new ResponseEntity<>(season, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReturnSeasonDTO>> getAllSeasonsFromSeries(UUID seriesId, Pageable pageable) {
        List<ReturnSeasonDTO> seasonsList = seasonService.findAllBySeries(seriesId);
        return new ResponseEntity<>(seasonsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReturnSeasonDTO>> updateSeason(@PathVariable UUID id,
            @RequestBody UpdateSeasonDTO updateSeasonDTO) {
        ReturnSeasonDTO updatedSeason = seasonService.update(id, updateSeasonDTO);
        ApiResponse<ReturnSeasonDTO> response = ResponseUtil.success(updatedSeason, ApiConstants.MESSAGE_RESOURCE_UPDATED,
                ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SEASONS_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSeason(@PathVariable UUID id) {
        seasonService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, ApiConstants.MESSAGE_RESOURCE_DELETED,
                ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SEASONS_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
