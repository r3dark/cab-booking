package com.example.cabSelection.exception;

import org.springframework.http.HttpStatus;

public class CabBookingException extends Exception {

	private String message;

	private HttpStatus code;

	public CabBookingException(String message, HttpStatus code) {
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}
}
