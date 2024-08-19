package com.streamit.streaming_service.model;

import jakarta.persistence.Entity;
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
}

