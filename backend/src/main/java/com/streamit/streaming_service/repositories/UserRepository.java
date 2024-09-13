package com.streamit.streaming_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID>{

    @Query("SELECT u FROM UserModel u JOIN u.person p WHERE p.email = :email")
    Optional<UserModel> findByEmail(@Param("email") String email);
}
