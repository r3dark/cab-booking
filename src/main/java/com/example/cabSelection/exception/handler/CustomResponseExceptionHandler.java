package com.example.cabSelection.exception.handler;

import com.example.cabSelection.exception.ErrorMessage;
import com.example.cabSelection.exception.InvalidRequestException;
import com.example.cabSelection.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseExceptionHandler {

	private static final String FAILURE_STATUS = "failure";

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
		return new ResponseEntity<>(getErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericErrorException(Exception ex) {
		return new ResponseEntity<>(getErrorResponse(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse getErrorResponse(String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(FAILURE_STATUS);
		errorResponse.setReason(message);
		return errorResponse;
	}
}
