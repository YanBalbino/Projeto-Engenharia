package com.streamit.streaming_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/streaming")
@AllArgsConstructor
public class PlaylistController {

    private final String baseDir = "caminho/Playlists/";

    @GetMapping("/playlists")
    public ResponseEntity<?> getPlaylist(@RequestParam String language, @RequestParam String mediaName) {
        String playlistFileName;

        switch (language.toLowerCase()) {
            case "pt":
                playlistFileName = "playlist_pt.m3u8";
                break;
            case "en":
                playlistFileName = "playlist_en.m3u8";
                break;
            case "es": // Espanhol
                playlistFileName = "playlist_es.m3u8";
                break;
            case "fr": // Francês
                playlistFileName = "playlist_fr.m3u8";
                break;
            case "de": // Alemão
                playlistFileName = "playlist_de.m3u8";
                break;
            case "zh": // Chinês
                playlistFileName = "playlist_zh.m3u8";
                break;
            case "jp": // Japonês
                playlistFileName = "playlist_jp.m3u8";
                break;
            case "it": // Italiano
                playlistFileName = "playlist_it.m3u8";
                break;
            default:
                return new ResponseEntity<>("Idioma de áudio inválido.", HttpStatus.BAD_REQUEST);
        }

        String folderPath = String.format("%splaylists_%s/", baseDir, mediaName);
        Path playlistPath = Paths.get(folderPath + playlistFileName);

        return new ResponseEntity<>(playlistPath.toString(), HttpStatus.OK);
    }

    @GetMapping("/subtitles")
    public ResponseEntity<?> getSubtitle(@RequestParam String subtitleLanguage, @RequestParam String mediaName) {
        String subtitleFileName;

        switch (subtitleLanguage.toLowerCase()) {
            case "pt":
                subtitleFileName = "legenda_pt.m3u8";
                break;
            case "en":
                subtitleFileName = "legenda_en.m3u8";
                break;
            case "es": // Espanhol
                subtitleFileName = "legenda_es.m3u8";
                break;
            case "fr": // Francês
                subtitleFileName = "legenda_fr.m3u8";
                break;
            case "de": // Alemão
                subtitleFileName = "legenda_de.m3u8";
                break;
            case "zh": // Chinês
                subtitleFileName = "legenda_zh.m3u8";
                break;
            case "jp": // Japonês
                subtitleFileName = "legenda_jp.m3u8";
                break;
            case "it": // Italiano
                subtitleFileName = "legenda_it.m3u8";
                break;
            default:
                return new ResponseEntity<>("Idioma de legenda inválido.", HttpStatus.BAD_REQUEST);
        }

        String folderPath = String.format("%splaylists_%s/", baseDir, mediaName);
        Path subtitlePath = Paths.get(folderPath + subtitleFileName);

        return new ResponseEntity<>(subtitlePath.toString(), HttpStatus.OK);
    }
}
