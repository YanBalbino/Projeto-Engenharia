package com.streamit.streaming_service.services;

import java.time.Instant;

import com.streamit.streaming_service.model.PersonModel;

public interface ITokenService {

	String generateToken(PersonModel user);
	String validateToken(String token);
	Instant generateExpirationDate();
}
