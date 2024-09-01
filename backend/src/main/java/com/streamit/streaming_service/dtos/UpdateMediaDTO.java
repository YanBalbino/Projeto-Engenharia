package com.streamit.streaming_service.dtos;

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
public class UpdateMediaDTO {

    private String titulo;

    private Integer anoProducao;

    private String genero;

    private String descricao;

    private String diretor;
}
