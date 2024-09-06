package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.dtos.episode.CreateEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.ReturnEpisodeDTO;
import com.streamit.streaming_service.dtos.episode.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SubtitleModel;

public class EpisodeMapper {
	
	public static void toUpdateEntity(UpdateEpisodeDTO episodeDto, EpisodeModel episodeModel) {
	    if (episodeDto != null) {
	        episodeModel.setTituloEpisodio(episodeDto.getTituloEpisodio());
	        episodeModel.setDuracaoEpisodio(episodeDto.getDuracaoEpisodio());
	        episodeModel.setVideoUrl(episodeDto.getVideoUrl());

	        if (episodeDto.getLegendasDisponiveis() != null) {
	            for (UpdateSubtitleDTO subtitleDto : episodeDto.getLegendasDisponiveis()) {
	                SubtitleModel subtitleModel = SubtitleMapper.findSubtitleModelById(subtitleDto.getId(), episodeModel.getLegendasDisponiveis());
	                if (subtitleModel != null) {
	                    SubtitleMapper.toUpdateEntity(subtitleDto, subtitleModel);
	                }
	            }
	        }

	        if (episodeDto.getAudiosDisponiveis() != null) {
	            for (UpdateAudioDTO audioDto : episodeDto.getAudiosDisponiveis()) {
	                AudioModel audioModel = AudioMapper.findAudioModelById(audioDto.getId(), episodeModel.getAudiosDisponiveis());
	                if (audioModel != null) {
	                    AudioMapper.toUpdateEntity(audioDto, audioModel);
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
            List<SubtitleModel> subtitleModels = new ArrayList<>();
            for (CreateSubtitleDTO subtitleDto : dto.getLegendasDisponiveis()) {
            	SubtitleModel subtitle = SubtitleMapper.toEntity(subtitleDto);
                subtitleModels.add(subtitle);
            }
            episode.setLegendasDisponiveis(subtitleModels);
        }

        if (dto.getAudiosDisponiveis() != null) {
            List<AudioModel> audioModels = new ArrayList<>();
            for (CreateAudioDTO audioDto : dto.getAudiosDisponiveis()) {
                AudioModel audio = AudioMapper.toEntity(audioDto);
                audioModels.add(audio);
            }
            episode.setAudiosDisponiveis(audioModels);
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
    
    public static EpisodeModel findEpisodeModelById(UUID id, List<EpisodeModel> episodes) {
        for (EpisodeModel episode : episodes) {
            if (episode.getId().equals(id)) {
                return episode;
            }
        }
        return null;
    }
}

