package com.streamit.streaming_service.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "actors")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ActorModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String imagemUrl;

    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "atores")
    private List<FilmModel> filme;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "atores")
    private List<SeriesModel> serie;
}
