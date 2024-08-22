package com.streamit.streaming_service.response;

import java.util.Arrays;
import java.util.List;

public class ResponseUtil {

	public static <T> ApiResponse<T> success(T data, String message, int code, String path) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setMessage(message);
		response.setData(data);
		response.setErrors(null);
		response.setCode(code);
		response.setTimestamp(System.currentTimeMillis());
		response.setPath(path);
		return response;
	}

	public static <T> ApiResponse<T> error(List<String> errors, String message, int errorCode, String path) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setMessage(message);
		response.setData(null);
		response.setErrors(errors);
		response.setCode(errorCode);
		response.setTimestamp(System.currentTimeMillis());
		response.setPath(path);
		return response;
	}

	public static <T> ApiResponse<T> error(String error, String message, int errorCode, String path) {
		return error(Arrays.asList(error), message, errorCode, path);
	}
}
