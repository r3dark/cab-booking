package com.example.cabSelection.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CabDriver {

	@JsonProperty("name")
	private String name;

	@JsonProperty("car_number")
	private String carNumber;

	@JsonProperty("phone_number")
	private Long phoneNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CabDriver{" +
				"name='" + name + '\'' +
				", carNumber='" + carNumber + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}
}
