package com.streamit.streaming_service.dtos;

import java.time.LocalDate;
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
public class ReturnSubscriptionDTO {
    
	private UUID id;
	
    private LocalDate dataInicio;
    
    private LocalDate dataTermino;
    
    private boolean statusAtivo;
    
}

