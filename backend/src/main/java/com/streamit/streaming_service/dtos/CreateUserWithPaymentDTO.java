package com.streamit.streaming_service.dtos;

import java.time.LocalDate;
import java.util.List;

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
public class CreateUserWithPaymentDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;
    
    @Size(max = 4, message = "Um usuário pode ter no máximo 4 perfis")
    private List<ProfileDTO> perfis;
    
    private String metodoPagamento; 
    
    @PastOrPresent(message = "A data de pagamento não pode ser futura")
    @NotNull(message = "A data de pagamento não pode ser nula")
    private LocalDate dataPagamento;

    @DecimalMin(value = "30.0", inclusive = true, message = "O valor deve ser igual a 30")
    private double valor;
}
