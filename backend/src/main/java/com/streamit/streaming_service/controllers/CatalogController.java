package com.streamit.streaming_service.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.dtos.media.ReturnMediaDTO;
import com.streamit.streaming_service.services.ICatalogService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/catalog")
@AllArgsConstructor
public class CatalogController {

	private final ICatalogService catalogService;

	// Busca por ator com paginação
	@GetMapping("/search/actor")
	public ResponseEntity<List<ReturnMediaDTO>> searchByActor(@RequestParam("nomeAtor") String nomeAtor,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		List<ReturnMediaDTO> catalog = catalogService.findFilmsAndSeriesByActorName(nomeAtor, pageable);
		return ResponseEntity.ok(catalog);
	}

	// Busca por título com paginação
	@GetMapping("/search/title")
	public ResponseEntity<List<ReturnMediaDTO>> searchByTitle(@RequestParam("titulo") String titulo,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		List<ReturnMediaDTO> catalog = catalogService.findFilmsAndSeriesByTitle(titulo, pageable);
		return ResponseEntity.ok(catalog);
	}

	// Busca por gênero com paginação
	@GetMapping("/search/genre")
	public ResponseEntity<List<ReturnMediaDTO>> searchByGenre(@RequestParam("genero") String genero,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		List<ReturnMediaDTO> catalog = catalogService.findFilmsAndSeriesByGenre(genero, pageable);
		return ResponseEntity.ok(catalog);
	}

	// Busca por diretor com paginação
	@GetMapping("/search/director")
	public ResponseEntity<List<ReturnMediaDTO>> searchByDirector(@RequestParam("diretor") String diretor,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		List<ReturnMediaDTO> catalog = catalogService.findFilmsAndSeriesByDirector(diretor, pageable);
		return ResponseEntity.ok(catalog);
	}
}
