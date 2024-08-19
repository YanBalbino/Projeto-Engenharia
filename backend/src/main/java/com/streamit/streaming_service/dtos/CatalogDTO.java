package com.streamit.streaming_service.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
public class CatalogDTO {
    
    @NotNull(message = "A lista de filmes não pode ser nula")
    @NotEmpty(message = "O catálogo deve conter ao menos um filme")
    private List<UUID> filmesIds;

    @NotNull(message = "A lista de séries não pode ser nula")
    @NotEmpty(message = "O catálogo deve conter ao menos uma série")
    private List<UUID> seriesIds;
}

