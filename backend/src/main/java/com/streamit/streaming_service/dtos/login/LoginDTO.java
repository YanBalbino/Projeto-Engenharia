package com.streamit.streaming_service.dtos.login;

import jakarta.validation.constraints.NotBlank;
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
public class LoginDTO {

	@NotBlank(message = "Senha não pode ser vazio.")
	private String senha;
	@NotBlank(message = "Email não pode ser vazio.")
	private String email;
}
