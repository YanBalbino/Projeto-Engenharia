package com.streamit.streaming_service.dtos.subtitle;

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
public class CreateSubtitleDTO {
	
    @NotBlank(message = "A URL da legenda não pode ser vazia")
    private String legendaUrl;

    @NotBlank(message = "O idioma da legenda não pode ser vazio")
    private String idioma;
}
