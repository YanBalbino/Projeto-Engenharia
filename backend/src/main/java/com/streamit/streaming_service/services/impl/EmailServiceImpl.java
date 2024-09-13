package com.streamit.streaming_service.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.services.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;
    
	@Value("${spring.mail.username}")
	private String senderEmail;
	
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
    @Override
    public void enviarEmailVerificacao(String email, String codigoVerificacao) {
        SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(senderEmail);
		message.setTo(email);
		message.setSubject("Código de recuperação");
		message.setText("Aqui está seu código de recuperação da senha: " + codigoVerificacao);
		
		mailSender.send(message);
    }
}