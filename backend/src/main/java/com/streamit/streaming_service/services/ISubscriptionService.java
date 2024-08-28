package com.streamit.streaming_service.services;

import java.util.UUID;

import com.streamit.streaming_service.dtos.ReturnSubscriptionDTO;

public interface ISubscriptionService {

	ReturnSubscriptionDTO findByUserId(UUID idUser);

}
