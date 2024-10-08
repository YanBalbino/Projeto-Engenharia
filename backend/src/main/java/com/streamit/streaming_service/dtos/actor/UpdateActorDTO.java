package com.streamit.streaming_service.dtos.actor;

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
public class UpdateActorDTO {
	
	private UUID id;

    private String imagemUrl;

    private String nome;
}
