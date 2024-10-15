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
import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IFilmService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/films")
@AllArgsConstructor
public class FilmController {

	private final IFilmService filmService;

	@PostMapping("/{titulo}")
	public ResponseEntity<ApiResponse<ReturnFilmDTO>> createFilm(@PathVariable String titulo, @Valid @RequestBody CreateFilmDTO createFilmDTO) {
		ReturnFilmDTO createdFilm = filmService.create(titulo, createFilmDTO);
		ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(createdFilm, ApiConstants.MESSAGE_RESOURCE_CREATED,
				ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_FILMS_TITULO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnFilmDTO> getFilmById(@PathVariable UUID id) {
		ReturnFilmDTO film = filmService.findById(id);
		return new ResponseEntity<>(film, HttpStatus.OK);
	}
	
	@GetMapping("/media/{mediaId}")
	public ResponseEntity<ReturnFilmDTO> getFilmByMediaId(@PathVariable UUID mediaId) {
		ReturnFilmDTO film = filmService.findByMedia(mediaId);
		return new ResponseEntity<>(film, HttpStatus.OK);
	}

	@GetMapping("/genre/{profileId}")
	public ResponseEntity<Page<ReturnFilmDTO>> getFilmsByGenre(@RequestParam("genre") String genre, 
	                                                            @PathVariable UUID profileId,
	                                                            @RequestParam(defaultValue = "0") int page, 
	                                                            @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<ReturnFilmDTO> filmsPage = filmService.findByGenre(genre, pageable, profileId);
	    return new ResponseEntity<>(filmsPage, HttpStatus.OK);
	}

	@GetMapping("/{profileId}")
	public ResponseEntity<Page<ReturnFilmDTO>> getAllFilms(@PathVariable UUID profileId, 
	                                                        @RequestParam(defaultValue = "0") int page, 
	                                                        @RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<ReturnFilmDTO> filmsPage = filmService.findAll(pageable, profileId);
	    return new ResponseEntity<>(filmsPage, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ApiResponse<ReturnFilmDTO>> updateFilm(@RequestBody UpdateFilmDTO createFilmDTO) {
		ReturnFilmDTO updatedFilm = filmService.update(createFilmDTO);
		ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, ApiConstants.MESSAGE_RESOURCE_UPDATED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_FILMS_UPDATE_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    @PutMapping("/update/{id}/audio")
    public ResponseEntity<ApiResponse<ReturnFilmDTO>> addAudio(@PathVariable UUID id, 
                                                                   @Valid @RequestBody CreateAudioDTO audioDTO) {
    	ReturnFilmDTO updatedFilm = filmService.addAudio(id, audioDTO);
        ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, 
                                                                      ApiConstants.MESSAGE_RESOURCE_ADDED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_FILMS_UPDATE_ID_AUDIO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}/subtitle")
    public ResponseEntity<ApiResponse<ReturnFilmDTO>> addSubtitle(@PathVariable UUID id, 
                                                                      @Valid @RequestBody CreateSubtitleDTO subtitleDTO) {
    	ReturnFilmDTO updatedFilm = filmService.addSubtitle(id, subtitleDTO);
        ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, 
                                                                      ApiConstants.MESSAGE_RESOURCE_ADDED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_FILMS_UPDATE_ID_SUBTITLE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}/actor")
    public ResponseEntity<ApiResponse<ReturnFilmDTO>> addActor(@PathVariable UUID id, 
    		@Valid @RequestBody CreateActorDTO actorDto) {
    	ReturnFilmDTO updatedFilm = filmService.addActor(id, actorDto);
    	ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, 
    			ApiConstants.MESSAGE_RESOURCE_ADDED, 
    			ApiConstants.HTTP_STATUS_OK, 
    			ApiConstants.PATH_FILMS_UPDATE_ID_ACTOR);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteFilm(@PathVariable UUID id) {
		filmService.delete(id);
		ApiResponse<Void> response = ResponseUtil.success(null, ApiConstants.MESSAGE_RESOURCE_DELETED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_FILMS_DELETE_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
