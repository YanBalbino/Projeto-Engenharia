package com.streamit.streaming_service.services;

import java.util.UUID;

import com.streamit.streaming_service.dtos.ReturnPaymentDTO;

public interface IPaymentService {

	ReturnPaymentDTO findByUserId(UUID idUser);
}
