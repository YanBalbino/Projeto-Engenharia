package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.streamit.streaming_service.model.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, UUID>{

	Optional<UserDetails> findByEmail(String email);
	boolean existsByEmail(String email);
}
