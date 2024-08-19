package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
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
	private String titulo;
	private int anoProducao;
	private String genero;
	private int duracao; // em minutos
	
	@ElementCollection
	@CollectionTable(name = "media_subtitles", joinColumns = @JoinColumn(name = "film_id"))
	@Column(name = "subtitles")
	private List<String> legendasDisponiveis;
	
	@ElementCollection
	@CollectionTable(name = "media_audios", joinColumns = @JoinColumn(name = "film_id"))
	@Column(name = "audios")
	private List<String> audiosDisponiveis;
	
	private String descricao;
	
	@ElementCollection
	@CollectionTable(name = "media_actors", joinColumns = @JoinColumn(name = "film_id"))
	@Column(name = "actors")
	private List<String> atores;
	
	private String diretor;
	
	@ManyToOne
	@JoinColumn(name = "catalog_id")
	private CatalogModel catalog;

}
