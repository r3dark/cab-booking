package com.example.cabBooking.exception;

public enum ErrorMessage {

	INVALID_REQUEST ("Invalid request. Fields and headers should not be null or empty"),
	DRIVER_ALREADY_EXISTS ("Driver already exists."),
	DRIVER_DOES_NOT_EXIST ("Driver does not exist."),
	INTERNAL_SERVER_ERROR ("Internal server error.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
