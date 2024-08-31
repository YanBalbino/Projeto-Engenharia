package com.streamit.streaming_service.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class FilmDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O título é obrigatório")
    @NotBlank(message = "O título não pode estar em branco")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres")
    private String titulo;

    @NotNull(message = "O ano de produção é obrigatório")
    @Min(value = 1888, message = "O ano de produção deve ser maior que 1888")
    private int anoProducao;

    @NotNull(message = "O gênero é obrigatório")
    @NotBlank(message = "O gênero não pode estar em branco")
    @Size(max = 100, message = "O gênero deve ter no máximo 100 caracteres")
    private String genero;

    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @Size(max = 255, message = "O nome do diretor deve ter no máximo 255 caracteres")
    private String diretor;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser maior que 0")
    private int duracao; // em minutos

    @NotNull(message = "A URL do vídeo é obrigatória")
    @NotBlank(message = "A URL do vídeo não pode estar em branco")
    @Size(max = 255, message = "A URL do vídeo deve ter no máximo 255 caracteres")
    private String videoUrl;

    private List<SubtitleDTO> legendasDisponiveis;

    private List<AudioDTO> audiosDisponiveis;

    private List<ActorDTO> atores;
    
    private List<UUID> actorIds;
}
