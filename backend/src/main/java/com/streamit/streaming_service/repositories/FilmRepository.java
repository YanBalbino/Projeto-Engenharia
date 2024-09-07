package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.FilmModel;

public interface FilmRepository extends JpaRepository<FilmModel, UUID>{

    @Query("SELECT COUNT(f) > 0 FROM FilmModel f WHERE f.media.titulo = :titulo")
    boolean existsByTitle(@Param("titulo") String titulo);
    
    @Query("SELECT f FROM FilmModel f JOIN f.audiosDisponiveis a WHERE a.id = :audioId")
    Optional<FilmModel> findFilmByAudioId(@Param("audioId") UUID audioId);
    
    @Query("SELECT f FROM FilmModel f JOIN f.legendasDisponiveis a WHERE a.id = :legendaId")
    Optional<FilmModel> findFilmBySubtitleId(@Param("legendaId") UUID legendaId);
}
