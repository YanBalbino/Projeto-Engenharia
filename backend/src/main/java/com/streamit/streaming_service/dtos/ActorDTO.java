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
public class ActorDTO {

    @NotBlank(message = "A URL da imagem não pode ser vazia")
    private String imagemUrl;

    @NotBlank(message = "O nome do ator não pode ser vazio")
    private String nome;
}
