package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.mappers.MediaMapper;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.repositories.MediaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalogServiceImpl {

    private final MediaRepository mediaRepository;

    // Busca por ator
    public List<ReturnMediaDTO> findMediaByActorName(String nomeAtor, Pageable pageable) {
        Page<MediaModel> mediaList = mediaRepository.findByActorName(nomeAtor, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();
        
        for (MediaModel media : mediaList.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }
        
        return mediaDtos;
    }

    // Busca por título
    public List<ReturnMediaDTO> findMediaByTitle(String titulo, Pageable pageable) {
        Page<MediaModel> mediaList = mediaRepository.findByTitle(titulo, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();
        
        for (MediaModel media : mediaList.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }
        
        return mediaDtos;
    }

    // Busca por gênero
    public List<ReturnMediaDTO> findMediaByGenre(String genero, Pageable pageable) {
        Page<MediaModel> mediaList = mediaRepository.findByGenre(genero, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();
        
        for (MediaModel media : mediaList.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }
        
        return mediaDtos;
    }

    // Busca por diretor
    public List<ReturnMediaDTO> findMediaByDirector(String diretor, Pageable pageable) {
        Page<MediaModel> mediaList = mediaRepository.findByDirector(diretor, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();
        
        for (MediaModel media : mediaList.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }
        
        return mediaDtos;
    }
}

