package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.dtos.media.UpdateMediaDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.MediaMapper;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.repositories.MediaRepository;
import com.streamit.streaming_service.services.IMediaService;
import com.streamit.streaming_service.services.IProfileService;
import com.streamit.streaming_service.strategy.profile.AgeRestrictionStrategy;
import com.streamit.streaming_service.strategy.profile.ChildProfileStrategy;
import com.streamit.streaming_service.strategy.profile.RegularProfileStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements IMediaService {

    private final MediaRepository mediaRepository;
    private final IProfileService profileService;

    @Override
    public Page<ReturnMediaDTO> findAll(UUID profileId, Pageable pageable) {
        ProfileModel profile = profileService.findProfileModelById(profileId);
        // Definindo a estratégia com base no tipo de perfil
        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<MediaModel> mediaPage = mediaRepository.findAll(pageable);
        List<ReturnMediaDTO> dtoList = new ArrayList<>();

        for (MediaModel media : mediaPage.getContent()) {
            ReturnMediaDTO dto = MediaMapper.toDto(media);
            dtoList.add(dto);
        }

        // Aplicando o filtro de faixa etária
        List<ReturnMediaDTO> filteredDtos = ageRestrictionStrategy.filterMedia(dtoList);

        // Criar um Page de ReturnMediaDTO
        return new PageImpl<>(filteredDtos, pageable, mediaPage.getTotalElements());
    }

    // Busca de mídia por ator
    @Override
    public Page<ReturnMediaDTO> findMediaByActorName(String nomeAtor, Pageable pageable, UUID profileId) {
        ProfileModel profile = profileService.findProfileModelById(profileId);

        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<MediaModel> mediaPage = mediaRepository.findByActorName(nomeAtor, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();

        for (MediaModel media : mediaPage.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }

        // Aplicando o filtro de faixa etária
        List<ReturnMediaDTO> filteredDtos = ageRestrictionStrategy.filterMedia(mediaDtos);

        // Criar um Page de ReturnMediaDTO
        return new PageImpl<>(filteredDtos, pageable, mediaPage.getTotalElements());
    }

    // Busca de mídia por título
    @Override
    public Page<ReturnMediaDTO> findMediaByTitle(String titulo, Pageable pageable, UUID profileId) {
        ProfileModel profile = profileService.findProfileModelById(profileId);

        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<MediaModel> mediaPage = mediaRepository.findByTitle(titulo, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();

        for (MediaModel media : mediaPage.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }

        // Aplicando o filtro de faixa etária
        List<ReturnMediaDTO> filteredDtos = ageRestrictionStrategy.filterMedia(mediaDtos);

        // Criar um Page de ReturnMediaDTO
        return new PageImpl<>(filteredDtos, pageable, mediaPage.getTotalElements());
    }

    // Busca de mídia por gênero
    @Override
    public Page<ReturnMediaDTO> findMediaByGenre(String genero, Pageable pageable, UUID profileId) {
        ProfileModel profile = profileService.findProfileModelById(profileId);

        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<MediaModel> mediaPage = mediaRepository.findByGenre(genero, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();

        for (MediaModel media : mediaPage.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }

        // Aplicando o filtro de faixa etária
        List<ReturnMediaDTO> filteredDtos = ageRestrictionStrategy.filterMedia(mediaDtos);

        // Criar um Page de ReturnMediaDTO
        return new PageImpl<>(filteredDtos, pageable, mediaPage.getTotalElements());
    }

    // Busca de mídia por diretor
    @Override
    public Page<ReturnMediaDTO> findMediaByDirector(String diretor, Pageable pageable, UUID profileId) {
        ProfileModel profile = profileService.findProfileModelById(profileId);

        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<MediaModel> mediaPage = mediaRepository.findByDirector(diretor, pageable);
        List<ReturnMediaDTO> mediaDtos = new ArrayList<>();

        for (MediaModel media : mediaPage.getContent()) {
            mediaDtos.add(MediaMapper.toDto(media));
        }

        // Aplicando o filtro de faixa etária
        List<ReturnMediaDTO> filteredDtos = ageRestrictionStrategy.filterMedia(mediaDtos);

        // Criar um Page de ReturnMediaDTO
        return new PageImpl<>(filteredDtos, pageable, mediaPage.getTotalElements());
    }


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
    public ReturnMediaDTO update(UpdateMediaDTO mediaDto) {
    	MediaModel entity = findModelById(mediaDto.getId());
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
