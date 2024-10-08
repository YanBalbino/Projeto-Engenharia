package com.streamit.streaming_service.dtos.payment;

import com.streamit.streaming_service.enums.PaymentMethod;

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
public class CreatePaymentDTO {
    
    @NotBlank(message = "Método do pagamento é obrigatório.")
    private PaymentMethod metodoPagamento;
    
    @NotNull(message = "Valor não pode ser nulo.")
    private double valor;
    
}

