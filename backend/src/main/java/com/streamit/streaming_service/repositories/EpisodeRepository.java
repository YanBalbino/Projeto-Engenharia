package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.EpisodeModel;

public interface EpisodeRepository extends JpaRepository<EpisodeModel, UUID> {

}
