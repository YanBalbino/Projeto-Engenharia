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
import com.streamit.streaming_service.dtos.profile.CreateProfileDTO;
import com.streamit.streaming_service.dtos.profile.ReturnProfileDTO;
import com.streamit.streaming_service.dtos.profile.UpdateProfileDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IProfileService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {

    private final IProfileService profileService;

    @PostMapping("/user/{idUser}")
    public ResponseEntity<ApiResponse<ReturnProfileDTO>> createProfile(@Valid @RequestBody CreateProfileDTO profileDto, @PathVariable UUID idUser) {
    	ReturnProfileDTO createdProfile = profileService.create(profileDto, idUser);
    	
        ApiResponse<ReturnProfileDTO> response = ResponseUtil.success(createdProfile, 
                ApiConstants.MESSAGE_RESOURCE_CREATED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_PROFILES);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnProfileDTO> getProfileById(@PathVariable UUID id) {
    	ReturnProfileDTO profile = profileService.findProfileDtoById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<ReturnProfileDTO>> getProfilesByUser(@PathVariable UUID idUser) {
        List<ReturnProfileDTO> profiles = profileService.findProfileDetailsByUser(idUser);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ReturnProfileDTO>> updateProfile(@RequestBody UpdateProfileDTO profileDTO) {
    	ReturnProfileDTO updatedProfile = profileService.updateProfile(profileDTO);
        ApiResponse<ReturnProfileDTO> response = ResponseUtil.success(updatedProfile, 
                ApiConstants.MESSAGE_RESOURCE_UPDATED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_PROFILES);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProfile(@PathVariable UUID id) {
        profileService.delete(id);
        ApiResponse<Void> response = ResponseUtil.success(null, 
                ApiConstants.MESSAGE_RESOURCE_DELETED, 
                ApiConstants.HTTP_STATUS_OK, 
                ApiConstants.PATH_PROFILE_BY_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
