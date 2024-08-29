package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
	
	private String nome;
	
    @ManyToMany
    @JoinTable(
        name = "catalog_media",
        joinColumns = @JoinColumn(name = "catalog_id"), 
        inverseJoinColumns = @JoinColumn(name = "media_id") 
    )
    private List<MediaModel> medias;

    /*public List<MediaModel> buscarFilmePorTitulo(String titulo) {
        // Implementação de busca por título
    }

    public List<MediaModel> buscarFilmePorDiretor(String diretor) {
        // Implementação de busca por diretor
    }

    public List<MediaModel> buscarFilmePorAtor(String ator) {
        // Implementação de busca por ator
    }*/

}

