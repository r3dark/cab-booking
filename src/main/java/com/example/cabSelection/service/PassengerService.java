package com.example.cabSelection.service;

import lombok.extern.slf4j.Slf4j;
import com.example.cabSelection.data.PassengerDao;
import com.example.cabSelection.exception.ErrorMessage;
import com.example.cabSelection.exception.InvalidRequestException;
import com.example.cabSelection.model.Driver;
import com.example.cabSelection.model.Location;
import com.example.cabSelection.model.response.AvailableCabs;
import com.example.cabSelection.model.response.CabDriver;
import com.example.cabSelection.model.response.CabUnavailableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.cabSelection.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PassengerService {

	@Autowired
	PassengerDao passengerDao;

	private static final String CABS_UNAVAILABLE_MESSAGE = "No cabs available!";

	private static final Double EARTH_RADIUS_IN_KM = 6371.0;

	private static final Double ALLOWED_DISTANCE = 4.0;

	public String getAvailableCabs(String requestBody) throws Exception {

		try {
			Location location = (Location) JsonUtils.toObject(requestBody, Location.class);

			if (validateGetAvailableCabsRequest(location)) {
				AvailableCabs availableCabs = getCabsForPassenger(location);
				if (availableCabs.getCabDrivers().size() != 0) {
					return JsonUtils.toJson(availableCabs);
				} else {
					CabUnavailableResponse cabUnavailableResponse = new CabUnavailableResponse();
					cabUnavailableResponse.setMessage(CABS_UNAVAILABLE_MESSAGE);
					return JsonUtils.toJson(cabUnavailableResponse);
				}
			} else {
				throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while getting available cabs for location : " + requestBody, ex);
			throw ex;
		}
	}

	private boolean validateGetAvailableCabsRequest(Location location) {

		return (!ObjectUtils.isEmpty(location)
				&& !ObjectUtils.isEmpty(location.getLatitude())
				&& !ObjectUtils.isEmpty(location.getLongitude())
		);
	}

	private AvailableCabs getCabsForPassenger(Location passengerLocation) {

		AvailableCabs availableCabs = new AvailableCabs();
		availableCabs.setCabDrivers(new ArrayList<>());

		List<Driver> drivers = getListOfDrivers();
		drivers.forEach(driver -> {
			if (driver.getLocation() != null) {
				Double distance = calculateDistanceBetweenPassengerAndDriver(passengerLocation, driver.getLocation());
				if (distance <= ALLOWED_DISTANCE) {
					CabDriver cabDriver = new CabDriver();
					cabDriver.setName(driver.getName());
					cabDriver.setCarNumber(driver.getCarNumber());
					cabDriver.setPhoneNumber(driver.getPhoneNumber());
					availableCabs.getCabDrivers().add(cabDriver);
				}
			}
		});

		return availableCabs;
	}

	private Double calculateDistanceBetweenPassengerAndDriver(Location passengerLocation, Location driverLocation) {

//		using Haversine formula
		double latitudinalDistance = Math.toRadians(passengerLocation.getLatitude() - driverLocation.getLatitude());
		double longitudinalDistance = Math.toRadians(passengerLocation.getLongitude() - driverLocation.getLongitude());

		double passengerLatitude = Math.toRadians(passengerLocation.getLatitude());
		double driverLatitude = Math.toRadians(driverLocation.getLatitude());

		double temp = Math.pow(Math.sin(latitudinalDistance / 2), 2) +
							(Math.pow(Math.sin(longitudinalDistance / 2), 2) *
							Math.cos(passengerLatitude) * Math.cos(driverLatitude));
		return 2 * EARTH_RADIUS_IN_KM * Math.asin(Math.sqrt(temp));
	}

	private List<Driver> getListOfDrivers() {

		Iterable<Driver> driverIterable = passengerDao.getAllDrivers();
		List<Driver> drivers = new ArrayList<>();
		driverIterable.forEach(drivers::add);
		return drivers;
	}
}
