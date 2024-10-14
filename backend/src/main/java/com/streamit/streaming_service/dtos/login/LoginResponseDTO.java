package com.streamit.streaming_service.dtos.login;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

	private String token;
	private UUID idUser;
}
