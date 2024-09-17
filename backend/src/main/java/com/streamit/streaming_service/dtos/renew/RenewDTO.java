package com.streamit.streaming_service.dtos.renew;

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
public class RenewDTO {

	@NotNull(message = "ID do usuário não pode ser nulo.")
	private UUID idUser;
	
	@NotNull(message = "ID da inscrição não pode ser nula.")
	private UUID idSubscription;
	
	@NotNull(message = "ID do pagamento não pode ser nulo.")
	private UUID idPayment;
	
    @NotBlank(message = "Método do pagamento é obrigatório.")
    private String metodoPagamento;
    
    @NotNull(message = "Valor não pode ser nulo.")
    private double valor;
}
