package com.streamit.streaming_service.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.dtos.CreateUserWithPaymentDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;
import com.streamit.streaming_service.services.IUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

	private final IUserService userService;

	@PostMapping
	public ResponseEntity<ReturnUserDTO> createUser(@Valid @RequestBody CreateUserWithPaymentDTO userPaymentDto) {
		ReturnUserDTO createdUser = userService.create(userPaymentDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReturnUserDTO> getUserById(@PathVariable UUID id) {
		ReturnUserDTO user = userService.findUserDtoById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ReturnUserDTO>> getAllUsers() {
		List<ReturnUserDTO> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser(
			@NotBlank(message = "Nome é obrigatório") @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres") String name,
			@PathVariable UUID id) {
		boolean updated = userService.update(name, id);
		if (updated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		boolean deleted = userService.delete(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
