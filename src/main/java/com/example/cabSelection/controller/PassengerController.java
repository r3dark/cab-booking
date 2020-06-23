package com.example.cabSelection.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.example.cabSelection.exception.ErrorMessage;
import com.example.cabSelection.exception.InvalidRequestException;
import com.example.cabSelection.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PassengerController {

	@Autowired
	PassengerService passengerService;

	@PostMapping("/api/v1/passenger/available_cabs")
	@ApiOperation("Get available cabs for customer.")
	public ResponseEntity<String> getAvailableCabs(@RequestBody String requestBody,  @RequestHeader (value = "content-type") String contentTypeHeader) throws Exception {

		log.debug("Request for getting available cabs for passenger : " + requestBody + "\nheaders: " + contentTypeHeader);
		if (!contentTypeHeader.equalsIgnoreCase("application/json")) {
			throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
		}

		String availableCabs = passengerService.getAvailableCabs(requestBody);

		log.debug("Response for getting available cabs for passenger : " + availableCabs);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(availableCabs, responseHeaders, HttpStatus.OK);
	}

}
