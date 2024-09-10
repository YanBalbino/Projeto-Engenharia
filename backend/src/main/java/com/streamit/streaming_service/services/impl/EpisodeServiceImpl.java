package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.EpisodeMapper;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.repositories.EpisodeRepository;
import com.streamit.streaming_service.services.IEpisodeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EpisodeServiceImpl implements IEpisodeService {

    private final EpisodeRepository episodeRepository;
    private final SeasonServiceImpl seasonServiceImpl;
    private final SeriesServiceImpl seriesServiceImpl;

    @Override
    public ReturnEpisodeDTO create(CreateEpisodeDTO episodeDto, UUID idSeason) {
    	SeasonModel season = seasonServiceImpl.findModelById(idSeason);
    	
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
    	SeasonModel season = seasonServiceImpl.findModelById(seasonId);
        List<ReturnEpisodeDTO> episodeDtoList = new ArrayList<>();
        
        for (EpisodeModel episode : season.getEpisodes()) {
            episodeDtoList.add(EpisodeMapper.toDto(episode));
        }
        return episodeDtoList;
	}

    @Override
    public List<ReturnEpisodeDTO> findAllBySeries(UUID seriesId) {
    	SeriesModel series = seriesServiceImpl.findModelById(seriesId);
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
    public ReturnEpisodeDTO update(UUID id, UpdateEpisodeDTO episodeDto) {
        EpisodeModel entity = episodeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Temporada não encontrada com o ID: " + id));
        
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

}
