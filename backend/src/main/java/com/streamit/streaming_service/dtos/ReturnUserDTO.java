package com.streamit.streaming_service.dtos;

import java.util.List;
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
public class ReturnUserDTO {
	
	private UUID id;
    
    private String nome;
    
    private String email;
    
    private List<ReturnProfileDTO> perfis;
}
