package com.streamit.streaming_service.services;

import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.login.LoginResponseDTO;

public interface IAuthenticationService {

	LoginResponseDTO login(LoginDTO loginDto);
}
