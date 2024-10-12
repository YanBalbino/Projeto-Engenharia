package com.streamit.streaming_service.dtos.media;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.UpdateActorDTO;
import com.streamit.streaming_service.enums.FaixaEtaria;

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
public class UpdateMediaDTO {

	private UUID id;
	private String titulo;
	private String anoProducao;
	private String genero;
	private String descricao;
	private String diretor;
	private String imgUrl;
	private FaixaEtaria faixaEtaria;
	private List<UpdateActorDTO> atores;
	private List<UUID> actorIds;
}
