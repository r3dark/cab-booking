package com.example.cabSelection.model.response;

public class ErrorResponse {

	private String status;

	private String reason;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ErrorResponse{" +
				"status='" + status + '\'' +
				", reason='" + reason + '\'' +
				'}';
	}
}
