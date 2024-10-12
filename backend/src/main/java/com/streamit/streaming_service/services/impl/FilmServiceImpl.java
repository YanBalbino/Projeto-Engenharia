package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ActorMapper;
import com.streamit.streaming_service.mappers.AudioMapper;
import com.streamit.streaming_service.mappers.FilmMapper;
import com.streamit.streaming_service.mappers.SubtitleMapper;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SubtitleModel;
import com.streamit.streaming_service.omdb.MediaOMDB;
import com.streamit.streaming_service.repositories.FilmRepository;
import com.streamit.streaming_service.services.IActorService;
import com.streamit.streaming_service.services.IFilmService;
import com.streamit.streaming_service.services.IMediaOMDBService;
import com.streamit.streaming_service.services.IProfileService;
import com.streamit.streaming_service.strategy.profile.AgeRestrictionStrategy;
import com.streamit.streaming_service.strategy.profile.ChildProfileStrategy;
import com.streamit.streaming_service.strategy.profile.RegularProfileStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements IFilmService {

    private FilmRepository filmRepository;
    private IActorService actorService;
    private IProfileService profileService;
    private IMediaOMDBService omdbService;
    
    //supor que filmes sem atores podem ser criados
    @Override
    public ReturnFilmDTO create(String titulo, CreateFilmDTO filmDto) {
    	StringBuilder sb = new StringBuilder();
    	if (filmRepository.existsByTitle(titulo)) {
    	    sb.append("Filme já cadastrado com o título: ").append(titulo).append(". ");
    	}

    	if (filmRepository.existsByUrl(filmDto.getVideoUrl())) {
    	    sb.append("Filme já cadastrado com a URL: ").append(filmDto.getVideoUrl()).append(". ");
    	}
    	
    	MediaOMDB omdb = omdbService.getMedia(titulo);

    	if (sb.length() > 0) {
    	    throw new ResourceAlreadyExistsException(sb.toString().trim());
    	}
    	
    	FilmModel entityMapped = FilmMapper.toEntity(filmDto, new FilmModel(), omdb);
    	
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = filmDto.getMedia().getActorIds();
    	if(!actorIds.isEmpty()) {
    		List<ActorModel> actors = new ArrayList<>();
    		for(UUID actorId : actorIds) {
    			ActorModel actor = actorService.findModelById(actorId);
    			actors.add(actor);
    		}
    		if(entityMapped.getMedia().getAtores().isEmpty()) {
    			entityMapped.getMedia().setAtores(actors);
    		}else {
    			entityMapped.getMedia().getAtores().addAll(actors);
    		}
    	}
    	
    	ReturnFilmDTO entityDto = FilmMapper.toDto(filmRepository.save(entityMapped));
        return entityDto; 
    }

	@Override
	public ReturnFilmDTO findById(UUID id) {
		FilmModel entity = findModelById(id);
    	ReturnFilmDTO entityDto = FilmMapper.toDto(entity);
        return entityDto; 
	}
	
	public FilmModel findModelById(UUID id) {
		return filmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id " + id));
	}

	@Override
	public Page<ReturnFilmDTO> findByGenre(String genre, Pageable pageable, UUID profileId) {
	    ProfileModel profile = profileService.findProfileModelById(profileId);
	    AgeRestrictionStrategy ageRestrictionStrategy;

	    if (profile.isPerfilInfantil()) {
	        ageRestrictionStrategy = new ChildProfileStrategy();
	    } else {
	        ageRestrictionStrategy = new RegularProfileStrategy();
	    }

	    Page<FilmModel> filmPage = filmRepository.findFilmsByGenre(genre, pageable);
	    List<ReturnFilmDTO> dtos = new ArrayList<>();

	    for (FilmModel entity : filmPage.getContent()) {
	        ReturnFilmDTO entityDto = FilmMapper.toDto(entity);
	        dtos.add(entityDto);
	    }
	    
	    // Aplicar filtro de restrição de idade
	    List<ReturnFilmDTO> filteredDtos = ageRestrictionStrategy.filterFilm(dtos);

	    // Criar um Page de ReturnFilmDTO
	    return new PageImpl<>(filteredDtos, pageable, filmPage.getTotalElements());
	}

	@Override
	public Page<ReturnFilmDTO> findAll(Pageable pageable, UUID profileId) {
	    ProfileModel profile = profileService.findProfileModelById(profileId);
	    AgeRestrictionStrategy ageRestrictionStrategy;

	    if (profile.isPerfilInfantil()) {
	        ageRestrictionStrategy = new ChildProfileStrategy();
	    } else {
	        ageRestrictionStrategy = new RegularProfileStrategy();
	    }

	    Page<FilmModel> filmPage = filmRepository.findAll(pageable);
	    List<ReturnFilmDTO> dtos = new ArrayList<>();

	    for (FilmModel entity : filmPage.getContent()) {
	        ReturnFilmDTO entityDto = FilmMapper.toDto(entity);
	        dtos.add(entityDto);
	    }

	    // Aplicar filtro de restrição de idade
	    List<ReturnFilmDTO> filteredDtos = ageRestrictionStrategy.filterFilm(dtos);

	    // Criar um Page de ReturnFilmDTO
	    return new PageImpl<>(filteredDtos, pageable, filmPage.getTotalElements());
	}

	@Override
	public ReturnFilmDTO update(UpdateFilmDTO filmDto) {
		FilmModel entity = findModelById(filmDto.getId());
		List<FilmModel> entities = filmRepository.findAll();
		for(FilmModel film : entities) {
			if(film.getMedia().getTitulo().equals(filmDto.getMedia().getTitulo()) && !entity.getId().equals(film.getId())) {
				throw new ResourceAlreadyExistsException("Filme já cadastrado com esse título.");
			}
		}
		FilmMapper.toUpdateEntity(filmDto, entity);
    	// lógica para adicionar atores que já existem no bd
    	List<UUID> actorIds = filmDto.getMedia().getActorIds();
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
    	ReturnFilmDTO entityDto = FilmMapper.toDto(filmRepository.save(entity));
        return entityDto; 
	}

	@Override
	public void delete(UUID id) {
	    FilmModel film = findModelById(id);
	    filmRepository.delete(film);
	}
	
	@Override
    public FilmModel getFilmByAudioId(UUID audioId) {
        return filmRepository.findFilmByAudioId(audioId)
        		.orElse(null);
    }
    
	@Override
    public FilmModel getFilmBySubtitleId(UUID audioId) {
    	return filmRepository.findFilmBySubtitleId(audioId)
    			.orElse(null);
    }

	@Override
	public ReturnFilmDTO addAudio(UUID id, CreateAudioDTO audioDto) {
		FilmModel entity = findModelById(id);
		AudioModel audio = AudioMapper.toEntity(audioDto);
		List<AudioModel> listAudio = entity.getAudiosDisponiveis();
		if(!listAudio.isEmpty()) {
			listAudio.add(audio);
		}
		ReturnFilmDTO dto = FilmMapper.toDto(entity);
		return dto;
	}

	@Override
	public ReturnFilmDTO addSubtitle(UUID id, CreateSubtitleDTO subtitleDto) {
		FilmModel entity = findModelById(id);
		SubtitleModel subtitle = SubtitleMapper.toEntity(subtitleDto);
		List<SubtitleModel> listSubtitle = entity.getLegendasDisponiveis();
		if(!listSubtitle.isEmpty()) {
			listSubtitle.add(subtitle);
		}
		ReturnFilmDTO dto = FilmMapper.toDto(entity);
		return dto;
	}

	@Override
	public ReturnFilmDTO addActor(UUID id, CreateActorDTO actorDto) {
		FilmModel entity = findModelById(id);
		ActorModel actor = ActorMapper.toEntity(actorDto, entity.getMedia());
		List<ActorModel> listActor = entity.getMedia().getAtores();
		if(!listActor.isEmpty()) {
			listActor.add(actor);
		}
		ReturnFilmDTO dto = FilmMapper.toDto(entity);
		return dto;
	}

	@Override
	public ReturnFilmDTO findByMedia(UUID mediaId) {
		FilmModel entity = filmRepository.findByMediaId(mediaId)
				.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id de mídia " + mediaId));
		ReturnFilmDTO dto = FilmMapper.toDto(entity);
		return dto;
	}

}
