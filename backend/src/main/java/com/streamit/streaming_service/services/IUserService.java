package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CreateUserWithPaymentDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;

public interface IUserService {

	ReturnUserDTO create(CreateUserWithPaymentDTO userPaymentDto);
	ReturnUserDTO findUserDtoById(UUID id);
	List<ReturnUserDTO> findAll();
	boolean update(String name, UUID idUser);
	boolean delete(UUID id);
}
