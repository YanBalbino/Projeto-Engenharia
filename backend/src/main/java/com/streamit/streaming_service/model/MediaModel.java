package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medias")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public abstract class MediaModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String titulo;
	private int anoProducao;
	private String genero;
	private int duracao; // em minutos

	private String descricao;

	private String diretor;

    @Column(unique = true)
	private String videoUrl;
    
    @ElementCollection
    @CollectionTable(name = "media_subtitles", joinColumns = @JoinColumn(name = "media_id"))
    @Column(name = "subtitle")
    private List<String> legendasDisponiveis;

    @ElementCollection
    @CollectionTable(name = "media_audios", joinColumns = @JoinColumn(name = "media_id"))
    @Column(name = "audio")
    private List<String> audiosDisponiveis;

    @ElementCollection
    @CollectionTable(name = "media_actors", joinColumns = @JoinColumn(name = "media_id"))
    @Column(name = "actor")
    private List<String> atores;

}