package com.streamit.streaming_service.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/streaming")
@AllArgsConstructor
public class PlaylistController {

	private final String baseDir = "caminho/Playlists";

	@GetMapping("/playlists")
	public ResponseEntity<?> getPlaylist(@RequestParam int number, @RequestParam String language,
			@RequestParam String mediaName, @RequestParam(required = false) String subtitleLanguage) {

		String playlistFileName;
		String subtitleFileName = null;

		switch (language.toLowerCase()) {
		case "pt":
			playlistFileName = String.format("playlist_pt%d.ts", number);
			break;
		case "eng":
			playlistFileName = String.format("playlist_eng%d.ts", number);
			break;
		case "es": // Espanhol
			playlistFileName = String.format("playlist_es%d.ts", number);
			break;
		case "fr": // Francês
			playlistFileName = String.format("playlist_fr%d.ts", number);
			break;
		case "de": // Alemão
			playlistFileName = String.format("playlist_de%d.ts", number);
			break;
		case "zh": // Chinês
			playlistFileName = String.format("playlist_zh%d.ts", number);
			break;
		case "jp": // Japonês
			playlistFileName = String.format("playlist_jp%d.ts", number);
			break;
		case "it": // Italiano
			playlistFileName = String.format("playlist_it%d.ts", number);
			break;
		default:
			return new ResponseEntity<>("Idioma de áudio inválido.", HttpStatus.BAD_REQUEST);
		}

		// Se o idioma da legenda for fornecido, construir o nome do arquivo de legenda
		if (subtitleLanguage != null) {
			switch (subtitleLanguage.toLowerCase()) {
			case "pt":
				subtitleFileName = String.format("legenda_pt%d.vtt", number);
				break;
			case "eng":
				subtitleFileName = String.format("legenda_eng%d.vtt", number);
				break;
			case "es": // Espanhol
				subtitleFileName = String.format("legenda_es%d.vtt", number);
				break;
			case "fr": // Francês
				subtitleFileName = String.format("legenda_fr%d.vtt", number);
				break;
			case "de": // Alemão
				subtitleFileName = String.format("legenda_de%d.vtt", number);
				break;
			case "zh": // Chinês
				subtitleFileName = String.format("legenda_zh%d.vtt", number);
				break;
			case "jp": // Japonês
				subtitleFileName = String.format("legenda_jp%d.vtt", number);
				break;
			case "it": // Italiano
				subtitleFileName = String.format("legenda_it%d.vtt", number);
				break;
			default:
				return new ResponseEntity<>("Idioma de legenda inválido.", HttpStatus.BAD_REQUEST);
			}
		}

		String folderPath = String.format("%splaylists_%s/", baseDir, mediaName);

		try {
			// Lógica para obter a playlist .ts
			Path playlistPath = Paths.get(folderPath + playlistFileName);
			Resource playlistResource = new UrlResource(playlistPath.toUri());

			if (playlistResource.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_TYPE, "video/MP2T");

				// Se não houver legenda, retornar apenas a playlist
				if (subtitleFileName == null) {
					return new ResponseEntity<>(playlistResource, headers, HttpStatus.OK);
				}

				// Lógica para obter a legenda .vtt
				Path subtitlePath = Paths.get(folderPath + subtitleFileName);
				Resource subtitleResource = new UrlResource(subtitlePath.toUri());

				if (subtitleResource.exists()) {
					HttpHeaders subtitleHeaders = new HttpHeaders();
					subtitleHeaders.add(HttpHeaders.CONTENT_TYPE, "text/vtt");

					// Retorna tanto a playlist quanto a legenda
					return ResponseEntity.ok().headers(headers).body(
							"Playlist disponível: " + playlistResource + ", Legenda disponível: " + subtitleResource);
				} else {
					return new ResponseEntity<>("Legenda não encontrada.", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>("Playlist não encontrada.", HttpStatus.NOT_FOUND);
			}
		} catch (MalformedURLException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
