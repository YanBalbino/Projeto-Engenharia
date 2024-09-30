package com.streamit.streaming_service.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.payment.ReturnPaymentDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.enums.PaymentMethod;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.PaymentMapper;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.PaymentRepository;
import com.streamit.streaming_service.services.IPaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	PaymentRepository paymentRepository;

	@Override
	public PaymentModel processarPagamento(PaymentMethod metodo, double valor, UUID id) {
		PaymentModel entity = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + id));
		
		LocalDateTime currentDate = LocalDateTime.now();

		entity.setDataPagamento(currentDate);
		entity.setMetodoPagamento(metodo);
		entity.setValor(valor);
		
		PaymentModel entitySaved = paymentRepository.save(entity);
		
		return entitySaved;
	}

	@Override
	public ReturnPaymentDTO findByUserId(UUID idUser) {
		PaymentModel payment = paymentRepository.findByUserId(idUser)
				.orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + idUser + " de usuário."));
		return PaymentMapper.toDto(payment);
	}

	@Override
	public PaymentModel createPayment(UserModel user, CreateUserDTO userDto, LocalDateTime currentDate) {

        PaymentModel payment = new PaymentModel();
        payment.setUser(user);
        payment.setDataPagamento(currentDate);
        payment.setMetodoPagamento(userDto.getMetodoPagamento());
        payment.setValor(userDto.getValor());

		return payment;
	}
}
