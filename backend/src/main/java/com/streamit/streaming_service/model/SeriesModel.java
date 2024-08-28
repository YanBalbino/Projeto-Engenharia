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
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SeriesModel extends MediaModel {
	private static final long serialVersionUID = 1L;
	private int numeroTemporadas;
	private int numeroEpisodios;
	
	@ElementCollection
	@CollectionTable(name = "series_subtitles", joinColumns = @JoinColumn(name = "series_id"))
	@Column(name = "subtitles")
	private List<String> legendasDisponiveis;

	@ElementCollection
	@CollectionTable(name = "series_audios", joinColumns = @JoinColumn(name = "series_id"))
	@Column(name = "audios")
	private List<String> audiosDisponiveis;
	
	@ElementCollection
	@CollectionTable(name = "series_actors", joinColumns = @JoinColumn(name = "series_id"))
	@Column(name = "actors")
	private List<String> atores;
}
