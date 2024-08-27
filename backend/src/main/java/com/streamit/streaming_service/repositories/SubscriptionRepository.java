package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.SubscriptionModel;

public interface SubscriptionRepository extends JpaRepository<SubscriptionModel, UUID>{

	Optional<SubscriptionModel> findByUserId(UUID userId);
}
