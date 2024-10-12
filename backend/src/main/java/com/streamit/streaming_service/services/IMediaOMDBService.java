package com.streamit.streaming_service.services;

import com.streamit.streaming_service.omdb.MediaOMDB;

public interface IMediaOMDBService {

	MediaOMDB getMedia(String tema);
}
