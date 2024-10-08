package com.streamit.streaming_service.dtos.subscription;

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
public class ReturnSubscriptionDTO {
    
	private UUID id;
	
    private LocalDateTime dataInicio;
    
    private LocalDateTime dataTermino;
    
    private boolean statusAtivo;
    
}

