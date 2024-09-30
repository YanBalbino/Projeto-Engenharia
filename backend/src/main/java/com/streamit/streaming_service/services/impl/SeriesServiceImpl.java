package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ActorMapper;
import com.streamit.streaming_service.mappers.SeasonMapper;
import com.streamit.streaming_service.mappers.SeriesMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.repositories.SeriesRepository;
import com.streamit.streaming_service.services.IActorService;
import com.streamit.streaming_service.services.IProfileService;
import com.streamit.streaming_service.services.ISeriesService;
import com.streamit.streaming_service.strategy.profile.AgeRestrictionStrategy;
import com.streamit.streaming_service.strategy.profile.ChildProfileStrategy;
import com.streamit.streaming_service.strategy.profile.RegularProfileStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeriesServiceImpl implements ISeriesService {

    private SeriesRepository seriesRepository;
    private IActorService actorService;
    private final IProfileService profileService;

    @Override
    public ReturnSeriesDTO create(CreateSeriesDTO seriesDto) {
        if (seriesRepository.existsByTitle(seriesDto.getMedia().getTitulo())) {
            throw new ResourceAlreadyExistsException("Série já cadastrada.");
        }
        SeriesModel entity = SeriesMapper.toEntity(seriesDto, new SeriesModel());
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = seriesDto.getMedia().getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorService.findModelById(actorId);
    			actors.add(actor);
    		}
    		if(entity.getMedia().getAtores().isEmpty()) {
    			entity.getMedia().setAtores(actors);
    		}else {
    			entity.getMedia().getAtores().addAll(actors);
    		}
    	}
    	ReturnSeriesDTO entityDto = SeriesMapper.toDto(seriesRepository.save(entity));
        return entityDto; 
    }
 
    public SeriesModel findModelById(UUID id) {
    	return seriesRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Série não encontrada com id " + id));
    }

	@Override
	public ReturnSeriesDTO findById(UUID id) {
		SeriesModel entity = findModelById(id);
		ReturnSeriesDTO entityDto = SeriesMapper.toDto(entity);
        return entityDto; 
	}
	
    @Override
    public List<ReturnSeriesDTO> findByGenre(String genre, Pageable pageable, UUID profileId) {
        ProfileModel profile = profileService.findProfileModelById(profileId);
        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<SeriesModel> seriesPage = seriesRepository.findSeriesByGenre(genre, pageable);
        List<ReturnSeriesDTO> dtos = new ArrayList<>();

        for (SeriesModel entity : seriesPage.getContent()) {
            ReturnSeriesDTO entityDto = SeriesMapper.toDto(entity);
            dtos.add(entityDto);
        }

        // Aplicar filtro de restrição de idade
        return ageRestrictionStrategy.filterSeries(dtos);
    }

    // Verificação de perfil infantil para todas as séries
    @Override
    public List<ReturnSeriesDTO> findAll(Pageable pageable, UUID profileId) {
        ProfileModel profile = profileService.findProfileModelById(profileId);
        AgeRestrictionStrategy ageRestrictionStrategy;

        if (profile.isPerfilInfantil()) {
            ageRestrictionStrategy = new ChildProfileStrategy();
        } else {
            ageRestrictionStrategy = new RegularProfileStrategy();
        }

        Page<SeriesModel> seriesPage = seriesRepository.findAll(pageable);
        List<ReturnSeriesDTO> dtos = new ArrayList<>();

        for (SeriesModel entity : seriesPage.getContent()) {
            ReturnSeriesDTO entityDto = SeriesMapper.toDto(entity);
            dtos.add(entityDto);
        }

        // Aplicar filtro de restrição de idade
        return ageRestrictionStrategy.filterSeries(dtos);
    }

	@Override
	public ReturnSeriesDTO update(UpdateSeriesDTO seriesDto) {
	    SeriesModel entity = findModelById(seriesDto.getId());

	    List<SeriesModel> entities = seriesRepository.findAll();
	    for (SeriesModel series : entities) {
	        if (series.getMedia().getTitulo().equals(seriesDto.getMedia().getTitulo()) && !entity.getId().equals(series.getId())) {
	            throw new ResourceAlreadyExistsException("Série já cadastrada com esse título.");
	        }
	    }
	    SeriesMapper.toUpdateEntity(seriesDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = seriesDto.getMedia().getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorService.findModelById(actorId);
    			actors.add(actor);
    		}
    		if(entity.getMedia().getAtores().isEmpty()) {
    			entity.getMedia().setAtores(actors);
    		}else {
    			entity.getMedia().getAtores().addAll(actors);
    		}
    	}
    	ReturnSeriesDTO entityDto = SeriesMapper.toDto(seriesRepository.save(entity));
        return entityDto; 
	}

	@Override
	public void delete(UUID id) {
		SeriesModel entity = findModelById(id);
	    seriesRepository.delete(entity);
	}
	
	@Override
	public ReturnSeriesDTO addSeason(UUID id, CreateSeasonDTO seasonDto) {
		SeriesModel entity = findModelById(id);
		SeasonModel season = SeasonMapper.toEntity(seasonDto, entity);
		List<SeasonModel> listSeason = entity.getSeasons();
		if(!listSeason.isEmpty()) {
			listSeason.add(season);
		}
		ReturnSeriesDTO dto = SeriesMapper.toDto(entity);
		return dto;
	}

	@Override
	public ReturnSeriesDTO addActor(UUID id, CreateActorDTO actorDto) {
		SeriesModel entity = findModelById(id);
		ActorModel actor = ActorMapper.toEntity(actorDto, entity.getMedia());
		List<ActorModel> listActor = entity.getMedia().getAtores();
		if(!listActor.isEmpty()) {
			listActor.add(actor);
		}
		ReturnSeriesDTO dto = SeriesMapper.toDto(entity);
		return dto;
	}
	
	@Override
	public ReturnSeriesDTO findByMedia(UUID mediaId) {
		SeriesModel entity = seriesRepository.findByMediaId(mediaId)
				.orElseThrow(() -> new ResourceNotFoundException("Série não encontrada com id de mídia " + mediaId));
		ReturnSeriesDTO dto = SeriesMapper.toDto(entity);
		return dto;
	}
}
