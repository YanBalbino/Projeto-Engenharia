package com.streamit.streaming_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.model.ProfileModel;

public interface ProfileRepository extends JpaRepository<ProfileModel, UUID> {

	@Query("SELECT p FROM ProfileModel p WHERE p.user.id = :idUser")
	List<ProfileModel> findProfileDetailsByUser(@Param("idUser") UUID idUser);
}
