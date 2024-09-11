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
import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IEpisodeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/episodes")
@AllArgsConstructor
public class EpisodeController {

    private final IEpisodeService episodeService;

    @PostMapping("/{seasonId}")
    public ResponseEntity<ApiResponse<ReturnEpisodeDTO>> createEpisode(@Valid @RequestBody CreateEpisodeDTO createEpisodeDTO, 
                                                                       @PathVariable UUID seasonId) {
        ReturnEpisodeDTO createdEpisode = episodeService.create(createEpisodeDTO, seasonId);
        ApiResponse<ReturnEpisodeDTO> response = ResponseUtil.success(createdEpisode, 
                                                                      ApiConstants.MESSAGE_RESOURCE_CREATED, 
                                                                      ApiConstants.HTTP_STATUS_CREATED, 
                                                                      ApiConstants.PATH_EPISODES_ID);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnEpisodeDTO> getEpisodeById(@PathVariable UUID id) {
        ReturnEpisodeDTO episode = episodeService.findById(id);
        return new ResponseEntity<>(episode, HttpStatus.OK);
    }

    @GetMapping("/seasons/{seasonId}")
    public ResponseEntity<List<ReturnEpisodeDTO>> getAllEpisodesBySeason(@PathVariable UUID seasonId) {
        List<ReturnEpisodeDTO> episodes = episodeService.findAllBySeason(seasonId);
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<ReturnEpisodeDTO>> getAllEpisodesBySeries(@PathVariable UUID seriesId) {
        List<ReturnEpisodeDTO> episodes = episodeService.findAllBySeries(seriesId);
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReturnEpisodeDTO>> updateEpisode(@PathVariable UUID id, 
                                                                       @Valid @RequestBody UpdateEpisodeDTO updateEpisodeDTO) {
        ReturnEpisodeDTO updatedEpisode = episodeService.update(id, updateEpisodeDTO);
        ApiResponse<ReturnEpisodeDTO> response = ResponseUtil.success(updatedEpisode, 
                                                                      ApiConstants.MESSAGE_RESOURCE_UPDATED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_EPISODES_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/audio")
    public ResponseEntity<ApiResponse<ReturnEpisodeDTO>> addAudio(@PathVariable UUID id, 
                                                                   @Valid @RequestBody CreateAudioDTO audioDTO) {
        ReturnEpisodeDTO updatedEpisode = episodeService.addAudio(id, audioDTO);
        ApiResponse<ReturnEpisodeDTO> response = ResponseUtil.success(updatedEpisode, 
                                                                      ApiConstants.MESSAGE_RESOURCE_ADDED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_EPISODES_ID_AUDIO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/subtitle")
    public ResponseEntity<ApiResponse<ReturnEpisodeDTO>> addSubtitle(@PathVariable UUID id, 
                                                                      @Valid @RequestBody CreateSubtitleDTO subtitleDTO) {
        ReturnEpisodeDTO updatedEpisode = episodeService.addSubtitle(id, subtitleDTO);
        ApiResponse<ReturnEpisodeDTO> response = ResponseUtil.success(updatedEpisode, 
                                                                      ApiConstants.MESSAGE_RESOURCE_ADDED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_EPISODES_ID_SUBTITLE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEpisode(@PathVariable UUID id) {
        episodeService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, 
                                                          ApiConstants.MESSAGE_RESOURCE_DELETED, 
                                                          ApiConstants.HTTP_STATUS_OK, 
                                                          ApiConstants.PATH_EPISODES_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
