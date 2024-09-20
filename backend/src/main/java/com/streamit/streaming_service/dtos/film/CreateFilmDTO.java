package com.streamit.streaming_service.dtos.film;

import java.io.Serializable;
import java.util.List;

import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.media.CreateMediaDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;

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
public class CreateFilmDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private CreateMediaDTO media;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser maior que 0")
    private int duracao; // em minutos

    @NotNull(message = "A URL do vídeo é obrigatória")
    @NotBlank(message = "A URL do vídeo não pode estar em branco")
    @Size(max = 255, message = "A URL do vídeo deve ter no máximo 255 caracteres")
    private String videoUrl;

    private List<CreateSubtitleDTO> legendasDisponiveis;

    private List<CreateAudioDTO> audiosDisponiveis;
    
}
