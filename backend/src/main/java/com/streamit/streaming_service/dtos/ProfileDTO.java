package com.streamit.streaming_service.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
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
public class ProfileDTO {
    
    @NotBlank(message = "Nome do perfil é obrigatório")
    private String nome;
    
    private String iconUrl;
    
    @NotNull(message = "É necessário indicar se o perfil é infantil")
    private Boolean perfilInfantil;
    
    private List<String> generosPreferidos;

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private UUID userId; // referência ao usuário do perfil
}

