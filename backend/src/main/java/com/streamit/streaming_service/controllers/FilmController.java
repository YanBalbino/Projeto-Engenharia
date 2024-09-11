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

	@PostMapping
	public ResponseEntity<ApiResponse<ReturnFilmDTO>> createFilm(@Valid @RequestBody CreateFilmDTO createFilmDTO) {
		ReturnFilmDTO createdFilm = filmService.create(createFilmDTO);
		ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(createdFilm, ApiConstants.MESSAGE_RESOURCE_CREATED,
				ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_FILMS);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnFilmDTO> getFilmById(@PathVariable UUID id) {
		ReturnFilmDTO film = filmService.findById(id);
		return new ResponseEntity<>(film, HttpStatus.OK);
	}

	@GetMapping("/genre")
	public ResponseEntity<List<ReturnFilmDTO>> getFilmsByGenre(@RequestParam("genre") String genre, Pageable pageable) {
		List<ReturnFilmDTO> filmsList = filmService.findByGenre(genre, pageable);
		return new ResponseEntity<>(filmsList, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ReturnFilmDTO>> getAllFilms(Pageable pageable) {
		List<ReturnFilmDTO> filmsList = filmService.findAll(pageable);
		return new ResponseEntity<>(filmsList, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ReturnFilmDTO>> updateFilm(@PathVariable UUID id,
			@RequestBody UpdateFilmDTO createFilmDTO) {
		ReturnFilmDTO updatedFilm = filmService.update(id, createFilmDTO);
		ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, ApiConstants.MESSAGE_RESOURCE_UPDATED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_FILMS_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    @PutMapping("/{id}/audio")
    public ResponseEntity<ApiResponse<ReturnFilmDTO>> addAudio(@PathVariable UUID id, 
                                                                   @Valid @RequestBody CreateAudioDTO audioDTO) {
    	ReturnFilmDTO updatedFilm = filmService.addAudio(id, audioDTO);
        ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, 
                                                                      ApiConstants.MESSAGE_RESOURCE_ADDED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_FILMS_ID_AUDIO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/subtitle")
    public ResponseEntity<ApiResponse<ReturnFilmDTO>> addSubtitle(@PathVariable UUID id, 
                                                                      @Valid @RequestBody CreateSubtitleDTO subtitleDTO) {
    	ReturnFilmDTO updatedFilm = filmService.addSubtitle(id, subtitleDTO);
        ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, 
                                                                      ApiConstants.MESSAGE_RESOURCE_ADDED, 
                                                                      ApiConstants.HTTP_STATUS_OK, 
                                                                      ApiConstants.PATH_FILMS_ID_SUBTITLE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/actor")
    public ResponseEntity<ApiResponse<ReturnFilmDTO>> addActor(@PathVariable UUID id, 
    		@Valid @RequestBody CreateActorDTO actorDto) {
    	ReturnFilmDTO updatedFilm = filmService.addActor(id, actorDto);
    	ApiResponse<ReturnFilmDTO> response = ResponseUtil.success(updatedFilm, 
    			ApiConstants.MESSAGE_RESOURCE_ADDED, 
    			ApiConstants.HTTP_STATUS_OK, 
    			ApiConstants.PATH_FILMS_ID_ACTOR);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteFilm(@PathVariable UUID id) {
		filmService.delete(id);
		ApiResponse<Void> response = ResponseUtil.success(null, ApiConstants.MESSAGE_RESOURCE_DELETED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_FILMS_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
