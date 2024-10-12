package com.streamit.streaming_service.dtos.media;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.enums.FaixaEtaria;

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
public class CreateMediaDTO {
    
    @NotBlank(message = "A faixa etária da mídia não pode ser vazia.")
    private FaixaEtaria faixaEtaria;
    
    private List<UUID> actorIds;
    
    private List<CreateActorDTO> atores;
}
