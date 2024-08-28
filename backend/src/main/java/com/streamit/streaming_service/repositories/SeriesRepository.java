package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.SeriesModel;

public interface SeriesRepository extends JpaRepository<SeriesModel, UUID>{

	SeriesModel findByVideoUrl(String videoURL);
}
