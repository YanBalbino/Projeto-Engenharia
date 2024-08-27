package com.streamit.streaming_service.services;

import java.util.UUID;

import com.streamit.streaming_service.dtos.SubscriptionDTO;
import com.streamit.streaming_service.model.SubscriptionModel;

public interface ISubscriptionService {

	SubscriptionModel renovarInscricao(UUID id);
	SubscriptionDTO findByUserId(UUID idUser);

}
