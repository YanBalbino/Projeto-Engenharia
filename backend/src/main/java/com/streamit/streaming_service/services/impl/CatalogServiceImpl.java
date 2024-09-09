package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.catalog.ReturnCatalogDTO;
import com.streamit.streaming_service.dtos.film.ReturnFilmDTO;
import com.streamit.streaming_service.dtos.series.ReturnSeriesDTO;
import com.streamit.streaming_service.mappers.FilmMapper;
import com.streamit.streaming_service.mappers.SeriesMapper;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.SeriesModel;
import com.streamit.streaming_service.repositories.FilmRepository;
import com.streamit.streaming_service.repositories.SeriesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalogServiceImpl {

    private final FilmRepository filmRepository;
    private final SeriesRepository seriesRepository;

    // Busca por ator
    public ReturnCatalogDTO findFilmsAndSeriesByActorName(String nomeAtor, Pageable pageable) {
        Page<FilmModel> filmList = filmRepository.findFilmsByActorName(nomeAtor, pageable);
        Page<SeriesModel> seriesList = seriesRepository.findSeriesByActorName(nomeAtor, pageable);
        
        List<ReturnFilmDTO> filmDtos = new ArrayList<>();
        for (FilmModel film : filmList.getContent()) {
            filmDtos.add(FilmMapper.toDto(film));
        }
        
        List<ReturnSeriesDTO> seriesDtos = new ArrayList<>();
        for (SeriesModel series : seriesList.getContent()) {
            seriesDtos.add(SeriesMapper.toDto(series));
        }
        
        return new ReturnCatalogDTO(filmDtos, seriesDtos);
    }

    // Busca por título
    public ReturnCatalogDTO findFilmsAndSeriesByTitle(String titulo, Pageable pageable) {
        Page<FilmModel> filmList = filmRepository.findFilmsByTitle(titulo, pageable);
        Page<SeriesModel> seriesList = seriesRepository.findSeriesByTitle(titulo, pageable);
        
        List<ReturnFilmDTO> filmDtos = new ArrayList<>();
        for (FilmModel film : filmList.getContent()) {
            filmDtos.add(FilmMapper.toDto(film));
        }
        
        List<ReturnSeriesDTO> seriesDtos = new ArrayList<>();
        for (SeriesModel series : seriesList.getContent()) {
            seriesDtos.add(SeriesMapper.toDto(series));
        }
        
        return new ReturnCatalogDTO(filmDtos, seriesDtos);
    }

    // Busca por gênero
    public ReturnCatalogDTO findFilmsAndSeriesByGenre(String genero, Pageable pageable) {
        Page<FilmModel> filmList = filmRepository.findFilmsByGenre(genero, pageable);
        Page<SeriesModel> seriesList = seriesRepository.findSeriesByGenre(genero, pageable);
        
        List<ReturnFilmDTO> filmDtos = new ArrayList<>();
        for (FilmModel film : filmList.getContent()) {
            filmDtos.add(FilmMapper.toDto(film));
        }
        
        List<ReturnSeriesDTO> seriesDtos = new ArrayList<>();
        for (SeriesModel series : seriesList.getContent()) {
            seriesDtos.add(SeriesMapper.toDto(series));
        }
        
        return new ReturnCatalogDTO(filmDtos, seriesDtos);
    }

    // Busca por diretor
    public ReturnCatalogDTO findFilmsAndSeriesByDirector(String diretor, Pageable pageable) {
        Page<FilmModel> filmList = filmRepository.findFilmsByDirector(diretor, pageable);
        Page<SeriesModel> seriesList = seriesRepository.findSeriesByDirector(diretor, pageable);
        
        List<ReturnFilmDTO> filmDtos = new ArrayList<>();
        for (FilmModel film : filmList.getContent()) {
            filmDtos.add(FilmMapper.toDto(film));
        }
        
        List<ReturnSeriesDTO> seriesDtos = new ArrayList<>();
        for (SeriesModel series : seriesList.getContent()) {
            seriesDtos.add(SeriesMapper.toDto(series));
        }
        
        return new ReturnCatalogDTO(filmDtos, seriesDtos);
    }
}
