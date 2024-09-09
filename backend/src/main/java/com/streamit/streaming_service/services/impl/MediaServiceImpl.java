package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.MediaMapper;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.repositories.MediaRepository;
import com.streamit.streaming_service.services.IMediaService;

@Service
public class MediaServiceImpl implements IMediaService {

    @Autowired
    private MediaRepository mediaRepository;
    
    public MediaModel findModelById(UUID id) {
    	return mediaRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Mídia não encontrada com id " + id));
    }

    @Override
    public ReturnMediaDTO findById(UUID id) {
        MediaModel media = findModelById(id);
        ReturnMediaDTO entityDto = MediaMapper.toDto(media);
        return entityDto;
    }

    @Override
    public List<ReturnMediaDTO> findAll(Pageable pageable) {
        Page<MediaModel> mediaList = mediaRepository.findAll(pageable);
        List<ReturnMediaDTO> dtoList = new ArrayList<>();
        
        for (MediaModel media : mediaList) {
            ReturnMediaDTO dto = MediaMapper.toDto(media);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public ReturnMediaDTO update(UUID id, UpdateMediaDTO mediaDto) {
    	MediaModel entity = findModelById(id);
	    List<MediaModel> entities = mediaRepository.findAll();
	    for (MediaModel media : entities) {
	        if (media.getTitulo().equals(mediaDto.getTitulo()) && !entity.getId().equals(media.getId())) {
	            throw new ResourceAlreadyExistsException("Mídia já cadastrada com esse título.");
	        }
	    }
        
        MediaMapper.toUpdateEntity(mediaDto, entity); 
        MediaModel updatedMedia = mediaRepository.save(entity); 
        
        return MediaMapper.toDto(updatedMedia); 
    }
}
