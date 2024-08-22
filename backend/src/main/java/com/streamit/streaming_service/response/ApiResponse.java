package com.streamit.streaming_service.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
	private boolean success;
	private String message;
	private T data;
	private List<String> errors;
	private int code;
	private long timestamp;
	private String path;
}
