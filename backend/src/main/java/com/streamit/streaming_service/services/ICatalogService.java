package com.streamit.streaming_service.services;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.catalog.ReturnCatalogDTO;

public interface ICatalogService {

    ReturnCatalogDTO findFilmsAndSeriesByActorName(String nomeAtor, Pageable pageable);
    ReturnCatalogDTO findFilmsAndSeriesByTitle(String titulo, Pageable pageable);
    ReturnCatalogDTO findFilmsAndSeriesByGenre(String genero, Pageable pageable);
    ReturnCatalogDTO findFilmsAndSeriesByDirector(String diretor, Pageable pageable);
}
