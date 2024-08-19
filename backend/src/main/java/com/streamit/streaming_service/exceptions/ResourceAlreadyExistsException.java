package com.streamit.streaming_service.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
