package com.streamit.streaming_service.dtos.media;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Título não pode ser vazio.")
    @Size(max = 255, message = "O título não pode ter mais que 255 caracteres.")
    private String titulo;

    @NotNull(message = "Ano de produção é obrigatório.")
    private Integer anoProducao;

    @NotBlank(message = "Gênero não pode ser vazio.")
    @Size(max = 100, message = "O gênero não pode ter mais que 100 caracteres.")
    private String genero;

    @Size(max = 500, message = "A descrição não pode ter mais que 500 caracteres.")
    private String descricao;

    @NotBlank(message = "Diretor não pode ser vazio.")
    @Size(max = 255, message = "O nome do diretor não pode ter mais que 255 caracteres.")
    private String diretor;
    
    @NotBlank(message = "A URL da imagem da mídia não pode ser vazia.")
    private String imgUrl;
    
    private List<UUID> actorIds;
    
    private List<CreateActorDTO> atores;
}
