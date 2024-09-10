package com.streamit.streaming_service.exceptions;

public class MaxProfilesLimitReachedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MaxProfilesLimitReachedException(String message) {
		super(message);
	}

}
