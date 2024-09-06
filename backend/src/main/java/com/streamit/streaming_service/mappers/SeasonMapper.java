package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.season.ReturnSeasonDTO;
import com.streamit.streaming_service.dtos.season.UpdateSeasonDTO;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;

public class SeasonMapper {
	
	public static void toUpdateEntity(UpdateSeasonDTO seasonDto, SeasonModel seasonModel) {
	    if (seasonDto != null && seasonModel != null) {
	        seasonModel.setSeasonNumber(seasonDto.getSeasonNumber());

	        if (seasonDto.getEpisodes() != null) {
	            for (UpdateEpisodeDTO episodeDto : seasonDto.getEpisodes()) {
	                EpisodeModel episodeModel = EpisodeMapper.findEpisodeModelById(episodeDto.getId(), seasonModel.getEpisodes());
	                if (episodeModel != null) {
	                    EpisodeMapper.toUpdateEntity(episodeDto, episodeModel);
	                }
	            }
	        }
	    }
	}
	
    public static SeasonModel toEntity(CreateSeasonDTO dto, SeriesModel series) {
        SeasonModel season = new SeasonModel();
        season.setSeasonNumber(dto.getSeasonNumber());
        season.setSerie(series);
        
        List<EpisodeModel> episodes = new ArrayList<>();
        for (CreateEpisodeDTO episodeDTO : dto.getEpisodes()) {
            episodes.add(EpisodeMapper.toEntity(episodeDTO, season));
        }
        season.setEpisodes(episodes);
        
        return season;
    }

    public static ReturnSeasonDTO toDto(SeasonModel season) {
        ReturnSeasonDTO dto = new ReturnSeasonDTO();
        dto.setId(season.getId());
        dto.setSeasonNumber(season.getSeasonNumber());

        List<ReturnEpisodeDTO> episodeDtos = new ArrayList<>();
        for (EpisodeModel episode : season.getEpisodes()) {
            episodeDtos.add(EpisodeMapper.toDto(episode));
        }
        dto.setEpisodes(episodeDtos);

        return dto;
    }
    
    public static SeasonModel findSeasonModelById(UUID id, List<SeasonModel> seasons) {
        for (SeasonModel season : seasons) {
            if (season.getId().equals(id)) {
                return season;
            }
        }
        return null;
    }
}

