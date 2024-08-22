package com.streamit.streaming_service.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> hadleException(Exception ex, WebRequest request) {
		List<String> errors = Arrays.asList(ex.getMessage());
		ApiResponse<Void> response = ResponseUtil.error(errors, ApiConstants.MESSAGE_INTERNAL_ERROR,
				ApiConstants.HTTP_STATUS_INTERNAL_SERVER_ERROR, request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> hadleResourceNotFoundException(Exception ex, WebRequest request) {
		ApiResponse<Void> response = ResponseUtil.error(ex.getMessage(), ApiConstants.MESSAGE_RESOURCE_NOT_FOUND,
				ApiConstants.HTTP_STATUS_NOT_FOUND, request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<Void>> hadleResourceAlreadyExistsException(Exception ex, WebRequest request) {
		ApiResponse<Void> response = ResponseUtil.error(ex.getMessage(), ApiConstants.MESSAGE_RESOURCE_ALREADY_EXISTS,
				ApiConstants.HTTP_STATUS_BAD_REQUEST, request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			WebRequest request) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(error -> {
			String errorMessage = error.getDefaultMessage();
			return errorMessage;
		}).collect(Collectors.toList());

		ApiResponse<Void> response = ResponseUtil.error(errors, "Validation failed", 400,
				request.getDescription(false));

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
