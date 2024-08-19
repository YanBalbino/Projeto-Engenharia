package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.CreateUserDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;

public interface IUserService {

	ReturnUserDTO create(CreateUserDTO user, CreatePaymentDTO paymentDTO);
	ReturnUserDTO findById(UUID id);
	List<ReturnUserDTO> findAll();
	boolean update(CreateUserDTO user, CreatePaymentDTO paymentDTO);
	boolean delete(UUID id);
}
