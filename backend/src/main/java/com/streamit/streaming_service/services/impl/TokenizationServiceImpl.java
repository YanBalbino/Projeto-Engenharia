package com.streamit.streaming_service.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.services.ITokenizationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenizationServiceImpl implements ITokenizationService {

	//provisorio
    @Override
    public String generateTokenFromCardData(String cardNumber, String cardHolder, String expiryDate, String cvv) {
        try {
            String cardData = cardNumber + cardHolder + expiryDate + cvv;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(cardData.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criar hash para os dados do cartão", e);
        }
    }
}
