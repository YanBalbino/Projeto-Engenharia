package com.streamit.streaming_service.services.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.CreateUserDTO;
import com.streamit.streaming_service.dtos.ReturnPaymentDTO;
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

	public PaymentModel processarPagamento(CreatePaymentDTO paymentDTO, UUID id) {
		PaymentModel entity = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + id));
		
        LocalDate currentDate = LocalDate.now();

		entity.setDataPagamento(currentDate);
		entity.setMetodoPagamento(paymentDTO.getMetodoPagamento());
		entity.setValor(paymentDTO.getValor());
		
		PaymentModel entitySaved = paymentRepository.save(entity);
		
		return entitySaved;
	}

	@Override
	public ReturnPaymentDTO findByUserId(UUID idUser) {
		PaymentModel payment = paymentRepository.findByUserId(idUser)
				.orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + idUser + " de usuário."));
		return PaymentMapper.toDto(payment);
	}

	public PaymentModel createPayment(UserModel user, CreateUserDTO userDto, LocalDate currentDate) {

        PaymentModel payment = new PaymentModel();
        payment.setUser(user);
        payment.setDataPagamento(currentDate);
        payment.setMetodoPagamento(userDto.getMetodoPagamento());
        payment.setValor(userDto.getValor());

		return payment;
	}
}
