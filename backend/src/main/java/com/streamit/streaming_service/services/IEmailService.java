package com.streamit.streaming_service.services;

public interface IEmailService {

	public void enviarEmailVerificacao(String email, String codigoVerificacao);
}
