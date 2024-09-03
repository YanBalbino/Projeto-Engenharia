package com.streamit.streaming_service.dtos.actor;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReturnActorDTO {

    private UUID id;

    private String imagemUrl;

    private String nome;
}

