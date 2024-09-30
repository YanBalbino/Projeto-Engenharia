package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.FilmModel;

public interface FilmRepository extends JpaRepository<FilmModel, UUID>{
	
	Optional<FilmModel> findByMediaId(UUID mediaId);

    @Query("SELECT COUNT(f) > 0 FROM FilmModel f WHERE f.media.titulo = :titulo")
    boolean existsByTitle(@Param("titulo") String titulo);
    
    @Query("SELECT COUNT(f) > 0 FROM FilmModel f WHERE f.videoUrl = :videoUrl")
    boolean existsByUrl(@Param("videoUrl") String videoUrl);
    
    @Query("SELECT f FROM FilmModel f JOIN f.audiosDisponiveis a WHERE a.id = :audioId")
    Optional<FilmModel> findFilmByAudioId(@Param("audioId") UUID audioId);
    
    @Query("SELECT f FROM FilmModel f JOIN f.legendasDisponiveis l WHERE l.id = :legendaId")
    Optional<FilmModel> findFilmBySubtitleId(@Param("legendaId") UUID legendaId);
    
    // Busca por ator com paginação
    @Query("SELECT f FROM FilmModel f JOIN f.media.atores a WHERE a.nome = :nomeAtor")
    Page<FilmModel> findFilmsByActorName(@Param("nomeAtor") String nomeAtor, Pageable pageable);

    // Busca por título com paginação
    @Query("SELECT f FROM FilmModel f WHERE LOWER(f.media.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Page<FilmModel> findFilmsByTitle(@Param("titulo") String titulo, Pageable pageable);

    // Busca por gênero com paginação
    @Query("SELECT f FROM FilmModel f WHERE LOWER(f.media.genero) LIKE LOWER(CONCAT('%', :genero, '%'))")
    Page<FilmModel> findFilmsByGenre(@Param("genero") String genero, Pageable pageable);

    // Busca por diretor com paginação
    @Query("SELECT f FROM FilmModel f WHERE LOWER(f.media.diretor) LIKE LOWER(CONCAT('%', :diretor, '%'))")
    Page<FilmModel> findFilmsByDirector(@Param("diretor") String diretor, Pageable pageable);
}
