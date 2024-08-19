package com.streamit.streaming_service.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private UUID userId;

    //@NotBlank(message = "O método de pagamento não pode ser vazio")
    private String metodoPagamento; 

    @PastOrPresent(message = "A data de pagamento não pode ser futura")
    @NotNull(message = "A data de pagamento não pode ser nula")
    private LocalDate dataPagamento;

    @DecimalMin(value = "30.0", inclusive = false, message = "O valor deve ser igual a 30")
    private double valor;
}
