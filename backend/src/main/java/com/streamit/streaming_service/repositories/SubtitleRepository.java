package com.streamit.streaming_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.SubtitleModel;

public interface SubtitleRepository extends JpaRepository<SubtitleModel, UUID>{

	@Query("SELECT s FROM SubtitleModel s JOIN FilmModel f ON s MEMBER OF f.legendasDisponiveis WHERE f.id = :filmId")
	List<SubtitleModel> findSubtitlesByFilmId(@Param("filmId") UUID filmId);

	@Query("SELECT s FROM SubtitleModel s JOIN EpisodeModel e ON s MEMBER OF e.legendasDisponiveis WHERE e.id = :episodeId")
	List<SubtitleModel> findSubtitlesByEpisodeId(@Param("episodeId") UUID episodeId);

}
