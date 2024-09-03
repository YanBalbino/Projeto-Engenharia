package com.streamit.streaming_service.dtos.actor;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
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
public class UpdateActorDTO {
	
	@NotNull(message = "O id do ator não pode ser nulo")
	private UUID id;

    private String imagemUrl;

    private String nome;
}
