package com.streamit.streaming_service.dtos;

import java.util.List;

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
public class EpisodeDTO {

    @NotNull(message = "Número do episódio é obrigatório.")
    private Integer numeroEpisodio;

    @NotEmpty(message = "Título do episódio não pode ser vazio.")
    @Size(max = 255, message = "O título do episódio não pode ter mais que 255 caracteres.")
    private String tituloEpisodio;

    @NotNull(message = "Duração do episódio é obrigatória.")
    private Integer duracaoEpisodio;

    @NotEmpty(message = "A URL do vídeo não pode ser vazia.")
    @Size(max = 500, message = "A URL do vídeo não pode ter mais que 500 caracteres.")
    private String videoUrl;

    @NotEmpty(message = "Deve haver pelo menos uma legenda disponível.")
    private List<SubtitleDTO> legendasDisponiveis;

    @NotEmpty(message = "Deve haver pelo menos um áudio disponível.")
    private List<AudioDTO> audiosDisponiveis;
}
