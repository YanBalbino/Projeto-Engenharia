package com.streamit.streaming_service.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "films")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FilmModel extends MediaModel {

	private static final long serialVersionUID = 1L;
	
	@ElementCollection
	@CollectionTable(name = "film_subtitles", joinColumns = @JoinColumn(name = "film_id"))
	@Column(name = "subtitles")
	private List<String> legendasDisponiveis;

	@ElementCollection
	@CollectionTable(name = "film_audios", joinColumns = @JoinColumn(name = "film_id"))
	@Column(name = "audios")
	private List<String> audiosDisponiveis;
	
	@ElementCollection
	@CollectionTable(name = "film_actors", joinColumns = @JoinColumn(name = "film_id"))
	@Column(name = "actors")
	private List<String> atores;
}
