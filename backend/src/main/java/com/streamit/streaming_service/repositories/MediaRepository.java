package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.MediaModel;

public interface MediaRepository extends JpaRepository<MediaModel, UUID>{

    @Query("SELECT COUNT(f) > 0 FROM MediaModel f WHERE f.titulo = :titulo")
    boolean existsByTitle(@Param("titulo") String titulo);
    
    // Busca por ator com paginação
    @Query("SELECT m FROM MediaModel m JOIN m.atores a WHERE a.nome = :nomeAtor")
    Page<MediaModel> findByActorName(@Param("nomeAtor") String nomeAtor, Pageable pageable);

    // Busca por título com paginação
    @Query("SELECT m FROM MediaModel m WHERE LOWER(m.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Page<MediaModel> findByTitle(@Param("titulo") String titulo, Pageable pageable);

    // Busca por gênero com paginação
    @Query("SELECT m FROM MediaModel m WHERE LOWER(m.genero) LIKE LOWER(CONCAT('%', :genero, '%'))")
    Page<MediaModel> findByGenre(@Param("genero") String genero, Pageable pageable);

    // Busca por diretor com paginação
    @Query("SELECT m FROM MediaModel m WHERE LOWER(m.diretor) LIKE LOWER(CONCAT('%', :diretor, '%'))")
    Page<MediaModel> findByDirector(@Param("diretor") String diretor, Pageable pageable);
}
