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

import com.streamit.streaming_service.dtos.CreateProfileDTO;
import com.streamit.streaming_service.services.IProfileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {

    private final IProfileService profileService;

    @PostMapping("/user/{idUser}")
    public ResponseEntity<CreateProfileDTO> createProfile(@RequestBody CreateProfileDTO profileDto, @PathVariable UUID idUser) {
        CreateProfileDTO createdProfile = profileService.create(profileDto, idUser);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProfileDTO> getProfileById(@PathVariable UUID id) {
        CreateProfileDTO profile = profileService.findById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreateProfileDTO>> getAllProfiles() {
        List<CreateProfileDTO> profiles = profileService.findAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PutMapping("/user/{idUser}")
    public ResponseEntity<Void> updateProfile(@RequestBody CreateProfileDTO profileDto, @PathVariable UUID idUser) {
        boolean updated = profileService.update(profileDto, idUser);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID id) {
        boolean deleted = profileService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
