package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.streamit.streaming_service.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PaymentModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@OneToOne
	@JoinColumn(name = "user_id")
    private UserModel user;	
    private PaymentMethod metodoPagamento; 
    private LocalDateTime dataPagamento;
    private double valor;
}

