package com.streamit.streaming_service.dtos.subtitle;

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
public class ReturnSubtitleDTO {

    private UUID id;
    private String idioma;
    private String url;
}
