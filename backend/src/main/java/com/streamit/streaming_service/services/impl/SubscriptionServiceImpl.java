package com.streamit.streaming_service.services.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.SubscriptionDTO;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.SubscriptionMapper;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.repositories.SubscriptionRepository;
import com.streamit.streaming_service.services.ISubscriptionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements ISubscriptionService {

	SubscriptionRepository subscriptionRepository;

	@Override
	public SubscriptionModel renovarInscricao(UUID id) {
		SubscriptionModel entity = subscriptionRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada com id " + id));
		
        LocalDate currentDate = LocalDate.now();

        entity.setDataInicio(currentDate);
        entity.setDataTermino(currentDate.plusMonths(1));
        entity.setStatusAtivo(true);
        SubscriptionModel entitySaved = subscriptionRepository.save(entity);
        
		return entitySaved;
	}
	
	@Override
	public SubscriptionDTO findByUserId(UUID idUser) {
		SubscriptionModel entity = subscriptionRepository.findByUserId(idUser)
				.orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada com id " + idUser + " de usuário."));
		return SubscriptionMapper.toDto(entity);
		
	}

}
