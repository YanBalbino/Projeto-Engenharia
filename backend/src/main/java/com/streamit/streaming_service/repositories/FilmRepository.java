package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.FilmModel;

public interface FilmRepository extends JpaRepository<FilmModel, UUID>{

    @Query("SELECT COUNT(f) > 0 FROM FilmModel f WHERE f.media.titulo = :titulo")
    boolean existsByTitle(@Param("titulo") String titulo);
}
