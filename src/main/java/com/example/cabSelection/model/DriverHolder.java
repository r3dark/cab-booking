package com.example.cabSelection.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverHolder {

	private List<Driver> drivers;

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	@Override
	public String toString() {
		return "DriverHolder{" +
				"drivers=" + drivers +
				'}';
	}
}
