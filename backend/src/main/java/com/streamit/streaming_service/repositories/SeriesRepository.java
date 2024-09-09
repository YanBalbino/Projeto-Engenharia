package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.SeriesModel;

public interface SeriesRepository extends JpaRepository<SeriesModel, UUID>{

    @Query("SELECT COUNT(s) > 0 FROM SeriesModel s WHERE s.media.titulo = :titulo")
    boolean existsByTitle(@Param("titulo") String titulo);
    
    // Busca por ator com paginação
    @Query("SELECT s FROM SeriesModel s JOIN s.atores a WHERE a.nome = :nomeAtor")
    Page<SeriesModel> findSeriesByActorName(@Param("nomeAtor") String nomeAtor, Pageable pageable);

    // Busca por título com paginação
    @Query("SELECT s FROM SeriesModel s WHERE LOWER(s.media.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Page<SeriesModel> findSeriesByTitle(@Param("titulo") String titulo, Pageable pageable);

    // Busca por gênero com paginação
    @Query("SELECT s FROM SeriesModel s WHERE LOWER(s.media.genero) LIKE LOWER(CONCAT('%', :genero, '%'))")
    Page<SeriesModel> findSeriesByGenre(@Param("genero") String genero, Pageable pageable);

    // Busca por diretor com paginação
    @Query("SELECT s FROM SeriesModel s WHERE LOWER(s.media.diretor) LIKE LOWER(CONCAT('%', :diretor, '%'))")
    Page<SeriesModel> findSeriesByDirector(@Param("diretor") String diretor, Pageable pageable);
}
