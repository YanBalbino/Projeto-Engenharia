package com.streamit.streaming_service.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MediaOMDB {

	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("Director")
	private String director;
	@JsonProperty("Poster")
	private String poster;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Plot")
    private String plot;
	
}
