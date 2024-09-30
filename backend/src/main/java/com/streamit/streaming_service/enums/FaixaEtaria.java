package com.streamit.streaming_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FaixaEtaria {

    LIVRE("L"),
    DEZ("10"),
    DOZE("12"),
    QUATORZE("14"),
    DEZESSEIS("16"),
    DEZOITO("18");

    private final String descricao;
}
