package com.streamit.streaming_service.dtos.user;

import java.time.LocalDate;

import com.streamit.streaming_service.dtos.payment.CreditCardDTO;
import com.streamit.streaming_service.enums.PaymentMethod;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
public class CreateUserDTOWithCreditCard {
    
    @NotNull(message = "Dados do usuário não podem ser nulos")
    private CreateUserDTO userDTO;

    @NotNull(message = "Dados do cartão não podem ser nulos")
    private CreditCardDTO creditCardDTO;
    
}
