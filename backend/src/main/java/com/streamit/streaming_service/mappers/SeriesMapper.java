package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.season.CreateSeasonDTO;
import com.streamit.streaming_service.dtos.season.ReturnSeasonDTO;
import com.streamit.streaming_service.dtos.series.CreateSeriesDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.dtos.series.UpdateSeriesDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;

public class SeriesMapper {
	
    public static SeriesModel toEntity(CreateSeriesDTO dto, SeriesModel series) {
        MediaModel media = new MediaModel();
        MediaMapper.toEntity(dto.getMedia(), media);
        series.setMedia(media);

        if(dto.getAtores() != null) {
            series.setAtores(ActorMapper.toEntityListToSeries(dto.getAtores(), series));
        }

        List<SeasonModel> seasons = new ArrayList<>();
        for (CreateSeasonDTO seasonDTO : dto.getSeasons()) {
            seasons.add(SeasonMapper.toEntity(seasonDTO, series));
        }

        series.setSeasons(seasons);
        return series;
    }
    
    public static SeriesModel toUpdateEntity(UpdateSeriesDTO dto, SeriesModel series) {
        MediaModel media = series.getMedia();
        MediaMapper.toUpdateEntity(dto.getMedia(), media);

        SeasonMapper.toUpdateEntity(dto.getSeasons(), series.getSeasons());
        ActorMapper.toUpdateEntity(dto.getAtores(), series.getAtores());

        return series;
    }

    public static ReturnSeriesDTO toDto(SeriesModel series) {
        ReturnSeriesDTO dto = new ReturnSeriesDTO();
        dto.setId(series.getId());
        dto.setMedia(MediaMapper.toDto(series.getMedia()));

        List<ReturnSeasonDTO> seasonDtos = new ArrayList<>();
        for (SeasonModel season : series.getSeasons()) {
            seasonDtos.add(SeasonMapper.toDto(season));
        }
        dto.setSeasons(seasonDtos);

        List<ReturnActorDTO> actorDtos = new ArrayList<>();
        for (ActorModel actor : series.getAtores()) {
            actorDtos.add(ActorMapper.toDto(actor));
        }
        dto.setAtores(actorDtos);

        return dto;
    }
}
