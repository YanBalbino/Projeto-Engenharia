package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;

public interface IMediaService {

	List<ReturnMediaDTO> findAll(Pageable pageable, UUID profileId);
    List<ReturnMediaDTO> findMediaByActorName(String nomeAtor, Pageable pageable, UUID profileId);
    List<ReturnMediaDTO> findMediaByTitle(String titulo, Pageable pageable, UUID profileId);
    List<ReturnMediaDTO> findMediaByGenre(String genero, Pageable pageable, UUID profileId);
    List<ReturnMediaDTO> findMediaByDirector(String diretor, Pageable pageable, UUID profileId);
	ReturnMediaDTO findById(UUID id);
	ReturnMediaDTO update(UpdateMediaDTO mediaDto);
}
