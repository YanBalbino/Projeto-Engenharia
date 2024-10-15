package com.streamit.streaming_service.services;

import com.streamit.streaming_service.model.AdminModel;

public interface IAdminService {

	AdminModel findAdminModelByEmail(String email);
}