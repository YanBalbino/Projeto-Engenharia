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
import com.streamit.streaming_service.dtos.CreateFilmDTO;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IFilmService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/films")
@AllArgsConstructor
public class FilmController {

    private final IFilmService filmService;

    @PostMapping
    public ResponseEntity<ApiResponse<FilmModel>> createFilm(@RequestBody CreateFilmDTO createFilmDTO) {
        FilmModel createdFilm = filmService.create(createFilmDTO);
        ApiResponse<FilmModel> response = ResponseUtil.success(createdFilm, 
                ApiConstants.MESSAGE_RESOURCE_CREATED, 
                ApiConstants.HTTP_STATUS_CREATED, 
                ApiConstants.PATH_FILMS);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmModel> getFilmById(@PathVariable UUID id) {
        FilmModel film = filmService.findById(id);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FilmModel>> getAllFilms() {
        List<FilmModel> filmsList = filmService.findAll();
        return new ResponseEntity<>(filmsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FilmModel>> updateFilm(@PathVariable UUID id, 
                                                             @RequestBody CreateFilmDTO createFilmDTO) {
        FilmModel updatedFilm = filmService.update(id, createFilmDTO);
        ApiResponse<FilmModel> response = ResponseUtil.success(updatedFilm, 
                ApiConstants.MESSAGE_RESOURCE_UPDATED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_FILMS_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFilm(@PathVariable UUID id) {
        filmService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, 
                ApiConstants.MESSAGE_RESOURCE_DELETED, 
                ApiConstants.HTTP_STATUS_NO_CONTENT, 
                ApiConstants.PATH_FILMS_ID);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
