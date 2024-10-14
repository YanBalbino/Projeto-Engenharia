package com.streamit.streaming_service.dtos.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO{

    @NotBlank(message = "Número do cartão não pode ser vazio.")
    private String cardNumber;

    @NotBlank(message = "Nome do titular não pode ser vazio.")
    private String cardHolder;

    @NotBlank(message = "Data de validade não pode ser vazia.")
    private String expiryDate; 

    @NotBlank(message = "Código de segurança não pode ser vazio.")
    private String cvv;
}
