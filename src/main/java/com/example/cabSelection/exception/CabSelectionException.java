package com.example.cabSelection.exception;

import org.springframework.http.HttpStatus;

public class CabSelectionException extends Exception {

	private String message;

	private HttpStatus code;

	public CabSelectionException(String message, HttpStatus code) {
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
