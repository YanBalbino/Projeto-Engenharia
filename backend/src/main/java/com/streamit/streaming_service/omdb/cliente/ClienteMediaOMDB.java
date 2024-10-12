package com.streamit.streaming_service.omdb.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.streamit.streaming_service.omdb.MediaOMDB;

@FeignClient(value = "omdbFilmes", url = "https://www.omdbapi.com/")
public interface ClienteMediaOMDB {

	@GetMapping
	MediaOMDB getMedia(@RequestParam("t") String tema, @RequestParam("apiKey") String key);
}
