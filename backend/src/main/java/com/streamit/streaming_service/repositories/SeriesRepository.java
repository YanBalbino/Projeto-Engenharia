package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.SeriesModel;

public interface SeriesRepository extends JpaRepository<SeriesModel, UUID>{

    @Query("SELECT COUNT(s) > 0 FROM SeriesModel s WHERE s.media.titulo = :titulo")
    boolean existsByTitle(@Param("titulo") String titulo);
}
