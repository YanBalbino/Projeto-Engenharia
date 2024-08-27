package com.streamit.streaming_service.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.dtos.ReturnPaymentDTO;
import com.streamit.streaming_service.services.impl.PaymentServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {

    private PaymentServiceImpl paymentServiceImpl;

    @GetMapping("/{userId}")
    public ResponseEntity<ReturnPaymentDTO> getSubscriptionByUserId(@PathVariable UUID userId) {
    	ReturnPaymentDTO payment = paymentServiceImpl.findByUserId(userId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
