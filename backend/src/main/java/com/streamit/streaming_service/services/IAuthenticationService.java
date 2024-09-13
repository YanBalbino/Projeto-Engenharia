package com.streamit.streaming_service.services;

import com.streamit.streaming_service.dtos.login.LoginDTO;

public interface IAuthenticationService {

	String login(LoginDTO loginDto);
}
