package com.streamit.streaming_service.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.dtos.ReturnSubscriptionDTO;
import com.streamit.streaming_service.services.ISubscriptionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subscriptions")
@AllArgsConstructor
public class SubscriptionController {

    private ISubscriptionService subscriptionService;

    @GetMapping("/{userId}")
    public ResponseEntity<ReturnSubscriptionDTO> getSubscriptionByUserId(@PathVariable UUID userId) {
    	ReturnSubscriptionDTO subscription = subscriptionService.findByUserId(userId);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
