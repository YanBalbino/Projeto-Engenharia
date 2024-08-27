package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.PaymentModel;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID>{

	Optional<PaymentModel> findByUserId(UUID userId);
}
