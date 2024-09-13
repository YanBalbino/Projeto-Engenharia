package com.streamit.streaming_service.dtos.payment;

import java.time.LocalDateTime;
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
public class ReturnPaymentDTO {
	
	private UUID id;
    
    private String metodoPagamento;
    
    private double valor;
    
    private LocalDateTime dataPagamento;

}

