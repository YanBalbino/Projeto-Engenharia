package com.streamit.streaming_service.dtos.audio;

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
public class ReturnAudioDTO {

    private UUID id;
    private String idioma;
    private String url;
}
