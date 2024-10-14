package com.streamit.streaming_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.omdb.MediaOMDB;
import com.streamit.streaming_service.omdb.cliente.ClienteMediaOMDB;
import com.streamit.streaming_service.services.IMediaOMDBService;

@Service
public class FilmOMDBServiceImpl implements IMediaOMDBService {

	@Value("${api.streamit.imdb.key}")
	private String apiKey;

	@Autowired
    private ClienteMediaOMDB cliente;
    
    @Override
    public MediaOMDB getMedia(String tema) {
    	return cliente.getMedia(tema, apiKey);
    }
    
}
