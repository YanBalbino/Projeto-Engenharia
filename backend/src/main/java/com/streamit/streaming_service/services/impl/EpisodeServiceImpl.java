package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.AudioMapper;
import com.streamit.streaming_service.mappers.EpisodeMapper;
import com.streamit.streaming_service.mappers.SubtitleMapper;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.model.SubtitleModel;
import com.streamit.streaming_service.repositories.EpisodeRepository;
import com.streamit.streaming_service.services.IEpisodeService;
import com.streamit.streaming_service.services.ISeasonService;
import com.streamit.streaming_service.services.ISeriesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EpisodeServiceImpl implements IEpisodeService {

    private final EpisodeRepository episodeRepository;
    private final ISeasonService seasonService;
    private final ISeriesService seriesService;

    @Override
    public ReturnEpisodeDTO create(CreateEpisodeDTO episodeDto, UUID idSeason) {
    	SeasonModel season = seasonService.findModelById(idSeason);
    	
    	for(EpisodeModel episode : season.getEpisodes()) {
    		if(episode.getNumeroEpisodio().equals(episodeDto.getNumeroEpisodio())) {
    			throw new ResourceAlreadyExistsException("O episódio " + episodeDto.getNumeroEpisodio() + " já existe.");
    		}
    	}
    	
        EpisodeModel episodeModel = EpisodeMapper.toEntity(episodeDto, season);
        
        EpisodeModel savedEpisode = episodeRepository.save(episodeModel);
        
        ReturnEpisodeDTO entityDto = EpisodeMapper.toDto(savedEpisode);
        
        return entityDto;
    }
    
    public EpisodeModel findModelById(UUID id) {
    	EpisodeModel episode = episodeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Temporada não encontrada com o ID: " + id));
    	
    	return episode;
    }

    @Override
    public ReturnEpisodeDTO findById(UUID id) {
        EpisodeModel episode = findModelById(id);
        return EpisodeMapper.toDto(episode);
    }
    
	@Override
	public List<ReturnEpisodeDTO> findAllBySeason(UUID seasonId) {
    	SeasonModel season = seasonService.findModelById(seasonId);
        List<ReturnEpisodeDTO> episodeDtoList = new ArrayList<>();
        
        for (EpisodeModel episode : season.getEpisodes()) {
            episodeDtoList.add(EpisodeMapper.toDto(episode));
        }
        return episodeDtoList;
	}

    @Override
    public List<ReturnEpisodeDTO> findAllBySeries(UUID seriesId) {
    	SeriesModel series = seriesService.findModelById(seriesId);
    	List<SeasonModel> seasonList = series.getSeasons();
    	List<EpisodeModel> episodeList = new ArrayList<>();
        List<ReturnEpisodeDTO> episodeDtoList = new ArrayList<>();
        
        for (SeasonModel season : seasonList) {
        	for(EpisodeModel episode : season.getEpisodes()) {
        		episodeList.add(episode);
        	}
        }
        
        for (EpisodeModel episode : episodeList) {
            episodeDtoList.add(EpisodeMapper.toDto(episode));
        }
        return episodeDtoList;
    }

    @Override
    public ReturnEpisodeDTO update(UpdateEpisodeDTO episodeDto) {
        EpisodeModel entity = findModelById(episodeDto.getId());
        
        EpisodeMapper.toUpdateEntity(episodeDto, entity);
        
        EpisodeModel updatedEpisode = episodeRepository.save(entity);
        
        ReturnEpisodeDTO entityDto = EpisodeMapper.toDto(updatedEpisode);
        
        return entityDto;
    }

    @Override
    public void delete(UUID id) {
        EpisodeModel episode = findModelById(id);
        episode.setTemporada(null);
        episodeRepository.delete(episode);
    }
    
    public EpisodeModel getEpisodeByAudioId(UUID audioId) {
        return episodeRepository.findEpisodeByAudioId(audioId)
        		.orElse(null);
    }
    
    public EpisodeModel getEpisodeBySubtitleId(UUID audioId) {
    	return episodeRepository.findEpisodeBySubtitleId(audioId)
    			.orElse(null);
    }

	@Override
	public ReturnEpisodeDTO addAudio(UUID id, CreateAudioDTO audioDTO) {
		EpisodeModel entity = findModelById(id);
		AudioModel audio = AudioMapper.toEntity(audioDTO);
		List<AudioModel> listAudio = entity.getAudiosDisponiveis();
		if(!listAudio.isEmpty()) {
			listAudio.add(audio);
		}
		ReturnEpisodeDTO dto = EpisodeMapper.toDto(entity);
		return dto;
	}

	@Override
	public ReturnEpisodeDTO addSubtitle(UUID id, CreateSubtitleDTO subtitleDTO) {
		EpisodeModel entity = findModelById(id);
		SubtitleModel subtitle = SubtitleMapper.toEntity(subtitleDTO);
		List<SubtitleModel> listSubtitle = entity.getLegendasDisponiveis();
		if(!listSubtitle.isEmpty()) {
			listSubtitle.add(subtitle);
		}
		ReturnEpisodeDTO dto = EpisodeMapper.toDto(entity);
		return dto;
	}

}
