package com.example.cabBooking.model.response;

public class CabUnavailableResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CabUnavailableResponse{" +
				"message='" + message + '\'' +
				'}';
	}
}
