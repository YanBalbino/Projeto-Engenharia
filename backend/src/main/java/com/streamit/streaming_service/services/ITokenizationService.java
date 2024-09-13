package com.streamit.streaming_service.services;

public interface ITokenizationService {

	 String generateTokenFromCardData(String cardNumber, String cardHolder, String expiryDate, String cvv);
}
