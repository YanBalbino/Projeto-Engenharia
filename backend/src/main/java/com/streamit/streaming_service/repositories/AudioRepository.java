package com.streamit.streaming_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.AudioModel;

public interface AudioRepository extends JpaRepository<AudioModel, UUID> {

    @Query("SELECT a FROM AudioModel a JOIN FilmModel f ON a MEMBER OF f.audiosDisponiveis WHERE f.id = :filmId")
    List<AudioModel> findAudiosByFilmId(@Param("filmId") UUID filmId);

    @Query("SELECT a FROM AudioModel a JOIN EpisodeModel e ON a MEMBER OF e.audiosDisponiveis WHERE e.id = :episodeId")
    List<AudioModel> findAudiosByEpisodeId(@Param("episodeId") UUID episodeId);

}
