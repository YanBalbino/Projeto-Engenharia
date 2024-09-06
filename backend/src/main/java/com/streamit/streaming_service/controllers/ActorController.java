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

    @GetMapping
    public ResponseEntity<List<ReturnActorDTO>> getAllActors() {
        List<ReturnActorDTO> actorList = actorService.findAll();
        return new ResponseEntity<>(actorList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReturnActorDTO>> updateActor(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateActorDTO actorDto) {

        ReturnActorDTO updatedActor = actorService.update(id, actorDto);
        ApiResponse<ReturnActorDTO> response = ResponseUtil.success(
                updatedActor,
                ApiConstants.MESSAGE_RESOURCE_UPDATED,
                ApiConstants.HTTP_STATUS_OK,
                ApiConstants.PATH_ACTOR_ID
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
