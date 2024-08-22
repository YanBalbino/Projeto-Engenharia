package com.streamit.streaming_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.model.ProfileModel;

public interface ProfileRepository extends JpaRepository<ProfileModel, UUID> {

	@Query("SELECT p.nome as nome, p.perfilInfantil as perfil, p.generosPreferidos as generos FROM ProfileModel p WHERE p.user.id = :idUser")
	List<ProfileDTO> findProfileDetailsByUser(@Param("idUser") UUID idUser);
}
