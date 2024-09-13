package com.streamit.streaming_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

	ADMIN("admin"),
	USER("user");
	
	private String role;
}
