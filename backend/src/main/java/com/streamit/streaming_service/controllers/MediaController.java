package com.streamit.streaming_service.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// Busca todos com paginação
	@GetMapping("/profiles/{profileId}")
	public ResponseEntity<Page<ReturnMediaDTO>> getAllMedia(@PathVariable UUID profileId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<ReturnMediaDTO> mediaPage = mediaService.findAll(profileId, pageable);
	    return new ResponseEntity<>(mediaPage, HttpStatus.OK);
	}

	// Busca por ator com paginação
	@GetMapping("/actor/profiles/{profileId}")
	public ResponseEntity<Page<ReturnMediaDTO>> searchByActor(@RequestParam("nomeAtor") String nomeAtor,
	        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, UUID profileId) {

	    Pageable pageable = PageRequest.of(page, size);
	    Page<ReturnMediaDTO> catalog = mediaService.findMediaByActorName(nomeAtor, pageable, profileId);
	    return ResponseEntity.ok(catalog);
	}

	// Busca por título com paginação
	@GetMapping("/title/profiles/{profileId}")
	public ResponseEntity<Page<ReturnMediaDTO>> searchByTitle(@RequestParam("titulo") String titulo,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, UUID profileId) {

		Pageable pageable = PageRequest.of(page, size);
		Page<ReturnMediaDTO> catalog = mediaService.findMediaByTitle(titulo, pageable, profileId);
		return ResponseEntity.ok(catalog);
	}

	// Busca por gênero com paginação
	@GetMapping("/genre/profiles/{profileId}")
	public ResponseEntity<Page<ReturnMediaDTO>> searchByGenre(@RequestParam("genero") String genero,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, UUID profileId) {

		Pageable pageable = PageRequest.of(page, size);
		Page<ReturnMediaDTO> catalog = mediaService.findMediaByGenre(genero, pageable, profileId);
		return ResponseEntity.ok(catalog);
	}

	// Busca por diretor com paginação
	@GetMapping("/director/profiles/{profileId}")
	public ResponseEntity<Page<ReturnMediaDTO>> searchByDirector(@RequestParam("diretor") String diretor,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, UUID profileId) {

		Pageable pageable = PageRequest.of(page, size);
		Page<ReturnMediaDTO> catalog = mediaService.findMediaByDirector(diretor, pageable, profileId);
		return ResponseEntity.ok(catalog);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnMediaDTO> getMediaById(@PathVariable UUID id) {
		ReturnMediaDTO mediaDto = mediaService.findById(id);
		return new ResponseEntity<>(mediaDto, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ApiResponse<ReturnMediaDTO>> updateMedia(@Valid @RequestBody UpdateMediaDTO mediaDto) {

		ReturnMediaDTO updatedMedia = mediaService.update(mediaDto);
		ApiResponse<ReturnMediaDTO> response = ResponseUtil.success(updatedMedia, ApiConstants.MESSAGE_RESOURCE_UPDATED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_MEDIAS_UPDATE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
