package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "catalogs")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CatalogModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@OneToMany(mappedBy = "catalogo")
    private List<FilmModel> filmes;
	@OneToMany(mappedBy = "catalogo")
    private List<SeriesModel> series;

    /*public List<FilmModel> buscarFilmePorTitulo(String titulo) {
        // Implementação de busca por título
    }

    public List<SeriesModel> buscarSeriePorTitulo(String titulo) {
        // Implementação de busca por título
    }

    public List<FilmModel> buscarFilmePorDiretor(String diretor) {
        // Implementação de busca por diretor
    }

    public List<SeriesModel> buscarSeriePorDiretor(String diretor) {
        // Implementação de busca por diretor
    }

    public List<FilmModel> buscarFilmePorAtor(String ator) {
        // Implementação de busca por ator
    }

    public List<SeriesModel> buscarSeriePorAtor(String ator) {
        // Implementação de busca por ator
    }*/
}

