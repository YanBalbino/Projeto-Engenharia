package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IActorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/actors")
@AllArgsConstructor
public class ActorController {

    private final IActorService actorService;

    @GetMapping("/{id}")
    public ResponseEntity<ReturnActorDTO> getActorById(@PathVariable UUID id) {
        ReturnActorDTO actorDto = actorService.findById(id);
        return new ResponseEntity<>(actorDto, HttpStatus.OK);
    }
    
    @GetMapping("/name")
    public ResponseEntity<List<ReturnActorDTO>> gedActorsByName(
            @RequestParam String nome, 
            Pageable pageable) {
        List<ReturnActorDTO> actors = actorService.findByName(nome, pageable);
        return ResponseEntity.ok(actors);
    }
    
    @GetMapping("/films/{filmId}")
    public ResponseEntity<List<ReturnActorDTO>> getAllActorsByFilm(@PathVariable UUID filmId) {
        List<ReturnActorDTO> actorDto = actorService.findAllByFilm(filmId);
        return new ResponseEntity<>(actorDto, HttpStatus.OK);
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<ReturnActorDTO>> getAllActorsBySeries(@PathVariable UUID seriesId) {
        List<ReturnActorDTO> actorDto = actorService.findAllBySeries(seriesId);
        return new ResponseEntity<>(actorDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ReturnActorDTO>> updateActor(@Valid @RequestBody UpdateActorDTO actorDto) {

        ReturnActorDTO updatedActor = actorService.update(actorDto);
        ApiResponse<ReturnActorDTO> response = ResponseUtil.success(
                updatedActor,
                ApiConstants.MESSAGE_RESOURCE_UPDATED,
                ApiConstants.HTTP_STATUS_OK,
                ApiConstants.PATH_ACTORS
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteActor(@PathVariable UUID id) {
    	actorService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, 
                ApiConstants.MESSAGE_RESOURCE_DELETED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_ACTORS_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
