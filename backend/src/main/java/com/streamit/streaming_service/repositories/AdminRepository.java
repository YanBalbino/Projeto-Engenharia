package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, UUID>{

    @Query("SELECT u FROM AdminModel u JOIN u.person p WHERE p.email = :email")
    Optional<AdminModel> findByEmail(@Param("email") String email);
}
