package com.streamit.streaming_service.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
public class CreateSeriesDTO {

    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @Positive(message = "O ano de produção deve ser positivo")
    private int anoProducao;

    @NotBlank(message = "O gênero não pode ser vazio")
    private String genero;

    @Positive(message = "A duração deve ser positiva")
    private int duracao; // em minutos

    @NotNull(message = "A lista de legendas não pode ser nula")
    @NotEmpty(message = "Deve haver ao menos uma legenda disponível")
    private List<String> legendasDisponiveis;

    @NotNull(message = "A lista de áudios não pode ser nula")
    @NotEmpty(message = "Deve haver ao menos um áudio disponível")
    private List<String> audiosDisponiveis;

    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotNull(message = "A lista de atores não pode ser nula")
    @NotEmpty(message = "Deve haver ao menos um ator")
    private List<String> atores;

    @NotBlank(message = "O nome do diretor não pode ser vazio")
    private String diretor;

    @Min(value = 1, message = "O número de temporadas deve ser no mínimo 1")
    private int numeroTemporadas;

    @Min(value = 1, message = "O número de episódios deve ser no mínimo 1")
    private int numeroEpisodios;

    @NotNull(message = "O ID do catálogo não pode ser nulo")
    private UUID catalogId;
}
