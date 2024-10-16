package com.streamit.streaming_service.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.renew.RenewDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTOWithCreditCard;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;
import com.streamit.streaming_service.model.UserModel;

public interface IUserService {

	ReturnUserDTO registerWithCreditCard(CreateUserDTOWithCreditCard userDTOWithCreditCard);
	ReturnUserDTO registerWithBankSlip(CreateUserDTO userPaymentDto);
	ReturnUserDTO findUserDtoById(UUID id);
	Page<ReturnUserDTO> findAll(Pageable pageable);
	ReturnUserDTO updateName(UpdateUserDTO userDto);
	void delete(UUID id);
	ReturnUserDTO renovarInscricao(RenewDTO renewDto);
	boolean getProfilesQuantity(UUID id);
	public void solicitarAlteracaoSenha(String email);
	public boolean verificarCodigo(String email, String codigo);
	public void alterarSenha(String email, String novaSenha);
	UserModel findUserModelById(UUID idUser);
	UserModel findUserModelByEmail(String email);
}