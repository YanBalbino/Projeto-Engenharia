package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.payment.CreditCardDTO;
import com.streamit.streaming_service.dtos.renew.RenewDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
public class UserController {

	private final IUserService userService;

	@PostMapping("/register/credit-card")
	public ResponseEntity<ApiResponse<ReturnUserDTO>> register(@Valid @RequestBody CreateUserDTO userPaymentDto, CreditCardDTO creditCardDto) {
		ReturnUserDTO createdUser = userService.registerWithCreditCard(userPaymentDto, creditCardDto);
		ApiResponse<ReturnUserDTO> response = ResponseUtil.success(createdUser, ApiConstants.MESSAGE_RESOURCE_CREATED,
				ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_USERS_REGISTER_CREDIT_CARD);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/register/bank-slip")
	public ResponseEntity<ApiResponse<ReturnUserDTO>> register(@Valid @RequestBody CreateUserDTO userPaymentDto) {
		ReturnUserDTO createdUser = userService.registerWithBankSlip(userPaymentDto);
		ApiResponse<ReturnUserDTO> response = ResponseUtil.success(createdUser, ApiConstants.MESSAGE_RESOURCE_CREATED,
				ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_USERS_REGISTER_BANK_SLIP);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnUserDTO> getUserById(@PathVariable UUID id) {
		ReturnUserDTO user = userService.findUserDtoById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ReturnUserDTO>> getAllUsers(Pageable pageable) {
		List<ReturnUserDTO> users = userService.findAll(pageable);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/renew")
	public ResponseEntity<ApiResponse<ReturnUserDTO>> renovarInscricao(@Valid @RequestBody RenewDTO renewDto) {

		ReturnUserDTO createdUser = userService.renovarInscricao(renewDto);

		ApiResponse<ReturnUserDTO> response = ResponseUtil.success(createdUser, ApiConstants.MESSAGE_RESOURCE_UPDATED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_USERS_RENEW);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ApiResponse<ReturnUserDTO>> updateUser(@Valid @RequestBody UpdateUserDTO userDto) {
		ReturnUserDTO createdUser = userService.updateName(userDto);

		ApiResponse<ReturnUserDTO> response = ResponseUtil.success(createdUser, ApiConstants.MESSAGE_RESOURCE_UPDATED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_USERS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID id) {
		userService.delete(id);
		ApiResponse<Void> response = ResponseUtil.success(null, ApiConstants.MESSAGE_RESOURCE_DELETED,
				ApiConstants.HTTP_STATUS_OK, ApiConstants.PATH_USER_BY_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/max-profiles-quantity/{id}")
	public ResponseEntity<Boolean> checkProfilesQuantity(@PathVariable UUID id) {
		boolean hasMaxProfiles = userService.getProfilesQuantity(id);
		return ResponseEntity.ok(hasMaxProfiles);
	}
	
    @PostMapping("/solicitar-alteracao-senha")
    public ResponseEntity<String> solicitarAlteracaoSenha(@Email @NotBlank(message = "Email não pode ser vazio") @RequestParam String email) {
        userService.solicitarAlteracaoSenha(email);
        return new ResponseEntity<>("Solicitação de alteração de senha enviada com sucesso.", HttpStatus.OK);
    }
    
    @PostMapping("/verificar-codigo")
    public ResponseEntity<String> verificarCodigo(@Email @NotBlank(message = "Email não pode ser vazio") @RequestParam String email, @RequestParam String codigo) {
        boolean valido = userService.verificarCodigo(email, codigo);
        if (valido) {
            return new ResponseEntity<>("Código de verificação válido.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Código de verificação inválido.", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(@Email @NotBlank(message = "Email não pode ser vazio") @RequestParam String email, @NotBlank(message = "Senha não pode ser vazia") @RequestParam String novaSenha) {
    	userService.alterarSenha(email, novaSenha);
        return new ResponseEntity<>("Senha alterada com sucesso.", HttpStatus.OK);
    }
}
