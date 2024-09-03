package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.actor.CreateActorDTO;
import com.streamit.streaming_service.dtos.actor.ReturnActorDTO;
import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.film.CreateFilmDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.film.UpdateFilmDTO;
import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.model.SubtitleModel;

public class FilmMapper {
	
    public static FilmModel toEntity(CreateFilmDTO dto, FilmModel film) {
        MediaModel media = new MediaModel();
        MediaMapper.toEntity(dto.getMedia(), media);
        film.setMedia(media);

        if(dto.getLegendasDisponiveis() != null) {
            film.setLegendasDisponiveis(SubtitleMapper.toEntityList(dto.getLegendasDisponiveis()));
        }

        if(dto.getAudiosDisponiveis() != null) {
            film.setAudiosDisponiveis(AudioMapper.toEntityList(dto.getAudiosDisponiveis()));
        }

        if(dto.getAtores() != null) {
            List<ActorModel> newActors = new ArrayList<>();
            for (CreateActorDTO actorDTO : dto.getAtores()) {
                ActorModel actor = new ActorModel();
                actor.setNome(actorDTO.getNome());
                actor.setImagemUrl(actorDTO.getImagemUrl());
                actor.setFilme(List.of(film));
                newActors.add(actor);
            }
            film.setAtores(newActors);
        }

        film.setDuracao(dto.getDuracao());
        film.setVideoUrl(dto.getVideoUrl());
        return film;
    }
    
    public static FilmModel toUpdateEntity(UpdateFilmDTO dto, FilmModel film) {
        MediaModel media = film.getMedia();
        MediaMapper.toUpdateEntity(dto.getMedia(), media);
        film.setDuracao(dto.getDuracao());
        film.setVideoUrl(dto.getVideoUrl());

        SubtitleMapper.toUpdateEntity(dto.getLegendasDisponiveis(), film.getLegendasDisponiveis());
        AudioMapper.toUpdateEntity(dto.getAudiosDisponiveis(), film.getAudiosDisponiveis());
        ActorMapper.toUpdateEntity(dto.getAtores(), film.getAtores());

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

        List<ReturnActorDTO> actorDtos = new ArrayList<>();
        for (ActorModel actor : film.getAtores()) {
            actorDtos.add(ActorMapper.toDto(actor));
        }
        dto.setAtores(actorDtos);

        return dto;
    }
}

