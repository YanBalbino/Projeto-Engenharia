package com.streamit.streaming_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.ActorModel;

public interface ActorRepository extends JpaRepository<ActorModel, UUID>{

	Page<ActorModel> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
    @Query("SELECT a FROM ActorModel a JOIN FilmModel f ON a MEMBER OF f.atores WHERE f.id = :filmId")
    List<ActorModel> findActorsByFilmId(@Param("filmId") UUID filmId);

    @Query("SELECT a FROM ActorModel a JOIN SeriesModel s ON a MEMBER OF s.atores WHERE s.id = :seriesId")
    List<ActorModel> findActorsBySeriesId(@Param("seriesId") UUID seriesId);
}
