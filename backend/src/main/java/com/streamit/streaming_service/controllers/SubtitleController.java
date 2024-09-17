package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.ISubtitleService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subtitles")
@AllArgsConstructor
public class SubtitleController {

    private final ISubtitleService subtitleService;

    @GetMapping("/{id}")
    public ResponseEntity<ReturnSubtitleDTO> getSubtitleById(@PathVariable UUID id) {
        ReturnSubtitleDTO subtitleDto = subtitleService.findById(id);
        return new ResponseEntity<>(subtitleDto, HttpStatus.OK);
    }
    
    @GetMapping("/films/{filmId}")
    public ResponseEntity<List<ReturnSubtitleDTO>> getAllSubtitleByFilm(@PathVariable UUID filmId) {
    	List<ReturnSubtitleDTO> subtitleDto = subtitleService.findAllByFilm(filmId);
    	return new ResponseEntity<>(subtitleDto, HttpStatus.OK);
    }
    
    @GetMapping("/episodes/{episodeId}")
    public ResponseEntity<List<ReturnSubtitleDTO>> getAllSubtitleBySeries(@PathVariable UUID episodeId) {
    	List<ReturnSubtitleDTO> subtitleDto = subtitleService.findAllByEpisode(episodeId);
    	return new ResponseEntity<>(subtitleDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ReturnSubtitleDTO>> updateSubtitle(@Valid @RequestBody UpdateSubtitleDTO subtitleDto) {

        ReturnSubtitleDTO updatedSubtitle = subtitleService.update(subtitleDto);
        ApiResponse<ReturnSubtitleDTO> response = ResponseUtil.success(
                updatedSubtitle,
                ApiConstants.MESSAGE_RESOURCE_UPDATED,
                ApiConstants.HTTP_STATUS_OK,
                ApiConstants.PATH_SUBTITLES
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteActor(@PathVariable UUID id) {
		subtitleService.delete(id);
		ApiResponse<Void> response = ResponseUtil.success(null, ApiConstants.MESSAGE_RESOURCE_DELETED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_SUBTITLES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
