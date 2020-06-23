package com.example.cabSelection.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends CabSelectionException {

	public InvalidRequestException(String message, HttpStatus code) {
		super(message, code);
	}
}
