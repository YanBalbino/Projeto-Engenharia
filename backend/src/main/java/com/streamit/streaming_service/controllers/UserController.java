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
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.payment.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.IUserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
public class UserController {

	private final IUserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDto) {
		String token = userService.login(loginDto);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<ReturnUserDTO>> register(@Valid @RequestBody CreateUserDTO userPaymentDto) {
		ReturnUserDTO createdUser = userService.create(userPaymentDto);
		ApiResponse<ReturnUserDTO> response = ResponseUtil.success(createdUser, ApiConstants.MESSAGE_RESOURCE_CREATED,
				ApiConstants.HTTP_STATUS_CREATED, ApiConstants.PATH_USERS);
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

	@PutMapping("/{userId}/subscriptions/{subscriptionId}/payments/{paymentId}/renew")
	public ResponseEntity<ApiResponse<ReturnUserDTO>> renovarInscricao(@PathVariable UUID userId,
			@PathVariable UUID subscriptionId, @PathVariable UUID paymentId,
			@Valid @RequestBody CreatePaymentDTO paymentDto) {

		ReturnUserDTO createdUser = userService.renovarInscricao(userId, subscriptionId, paymentId, paymentDto);

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
}
