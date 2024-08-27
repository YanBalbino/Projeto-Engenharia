package com.streamit.streaming_service.services;

import java.util.UUID;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.ReturnPaymentDTO;
import com.streamit.streaming_service.model.PaymentModel;

public interface IPaymentService {

	PaymentModel processarPagamento(CreatePaymentDTO paymentDTO, UUID id);
	ReturnPaymentDTO findByUserId(UUID idUser);
}
