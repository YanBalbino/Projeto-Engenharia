package com.streamit.streaming_service.mappers;

import java.util.ArrayList;
import java.util.List;

import com.streamit.streaming_service.dtos.ActorDTO;
import com.streamit.streaming_service.dtos.AudioDTO;
import com.streamit.streaming_service.dtos.EpisodeDTO;
import com.streamit.streaming_service.dtos.FilmDTO;
import com.streamit.streaming_service.dtos.SeasonDTO;
import com.streamit.streaming_service.dtos.SeriesDTO;
import com.streamit.streaming_service.dtos.SubtitleDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.MediaAttributes;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.model.SubtitleModel;

public class MediaMapper {

    public static FilmModel toFilmEntity(FilmDTO dto, FilmModel film) {
        MediaModel media = new MediaModel();
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
        film.setMedia(media);

        if(dto.getLegendasDisponiveis() != null) {
        	film.setLegendasDisponiveis(updateSubtitles(dto.getLegendasDisponiveis()));
        }
        
        if(dto.getAudiosDisponiveis() != null) {
        	film.setAudiosDisponiveis(updateAudios(dto.getAudiosDisponiveis()));
        }

        if(dto.getAtores() != null) {
            List<ActorModel> newActors = new ArrayList<>();
            for (ActorDTO actorDTO : dto.getAtores()) {
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

    public static SeriesModel toSeriesEntity(SeriesDTO dto, SeriesModel series) {
        MediaModel media = new MediaModel();
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
        series.setMedia(media);

        if(dto.getAtores() != null) {
            List<ActorModel> newActors = new ArrayList<>();
            for (ActorDTO actorDTO : dto.getAtores()) {
                ActorModel actor = new ActorModel();
                actor.setNome(actorDTO.getNome());
                actor.setImagemUrl(actorDTO.getImagemUrl());
                actor.setSerie(List.of(series));
                newActors.add(actor);
            }
            series.setAtores(newActors);
        }

        List<SeasonModel> seasons = new ArrayList<>();
        for (SeasonDTO seasonDTO : dto.getSeasons()) {
            SeasonModel season = new SeasonModel();
            season.setSeasonNumber(seasonDTO.getSeasonNumber());
            
            List<EpisodeModel> episodes = new ArrayList<>();
            for (EpisodeDTO episodeDTO : seasonDTO.getEpisodes()) {
                EpisodeModel episode = new EpisodeModel();
                episode.setTemporada(season);
                episode.setNumeroEpisodio(episodeDTO.getNumeroEpisodio());
                episode.setTituloEpisodio(episodeDTO.getTituloEpisodio());
                episode.setDuracaoEpisodio(episodeDTO.getDuracaoEpisodio());
                episode.setVideoUrl(episodeDTO.getVideoUrl());

                if(episodeDTO.getLegendasDisponiveis() != null) {
                	episode.setLegendasDisponiveis(updateSubtitles(episodeDTO.getLegendasDisponiveis()));
                }
                
                if(episodeDTO.getAudiosDisponiveis() != null) {
                	episode.setAudiosDisponiveis(updateAudios(episodeDTO.getAudiosDisponiveis()));
                }

                episodes.add(episode);
            }
            season.setEpisodes(episodes);
            season.setSerie(series);
            seasons.add(season);
        }
        series.setSeasons(seasons);

        return series;
    }
    
    public static List<SubtitleModel> updateSubtitles(List<SubtitleDTO> subtitleDTOs) {
        List<SubtitleModel> subtitles = new ArrayList<>();
        for (SubtitleDTO subtitleDTO : subtitleDTOs) {
            SubtitleModel subtitle = new SubtitleModel();
            MediaAttributes attributes = new MediaAttributes();
            attributes.setIdioma(subtitleDTO.getIdioma());
            attributes.setUrl(subtitleDTO.getLegendaUrl());
            subtitle.setAttributes(attributes);
            subtitles.add(subtitle);
        }
        return subtitles;
    }

    public static List<AudioModel> updateAudios(List<AudioDTO> audioDTOs) {
        List<AudioModel> audios = new ArrayList<>();
        for (AudioDTO audioDTO : audioDTOs) {
            AudioModel audio = new AudioModel();
            MediaAttributes attributes = new MediaAttributes();
            attributes.setIdioma(audioDTO.getIdioma());
            attributes.setUrl(audioDTO.getAudioUrl());
            audio.setAttributes(attributes);
            audios.add(audio);
        }
        return audios;
    }
}
