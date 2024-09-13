package com.streamit.streaming_service.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.streamit.streaming_service.dtos.subscription.ReturnSubscriptionDTO;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;

public interface ISubscriptionService {

	ReturnSubscriptionDTO findByUserId(UUID idUser);
	SubscriptionModel renovarInscricao(UUID id);
	SubscriptionModel createSubscription(UserModel user, LocalDateTime currentDate);
}
