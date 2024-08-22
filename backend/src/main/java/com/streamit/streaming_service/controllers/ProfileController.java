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
import com.streamit.streaming_service.dtos.ReturnProfileDTO;
import com.streamit.streaming_service.services.IProfileService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {

    private final IProfileService profileService;

    @PostMapping("/user/{idUser}")
    public ResponseEntity<ReturnProfileDTO> createProfile(@Valid @RequestBody CreateProfileDTO profileDto, @PathVariable UUID idUser) {
    	ReturnProfileDTO createdProfile = profileService.create(profileDto, idUser);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfile(@RequestBody CreateProfileDTO profileDTO, @PathVariable UUID id) {
        boolean updated = profileService.updateProfile(profileDTO, id);
        if (updated) {
            return ResponseEntity.ok("Profile updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found.");
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
