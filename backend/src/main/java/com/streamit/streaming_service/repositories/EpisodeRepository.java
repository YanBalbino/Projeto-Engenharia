package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.EpisodeModel;

public interface EpisodeRepository extends JpaRepository<EpisodeModel, UUID> {

    @Query("SELECT e FROM EpisodeModel e JOIN e.audiosDisponiveis a WHERE a.id = :audioId")
    Optional<EpisodeModel> findEpisodeByAudioId(@Param("audioId") UUID audioId);
    
    @Query("SELECT e FROM EpisodeModel e JOIN e.legendasDisponiveis l WHERE l.id = :legendaId")
    Optional<EpisodeModel> findEpisodeBySubtitleId(@Param("legendaId") UUID legendaId);
}
