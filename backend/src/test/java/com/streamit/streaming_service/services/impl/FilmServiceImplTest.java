package com.streamit.streaming_service.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.streamit.streaming_service.dtos.FilmDTO;
import com.streamit.streaming_service.model.CatalogModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.repositories.FilmRepository;

public class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    private FilmDTO filmDTO;
    private FilmModel filmModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        filmDTO = new FilmDTO();
        filmDTO.setTitulo("Sample Film Title");
        filmDTO.setAnoProducao(2024);
        filmDTO.setGenero("Action");
        filmDTO.setDuracao(120);
        filmDTO.setLegendasDisponiveis(List.of("English", "Spanish"));
        filmDTO.setAudiosDisponiveis(List.of("English", "French"));
        filmDTO.setDescricao("Sample Film Description");
        filmDTO.setAtores(List.of("Actor 1", "Actor 2"));
        filmDTO.setDiretor("Film Director");
        filmDTO.setCatalogId(UUID.randomUUID());

        filmModel = new FilmModel();
        filmModel.setId(UUID.randomUUID());
        filmModel.setTitulo(filmDTO.getTitulo());
        filmModel.setAnoProducao(filmDTO.getAnoProducao());
        filmModel.setGenero(filmDTO.getGenero());
        filmModel.setDuracao(filmDTO.getDuracao());
        filmModel.setLegendasDisponiveis(filmDTO.getLegendasDisponiveis());
        filmModel.setAudiosDisponiveis(filmDTO.getAudiosDisponiveis());
        filmModel.setDescricao(filmDTO.getDescricao());
        filmModel.setAtores(filmDTO.getAtores());
        filmModel.setDiretor(filmDTO.getDiretor());
        filmModel.setCatalogo(new CatalogModel());
    }

    @Test
    public void testCreateFilm() {
        when(filmRepository.save(filmModel)).thenReturn(filmModel);

        FilmDTO createdFilm = filmService.create(filmDTO);

        assertEquals(filmDTO.getTitulo(), createdFilm.getTitulo());
        assertEquals(filmDTO.getAnoProducao(), createdFilm.getAnoProducao());
        assertEquals(filmDTO.getGenero(), createdFilm.getGenero());
        assertEquals(filmDTO.getDuracao(), createdFilm.getDuracao());
        assertEquals(filmDTO.getLegendasDisponiveis(), createdFilm.getLegendasDisponiveis());
        assertEquals(filmDTO.getAudiosDisponiveis(), createdFilm.getAudiosDisponiveis());
        assertEquals(filmDTO.getDescricao(), createdFilm.getDescricao());
        assertEquals(filmDTO.getAtores(), createdFilm.getAtores());
        assertEquals(filmDTO.getDiretor(), createdFilm.getDiretor());
        assertEquals(filmDTO.getCatalogId(), createdFilm.getCatalogId());
    }
}
