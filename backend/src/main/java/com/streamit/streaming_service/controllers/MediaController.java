package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IMediaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/medias")
@AllArgsConstructor
public class MediaController {

	private final IMediaService mediaService;

	@GetMapping("/{id}")
	public ResponseEntity<ReturnMediaDTO> getMediaById(@PathVariable UUID id) {
		ReturnMediaDTO mediaDto = mediaService.findModelById(id);
		return new ResponseEntity<>(mediaDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ReturnMediaDTO>> getAllMedia() {
		List<ReturnMediaDTO> mediaList = mediaService.findAll();
		return new ResponseEntity<>(mediaList, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ReturnMediaDTO>> updateMedia(@PathVariable UUID id,
			@Valid @RequestBody UpdateMediaDTO mediaDto) {

		ReturnMediaDTO updatedMedia = mediaService.update(id, mediaDto);
		ApiResponse<ReturnMediaDTO> response = ResponseUtil.success(updatedMedia, ApiConstants.MESSAGE_RESOURCE_UPDATED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_MEDIA_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
