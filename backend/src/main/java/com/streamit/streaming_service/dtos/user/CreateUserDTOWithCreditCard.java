package com.streamit.streaming_service.dtos.user;

import com.streamit.streaming_service.dtos.payment.CreditCardDTO;

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
public class CreateUserDTOWithCreditCard {
	
	@NotNull(message = "Dados do usuário não podem ser nulos")
	private CreateUserDTO userDto;
    
    @NotNull(message = "Dados do cartão não podem ser nulos")
    private CreditCardDTO creditCardDto;
    
}
