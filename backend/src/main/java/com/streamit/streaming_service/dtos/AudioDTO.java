package com.streamit.streaming_service.dtos;

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
public class AudioDTO {

    @NotBlank(message = "A URL do áudio não pode ser vazia")
    private String audioUrl;

    @NotBlank(message = "O idioma do áudio não pode ser vazio")
    private String idioma;
}
