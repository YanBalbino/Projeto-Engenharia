package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
    private PersonModel person;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProfileModel> perfis;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PaymentModel payment;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SubscriptionModel subscription;
	
    private LocalDateTime createdDate;
    
    private String codigoEmail;
}

