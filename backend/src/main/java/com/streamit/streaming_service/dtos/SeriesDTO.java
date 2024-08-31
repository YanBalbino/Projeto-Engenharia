package com.streamit.streaming_service.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeriesDTO {

    @NotEmpty(message = "Título não pode ser vazio.")
    @Size(max = 255, message = "O título não pode ter mais que 255 caracteres.")
    private String titulo;

    @NotNull(message = "Ano de produção é obrigatório.")
    private Integer anoProducao;

    @NotEmpty(message = "Gênero não pode ser vazio.")
    @Size(max = 100, message = "O gênero não pode ter mais que 100 caracteres.")
    private String genero;

    @Size(max = 500, message = "A descrição não pode ter mais que 500 caracteres.")
    private String descricao;

    @NotEmpty(message = "Diretor não pode ser vazio.")
    @Size(max = 255, message = "O nome do diretor não pode ter mais que 255 caracteres.")
    private String diretor;

    @NotEmpty(message = "Deve haver pelo menos uma temporada.")
    private List<SeasonDTO> seasons;

    private List<ActorDTO> atores;
    
    private List<UUID> actorIds;
}