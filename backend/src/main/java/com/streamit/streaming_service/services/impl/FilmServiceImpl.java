package com.streamit.streaming_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.FilmDTO;
import com.streamit.streaming_service.model.CatalogModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.repositories.FilmRepository;
import com.streamit.streaming_service.services.IFilmService;

@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public FilmDTO create(FilmDTO filmDto) {
        FilmModel filmModel = new FilmModel();
        filmModel.setTitulo(filmDto.getTitulo());
        filmModel.setAnoProducao(filmDto.getAnoProducao());
        filmModel.setGenero(filmDto.getGenero());
        filmModel.setDuracao(filmDto.getDuracao());
        filmModel.setLegendasDisponiveis(filmDto.getLegendasDisponiveis());
        filmModel.setAudiosDisponiveis(filmDto.getAudiosDisponiveis());
        filmModel.setDescricao(filmDto.getDescricao());
        filmModel.setAtores(filmDto.getAtores());
        filmModel.setDiretor(filmDto.getDiretor());
        
        CatalogModel catalog = new CatalogModel();
        catalog.setId(filmDto.getCatalogId()); 
        filmModel.setCatalogo(catalog);

        FilmModel savedFilm = filmRepository.save(filmModel);

        FilmDTO result = new FilmDTO();
        result.setTitulo(savedFilm.getTitulo());
        result.setAnoProducao(savedFilm.getAnoProducao());
        result.setGenero(savedFilm.getGenero());
        result.setDuracao(savedFilm.getDuracao());
        result.setLegendasDisponiveis(savedFilm.getLegendasDisponiveis());
        result.setAudiosDisponiveis(savedFilm.getAudiosDisponiveis());
        result.setDescricao(savedFilm.getDescricao());
        result.setAtores(savedFilm.getAtores());
        result.setDiretor(savedFilm.getDiretor());
        result.setCatalogId(savedFilm.getCatalogo().getId()); 

        return result;
    }
}
