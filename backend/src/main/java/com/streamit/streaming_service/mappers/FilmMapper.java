package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.audio.CreateAudioDTO;
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;
import com.streamit.streaming_service.dtos.subtitle.CreateSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.model.SubtitleModel;
import com.streamit.streaming_service.omdb.MediaOMDB;

public class FilmMapper {
	
    public static FilmModel toEntity(CreateFilmDTO dto, FilmModel film, MediaOMDB omdb) {
        MediaModel media = new MediaModel();
        MediaMapper.toEntity(dto.getMedia(), media, omdb);
        film.setMedia(media);

        if (dto.getLegendasDisponiveis() != null) {
            List<SubtitleModel> subtitles = new ArrayList<>();
            for (CreateSubtitleDTO subtitleDto : dto.getLegendasDisponiveis()) {
                SubtitleModel subtitle = SubtitleMapper.toEntity(subtitleDto);
                subtitles.add(subtitle);
            }
            film.setLegendasDisponiveis(subtitles);
        }

        if (dto.getAudiosDisponiveis() != null) {
            List<AudioModel> audios = new ArrayList<>();
            for (CreateAudioDTO audioDto : dto.getAudiosDisponiveis()) {
                AudioModel audio = AudioMapper.toEntity(audioDto);
                audios.add(audio);
            }
            film.setAudiosDisponiveis(audios);
        }

        film.setDuracao(dto.getDuracao());
        film.setVideoUrl(dto.getVideoUrl());

        return film;
    }
    
    public static FilmModel toUpdateEntity(UpdateFilmDTO dto, FilmModel film) {
        MediaMapper.toUpdateEntity(dto.getMedia(), film.getMedia());

        film.setDuracao(dto.getDuracao());
        film.setVideoUrl(dto.getVideoUrl());

        if (dto.getLegendasDisponiveis() != null) {
            for (UpdateSubtitleDTO subtitleDto : dto.getLegendasDisponiveis()) {
                SubtitleModel subtitleModel = SubtitleMapper.findSubtitleModelById(subtitleDto.getId(), film.getLegendasDisponiveis());
                if (subtitleModel != null) {
                    SubtitleMapper.toUpdateEntity(subtitleDto, subtitleModel);
                }
            }
        }
        if (dto.getAudiosDisponiveis() != null) {
            for (UpdateAudioDTO audioDto : dto.getAudiosDisponiveis()) {
                AudioModel audioModel = AudioMapper.findAudioModelById(audioDto.getId(), film.getAudiosDisponiveis());
                if (audioModel != null) {
                    AudioMapper.toUpdateEntity(audioDto, audioModel);
                }
            }
        }
        return film;
    }

    public static ReturnFilmDTO toDto(FilmModel film) {
        ReturnFilmDTO dto = new ReturnFilmDTO();
        dto.setId(film.getId());
        dto.setMedia(MediaMapper.toDto(film.getMedia()));
        dto.setDuracao(film.getDuracao());
        dto.setVideoUrl(film.getVideoUrl());

        List<ReturnSubtitleDTO> subtitleDtos = new ArrayList<>();
        for (SubtitleModel subtitle : film.getLegendasDisponiveis()) {
            subtitleDtos.add(SubtitleMapper.toDto(subtitle));
        }
        dto.setLegendasDisponiveis(subtitleDtos);

        List<ReturnAudioDTO> audioDtos = new ArrayList<>();
        for (AudioModel audio : film.getAudiosDisponiveis()) {
            audioDtos.add(AudioMapper.toDto(audio));
        }
        dto.setAudiosDisponiveis(audioDtos);

        return dto;
    }
}

