package com.streamit.streaming_service.dtos;

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
public class UpdateAudioDTO {
	
	@NotNull(message = "O id do áudio não pode ser nulo")
	private UUID id;

    private String audioUrl;

    private String idioma;
}
