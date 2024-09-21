package com.streamit.streaming_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;

public interface ICatalogService {

    List<ReturnMediaDTO> findFilmsAndSeriesByActorName(String nomeAtor, Pageable pageable);
    List<ReturnMediaDTO> findFilmsAndSeriesByTitle(String titulo, Pageable pageable);
    List<ReturnMediaDTO> findFilmsAndSeriesByGenre(String genero, Pageable pageable);
    List<ReturnMediaDTO> findFilmsAndSeriesByDirector(String diretor, Pageable pageable);
}
