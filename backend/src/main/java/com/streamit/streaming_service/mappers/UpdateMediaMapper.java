package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.UpdateActorDTO;
import com.streamit.streaming_service.dtos.UpdateAudioDTO;
import com.streamit.streaming_service.dtos.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.UpdateFilmDTO;
import com.streamit.streaming_service.dtos.UpdateMediaDTO;
import com.streamit.streaming_service.dtos.UpdateSeasonDTO;
import com.streamit.streaming_service.dtos.UpdateSeriesDTO;
import com.streamit.streaming_service.dtos.UpdateSubtitleDTO;
import com.streamit.streaming_service.model.ActorModel;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.model.SeasonModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.model.SubtitleModel;

public class UpdateMediaMapper {

    public static FilmModel toFilmEntity(UpdateFilmDTO dto, FilmModel film) {
        MediaModel media = film.getMedia();
        updateMediaFields(dto.getMedia(), media);
        film.setDuracao(dto.getDuracao());
        film.setVideoUrl(dto.getVideoUrl());

        updateSubtitles(dto.getLegendasDisponiveis(), film.getLegendasDisponiveis());
        updateAudios(dto.getAudiosDisponiveis(), film.getAudiosDisponiveis());
        updateActors(dto.getAtores(), film.getAtores());

        return film;
    }

    public static SeriesModel toSeriesEntity(UpdateSeriesDTO dto, SeriesModel series) {
        MediaModel media = series.getMedia();
        updateMediaFields(dto.getMedia(), media);

        updateSeasons(dto.getSeasons(), series.getSeasons());
        updateActors(dto.getAtores(), series.getAtores());

        return series;
    }

    private static void updateMediaFields(UpdateMediaDTO dto, MediaModel media) {
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
    }

    private static void updateSubtitles(List<UpdateSubtitleDTO> legendasDto, List<SubtitleModel> legendasModel) {
        if (legendasDto != null) {
            for (UpdateSubtitleDTO legendaDto : legendasDto) {
                UUID legendaId = legendaDto.getId();
                for (SubtitleModel legendaModel : legendasModel) {
                    if (legendaModel.getId().equals(legendaId)) {
                        legendaModel.setIdioma(legendaDto.getIdioma());
                        legendaModel.setUrl(legendaDto.getLegendaUrl());
                        break;
                    }
                }
            }
        }
    }

    private static void updateAudios(List<UpdateAudioDTO> audiosDto, List<AudioModel> audiosModel) {
        if (audiosDto != null) {
            for (UpdateAudioDTO audioDto : audiosDto) {
                UUID audioId = audioDto.getId();
                for (AudioModel audioModel : audiosModel) {
                    if (audioModel.getId().equals(audioId)) {
                        audioModel.setIdioma(audioDto.getIdioma());
                        audioModel.setUrl(audioDto.getAudioUrl());
                        break;
                    }
                }
            }
        }
    }

    private static void updateActors(List<UpdateActorDTO> atoresDto, List<ActorModel> atoresModel) {
        if (atoresDto != null) {
            for (UpdateActorDTO atorDto : atoresDto) {
                UUID atorId = atorDto.getId();
                for (ActorModel atorModel : atoresModel) {
                    if (atorModel.getId().equals(atorId)) {
                        atorModel.setNome(atorDto.getNome());
                        atorModel.setImagemUrl(atorDto.getImagemUrl());
                        break;
                    }
                }
            }
        }
    }

    private static void updateSeasons(List<UpdateSeasonDTO> seasonsDto, List<SeasonModel> seasonsModel) {
        if (seasonsDto != null) {
            for (UpdateSeasonDTO seasonDto : seasonsDto) {
                Integer seasonNumber = seasonDto.getSeasonNumber();
                for (SeasonModel seasonModel : seasonsModel) {
                    if (seasonModel.getSeasonNumber().equals(seasonNumber)) {
                        updateEpisodes(seasonDto.getEpisodes(), seasonModel.getEpisodes());
                        break;
                    }
                }
            }
        }
    }

    private static void updateEpisodes(List<UpdateEpisodeDTO> episodesDto, List<EpisodeModel> episodesModel) {
        if (episodesDto != null) {
            for (UpdateEpisodeDTO episodeDto : episodesDto) {
                Integer episodeNumber = episodeDto.getNumeroEpisodio();
                for (EpisodeModel episodeModel : episodesModel) {
                    if (episodeModel.getNumeroEpisodio().equals(episodeNumber)) {
                        episodeModel.setTituloEpisodio(episodeDto.getTituloEpisodio());
                        episodeModel.setDuracaoEpisodio(episodeDto.getDuracaoEpisodio());
                        episodeModel.setVideoUrl(episodeDto.getVideoUrl());

                        updateSubtitles(episodeDto.getLegendasDisponiveis(), episodeModel.getLegendasDisponiveis());
                        updateAudios(episodeDto.getAudiosDisponiveis(), episodeModel.getAudiosDisponiveis());
                        break;
                    }
                }
            }
        }
    }
}
