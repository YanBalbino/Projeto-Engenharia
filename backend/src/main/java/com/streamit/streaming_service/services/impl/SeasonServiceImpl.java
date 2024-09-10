package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.season.ReturnSeasonDTO;
import com.streamit.streaming_service.dtos.season.UpdateSeasonDTO;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.SeasonMapper;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.repositories.SeasonRepository;
import com.streamit.streaming_service.services.ISeasonService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeasonServiceImpl implements ISeasonService {

    private final SeasonRepository seasonRepository;
    private final SeriesServiceImpl seriesServiceImpl;

    @Override
    public ReturnSeasonDTO create(CreateSeasonDTO seasonDto, UUID idSeries) {
    	if(seasonDto.getEpisodes().isEmpty()) {
    		throw new IllegalArgumentException("A temporada deve possuir pelo menos 1 episódio.");
    	}
    	
    	SeriesModel series = seriesServiceImpl.findModelById(idSeries);
    	
    	for(SeasonModel season : series.getSeasons()) {
    		if(season.getSeasonNumber().equals(seasonDto.getSeasonNumber())) {
    			throw new ResourceNotFoundException("A temporada " + seasonDto.getSeasonNumber() + " já existe.");
    		}
    	}
    	
        SeasonModel seasonModel = SeasonMapper.toEntity(seasonDto, series);
        
        SeasonModel savedSeason = seasonRepository.save(seasonModel);
        
        ReturnSeasonDTO entityDto = SeasonMapper.toDto(savedSeason);
        
        return entityDto;
    }
    
    public SeasonModel findModelById(UUID id) {
    	SeasonModel season = seasonRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Temporada não encontrada com o ID: " + id));
    	
    	return season;
    }

    @Override
    public ReturnSeasonDTO findById(UUID id) {
        SeasonModel season = findModelById(id);
        return SeasonMapper.toDto(season);
    }

    @Override
    public List<ReturnSeasonDTO> findAllBySeries(UUID seriesId) {
    	SeriesModel series = seriesServiceImpl.findModelById(seriesId);
    	List<SeasonModel> seasonList = series.getSeasons();
        List<ReturnSeasonDTO> seasonDtoList = new ArrayList<>();
        
        for (SeasonModel season : seasonList) {
            seasonDtoList.add(SeasonMapper.toDto(season));
        }
        return seasonDtoList;
    }

    @Override
    public ReturnSeasonDTO update(UUID id, UpdateSeasonDTO seasonDto) {
        SeasonModel entity = seasonRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Temporada não encontrada com o ID: " + id));
        
        SeasonMapper.toUpdateEntity(seasonDto, entity);
        
        SeasonModel updatedSeason = seasonRepository.save(entity);
        
        ReturnSeasonDTO entityDto = SeasonMapper.toDto(updatedSeason);
        
        return entityDto;
    }

    @Override
    public void delete(UUID id) {
        SeasonModel season = findModelById(id);
        season.setSerie(null);
        seasonRepository.delete(season);
    }
}
