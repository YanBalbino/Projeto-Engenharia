package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.ActorModel;

public interface ActorRepository extends JpaRepository<ActorModel, UUID>{

	Optional<ActorModel> findById(UUID id);
}
