package com.streamit.streaming_service.mappers;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.UpdateActorDTO;
import com.streamit.streaming_service.dtos.UpdateAudioDTO;
import com.streamit.streaming_service.dtos.UpdateEpisodeDTO;
import com.streamit.streaming_service.dtos.UpdateFilmDTO;
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

	//se não passar os ids das entidades, ele não altera nada
    public static FilmModel toFilmEntity(UpdateFilmDTO dto, FilmModel film) {
    	//no frontend, deve deixar tudo definido como estava antes de editar para caso nada de media seja alterado
    	MediaModel media = film.getMedia();
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
        film.setMedia(media);
        film.setDuracao(dto.getDuracao());
        film.setVideoUrl(dto.getVideoUrl());

        List<UpdateSubtitleDTO> legendasDto = dto.getLegendasDisponiveis();
        List<SubtitleModel> legendasModel = film.getLegendasDisponiveis();
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
        
        List<UpdateAudioDTO> audiosDto = dto.getAudiosDisponiveis();
        List<AudioModel> audiosModel = film.getAudiosDisponiveis();

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

        List<UpdateActorDTO> atoresDto = dto.getAtores();
        List<ActorModel> atoresModel = film.getAtores();

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

        return film;
    }

    public static SeriesModel toSeriesEntity(UpdateSeriesDTO dto, SeriesModel series) {
        MediaModel media = series.getMedia();
        media.setTitulo(dto.getTitulo());
        media.setAnoProducao(dto.getAnoProducao());
        media.setGenero(dto.getGenero());
        media.setDescricao(dto.getDescricao());
        media.setDiretor(dto.getDiretor());
        series.setMedia(media);

        List<UpdateSeasonDTO> seasonsDto = dto.getSeasons();
        List<SeasonModel> seasonsModel = series.getSeasons();

        if (seasonsDto != null) {
            for (UpdateSeasonDTO seasonDto : seasonsDto) {
                Integer seasonNumber = seasonDto.getSeasonNumber();
                for (SeasonModel seasonModel : seasonsModel) {
                    if (seasonModel.getSeasonNumber().equals(seasonNumber)) {
                        List<UpdateEpisodeDTO> episodesDto = seasonDto.getEpisodes();
                        List<EpisodeModel> episodesModel = seasonModel.getEpisodes();

                        if (episodesDto != null) {
                            for (UpdateEpisodeDTO episodeDto : episodesDto) {
                                Integer episodeNumber = episodeDto.getNumeroEpisodio();
                                for (EpisodeModel episodeModel : episodesModel) {
                                    if (episodeModel.getNumeroEpisodio().equals(episodeNumber)) {
                                        episodeModel.setTituloEpisodio(episodeDto.getTituloEpisodio());
                                        episodeModel.setDuracaoEpisodio(episodeDto.getDuracaoEpisodio());
                                        episodeModel.setVideoUrl(episodeDto.getVideoUrl());

                                        List<UpdateSubtitleDTO> legendasDto = episodeDto.getLegendasDisponiveis();
                                        List<SubtitleModel> legendasModel = episodeModel.getLegendasDisponiveis();

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

                                        List<UpdateAudioDTO> audiosDto = episodeDto.getAudiosDisponiveis();
                                        List<AudioModel> audiosModel = episodeModel.getAudiosDisponiveis();

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
                                        break; 
                                    }
                                }
                            }
                        }
                        break; 
                    }
                }
            }
        }

        List<UpdateActorDTO> atoresDto = dto.getAtores();
        List<ActorModel> atoresModel = series.getAtores();

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

        return series;
    }

}
