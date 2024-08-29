package com.streamit.streaming_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "films")
public class FilmModel extends MediaModel {

	private static final long serialVersionUID = 1L;
}