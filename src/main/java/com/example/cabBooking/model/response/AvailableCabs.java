package com.example.cabBooking.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvailableCabs {

	@JsonProperty ("available_cabs")
	private List<CabDriver> cabDrivers;

	public List<CabDriver> getCabDrivers() {
		return cabDrivers;
	}

	public void setCabDrivers(List<CabDriver> cabDrivers) {
		this.cabDrivers = cabDrivers;
	}

	@Override
	public String toString() {
		return "AvailableCabs{" +
				"cabDrivers=" + cabDrivers +
				'}';
	}
}
