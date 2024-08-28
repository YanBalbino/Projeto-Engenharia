package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.CreateUserDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;

public interface IUserService {

	ReturnUserDTO create(CreateUserDTO userPaymentDto);
	ReturnUserDTO findUserDtoById(UUID id);
	List<ReturnUserDTO> findAll();
	ReturnUserDTO update(String name, UUID idUser);
	void delete(UUID id);
	ReturnUserDTO renovarInscricao(UUID userId, UUID paymentId, UUID subscriptionId, CreatePaymentDTO paymentDto);
}
