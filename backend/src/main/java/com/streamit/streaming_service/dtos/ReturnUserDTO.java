package com.streamit.streaming_service.dtos;

import java.util.List;

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
    
    private String nome;
    
    private String email;
    
    private List<CreateProfileDTO> perfis;
}
