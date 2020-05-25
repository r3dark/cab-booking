package com.example.cabBooking.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import com.example.cabBooking.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;

@Slf4j
public class JsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object object) throws Exception {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception ex) {
			log.error("Error occurred while serializing object : " + object, ex);
			throw ex;
		}
	}

	public static Object toObject(String json, Class clazz) throws Exception {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (UnrecognizedPropertyException ue) {
			String errorMessage = ue.getMessage().substring(0, ue.getMessage().indexOf("("));
			log.error("Error occurred while de-serializing json : " + json + " to object : " + clazz, ue);
			throw new InvalidRequestException(errorMessage, HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			log.error("Error occurred while de-serializing json : " + json + " to object : " + clazz, ex);
			throw ex;
		}
	}
}
