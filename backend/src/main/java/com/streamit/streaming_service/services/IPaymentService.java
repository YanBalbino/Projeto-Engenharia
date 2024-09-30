package com.streamit.streaming_service.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.streamit.streaming_service.dtos.payment.ReturnPaymentDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.enums.PaymentMethod;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.UserModel;

public interface IPaymentService {

	ReturnPaymentDTO findByUserId(UUID idUser);
	PaymentModel processarPagamento(PaymentMethod metodo, double valor, UUID id);
	PaymentModel createPayment(UserModel user, CreateUserDTO userDto, LocalDateTime currentDate);
}
