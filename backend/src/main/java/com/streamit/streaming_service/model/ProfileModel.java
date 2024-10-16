package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProfileModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
    private String nome;
    private String iconUrl;
    private boolean perfilInfantil; // true para perfil infantil, false para perfil comum
    
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profiles_genre", joinColumns = @JoinColumn(name = "profile_id"))
	@Column(name = "genres")
    private List<String> generosPreferidos; 
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
}

