package com.example.cabSelection.model.response;

public class LocationSavedResponse {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LocationSavedResponse{" +
				"status='" + status + '\'' +
				'}';
	}
}
