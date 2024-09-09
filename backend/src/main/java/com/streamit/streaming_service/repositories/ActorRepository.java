package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.ActorModel;

public interface ActorRepository extends JpaRepository<ActorModel, UUID>{

	Page<ActorModel> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
