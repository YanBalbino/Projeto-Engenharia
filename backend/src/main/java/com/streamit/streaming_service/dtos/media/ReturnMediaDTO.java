package com.streamit.streaming_service.dtos.media;

import java.util.UUID;

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
public class ReturnMediaDTO {

    private UUID id;
    private String titulo;
    private Integer anoProducao;
    private String genero;
    private String descricao;
    private String diretor;
}

