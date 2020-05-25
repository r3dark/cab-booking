package com.example.cabBooking.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends CabBookingException {

	public InvalidRequestException(String message, HttpStatus code) {
		super(message, code);
	}
}
