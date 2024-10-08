package com.streamit.streaming_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamit.streaming_service.model.CreditCardTokenModel;

public interface CreditCardTokenRepository extends JpaRepository<CreditCardTokenModel, UUID>{

}
