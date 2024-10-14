package com.streamit.streaming_service.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;

public interface IMediaService {

	Page<ReturnMediaDTO> findAll(UUID profileId, Pageable pageable);
    Page<ReturnMediaDTO> findMediaByActorName(String nomeAtor, Pageable pageable, UUID profileId);
    Page<ReturnMediaDTO> findMediaByTitle(String titulo, Pageable pageable, UUID profileId);
    Page<ReturnMediaDTO> findMediaByGenre(String genero, Pageable pageable, UUID profileId);
    Page<ReturnMediaDTO> findMediaByDirector(String diretor, Pageable pageable, UUID profileId);
	ReturnMediaDTO findById(UUID id);
	ReturnMediaDTO update(UpdateMediaDTO mediaDto);
}
