package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.enums.FaixaEtaria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medias")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MediaModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(unique = true)
	private String titulo;
	private int anoProducao;
	private String genero;
	private String descricao;
	private String diretor;
	private String imgUrl;
	private FaixaEtaria faixaEtaria;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
	    name = "media_actors",
	    joinColumns = @JoinColumn(name = "media_id"),
	    inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	private List<ActorModel> atores;

}