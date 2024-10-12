package com.streamit.streaming_service.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/api/streaming")
public class PlaylistController {

    private final String baseDir = "caminho/Playlists";

    @GetMapping("/playlists")
    public ResponseEntity<?> getPlaylist(
            @RequestParam int number, 
            @RequestParam String language, 
            @RequestParam String mediaName, 
            @RequestParam(required = false, defaultValue = "false") boolean includeSubtitle) {

        String playlistFileName;
        String subtitleFileName;

        switch (language.toLowerCase()) {
            case "pt": // Português
                playlistFileName = String.format("playlist_pt%d.ts", number);
                subtitleFileName = String.format("legenda_pt%d.vtt", number);
                break;
            case "eng": // Inglês
                playlistFileName = String.format("playlist_eng%d.ts", number);
                subtitleFileName = String.format("legenda_eng%d.vtt", number);
                break;
            case "es": // Espanhol
                playlistFileName = String.format("playlist_es%d.ts", number);
                subtitleFileName = String.format("legenda_es%d.vtt", number);
                break;
            case "fr": // Francês
                playlistFileName = String.format("playlist_fr%d.ts", number);
                subtitleFileName = String.format("legenda_fr%d.vtt", number);
                break;
            case "de": // Alemão
                playlistFileName = String.format("playlist_de%d.ts", number);
                subtitleFileName = String.format("legenda_de%d.vtt", number);
                break;
            case "zh": // Chinês
                playlistFileName = String.format("playlist_zh%d.ts", number);
                subtitleFileName = String.format("legenda_zh%d.vtt", number);
                break;
            case "jp": // Japonês
                playlistFileName = String.format("playlist_jp%d.ts", number);
                subtitleFileName = String.format("legenda_jp%d.vtt", number);
                break;
            case "it": // Italiano
                playlistFileName = String.format("playlist_it%d.ts", number);
                subtitleFileName = String.format("legenda_it%d.vtt", number);
                break;
            default:
                return new ResponseEntity<>("Idioma inválido.", HttpStatus.BAD_REQUEST);
        }

        // Construindo o caminho da pasta
        String folderPath = String.format("%splaylists_%s/", baseDir, mediaName);

        // Lógica para obter a playlist .ts
        try {
            Path playlistPath = Paths.get(folderPath + playlistFileName);
            Resource playlistResource = new UrlResource(playlistPath.toUri());

            if (playlistResource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "video/MP2T");
                ResponseEntity<Resource> playlistResponse = new ResponseEntity<>(playlistResource, headers, HttpStatus.OK);

                // Se o cliente não requisitar a legenda, retorna apenas a playlist
                if (!includeSubtitle) {
                    return playlistResponse;
                }

                // Lógica para obter a legenda .vtt
                Path subtitlePath = Paths.get(folderPath + subtitleFileName);
                Resource subtitleResource = new UrlResource(subtitlePath.toUri());

                if (subtitleResource.exists()) {
                    HttpHeaders subtitleHeaders = new HttpHeaders();
                    subtitleHeaders.add(HttpHeaders.CONTENT_TYPE, "text/vtt");

                    // Retorna um objeto que contém a playlist e a legenda
                    return ResponseEntity.ok()
                            .headers(headers)
                            .body("Playlist disponível: " + playlistResource + ", Legenda disponível: " + subtitleResource);
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
