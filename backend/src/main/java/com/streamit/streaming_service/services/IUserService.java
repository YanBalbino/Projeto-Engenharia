package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.payment.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;

public interface IUserService {

	ReturnUserDTO create(CreateUserDTO userPaymentDto);
	ReturnUserDTO findUserDtoById(UUID id);
	List<ReturnUserDTO> findAll(Pageable pageable);
	ReturnUserDTO updateName(UpdateUserDTO userDto);
	void delete(UUID id);
	ReturnUserDTO renovarInscricao(UUID userId, UUID paymentId, UUID subscriptionId, CreatePaymentDTO paymentDto);
	boolean getProfilesQuantity(UUID id);
	String login(LoginDTO loginDto);
}
