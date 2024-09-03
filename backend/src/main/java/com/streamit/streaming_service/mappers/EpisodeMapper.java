package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SubtitleModel;

public class EpisodeMapper {
	
    public static void toUpdateEntity(List<UpdateEpisodeDTO> episodesDto, List<EpisodeModel> episodesModel) {
        if (episodesDto != null) {
            for (UpdateEpisodeDTO episodeDto : episodesDto) {
                Integer episodeNumber = episodeDto.getNumeroEpisodio();
                for (EpisodeModel episodeModel : episodesModel) {
                    if (episodeModel.getNumeroEpisodio().equals(episodeNumber)) {
                        episodeModel.setTituloEpisodio(episodeDto.getTituloEpisodio());
                        episodeModel.setDuracaoEpisodio(episodeDto.getDuracaoEpisodio());
                        episodeModel.setVideoUrl(episodeDto.getVideoUrl());

                        SubtitleMapper.toUpdateEntity(episodeDto.getLegendasDisponiveis(), episodeModel.getLegendasDisponiveis());
                        AudioMapper.toUpdateEntity(episodeDto.getAudiosDisponiveis(), episodeModel.getAudiosDisponiveis());
                        break;
                    }
                }
            }
        }
    }
	
    public static EpisodeModel toEntity(CreateEpisodeDTO dto, SeasonModel season) {
        EpisodeModel episode = new EpisodeModel();
        episode.setTemporada(season);
        episode.setNumeroEpisodio(dto.getNumeroEpisodio());
        episode.setTituloEpisodio(dto.getTituloEpisodio());
        episode.setDuracaoEpisodio(dto.getDuracaoEpisodio());
        episode.setVideoUrl(dto.getVideoUrl());

        if (dto.getLegendasDisponiveis() != null) {
            episode.setLegendasDisponiveis(SubtitleMapper.toEntityList(dto.getLegendasDisponiveis()));
        }

        if (dto.getAudiosDisponiveis() != null) {
            episode.setAudiosDisponiveis(AudioMapper.toEntityList(dto.getAudiosDisponiveis()));
        }

        return episode;
    }

    public static ReturnEpisodeDTO toDto(EpisodeModel episode) {
        ReturnEpisodeDTO dto = new ReturnEpisodeDTO();
        dto.setId(episode.getId());
        dto.setNumeroEpisodio(episode.getNumeroEpisodio());
        dto.setTituloEpisodio(episode.getTituloEpisodio());
        dto.setDuracaoEpisodio(episode.getDuracaoEpisodio());
        dto.setVideoUrl(episode.getVideoUrl());

        List<ReturnSubtitleDTO> subtitleDtos = new ArrayList<>();
        for (SubtitleModel subtitle : episode.getLegendasDisponiveis()) {
            subtitleDtos.add(SubtitleMapper.toDto(subtitle));
        }
        dto.setLegendasDisponiveis(subtitleDtos);

        List<ReturnAudioDTO> audioDtos = new ArrayList<>();
        for (AudioModel audio : episode.getAudiosDisponiveis()) {
            audioDtos.add(AudioMapper.toDto(audio));
        }
        dto.setAudiosDisponiveis(audioDtos);

        return dto;
    }
}

