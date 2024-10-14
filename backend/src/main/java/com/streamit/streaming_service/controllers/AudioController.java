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
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IAudioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/audios")
@AllArgsConstructor
public class AudioController {

    private final IAudioService audioService;

    @GetMapping("/{id}")
    public ResponseEntity<ReturnAudioDTO> getAudioById(@PathVariable UUID id) {
        ReturnAudioDTO audioDto = audioService.findById(id);
        return new ResponseEntity<>(audioDto, HttpStatus.OK);
    }
    
    @GetMapping("/films/{filmId}")
    public ResponseEntity<List<ReturnAudioDTO>> getAllAudiosByFilm(@PathVariable UUID filmId) {
        List<ReturnAudioDTO> audioDto = audioService.findAllByFilm(filmId);
        return new ResponseEntity<>(audioDto, HttpStatus.OK);
    }

    @GetMapping("/episodes/{episodeId}")
    public ResponseEntity<List<ReturnAudioDTO>> getAllAudiosByEpisode(@PathVariable UUID episodeId) {
        List<ReturnAudioDTO> audioDto = audioService.findAllByEpisode(episodeId);
        return new ResponseEntity<>(audioDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ReturnAudioDTO>> updateAudio(@Valid @RequestBody UpdateAudioDTO audioDto) {

        ReturnAudioDTO updatedAudio = audioService.update(audioDto);
        ApiResponse<ReturnAudioDTO> response = ResponseUtil.success(
                updatedAudio,
                ApiConstants.MESSAGE_RESOURCE_UPDATED,
                ApiConstants.HTTP_STATUS_OK,
                ApiConstants.PATH_AUDIOS
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteActor(@PathVariable UUID id) {
    	audioService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, 
                ApiConstants.MESSAGE_RESOURCE_DELETED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_AUDIOS_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
