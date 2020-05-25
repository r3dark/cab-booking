package com.example.cabBooking.service;

import lombok.extern.slf4j.Slf4j;
import com.example.cabBooking.data.DriverDao;
import com.example.cabBooking.exception.ErrorMessage;
import com.example.cabBooking.exception.InvalidRequestException;
import com.example.cabBooking.model.Driver;
import com.example.cabBooking.model.DriverHolder;
import com.example.cabBooking.model.Location;
import com.example.cabBooking.model.response.LocationSavedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.example.cabBooking.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DriverService {

	@Autowired
	DriverDao driverDao;

	private static final String SUCCESS_STATUS = "success";

	public String registerDriver(String requestBody) throws Exception {

		try {
			Driver driver = (Driver) JsonUtils.toObject(requestBody, Driver.class);

			if (validateRegisterDriverRequest(driver)) {
				Driver savedDriver = driverDao.saveDriver(driver);
				return JsonUtils.toJson(savedDriver);
			} else {
				throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while registering driver : " + requestBody, ex);
			throw ex;
		}
	}

	public String saveDriverLocation(String requestBody, Long id) throws Exception {

		try {
			Location location = (Location) JsonUtils.toObject(requestBody, Location.class);

			if (validateSaveLocationRequest(location)) {
				Driver driver = driverDao.getDriverById(id);

				if (ObjectUtils.isEmpty(driver)) {
					throw new InvalidRequestException(ErrorMessage.DRIVER_DOES_NOT_EXIST.getMessage(), HttpStatus.BAD_REQUEST);
				}

				setLocationForDriver(location, driver);

				LocationSavedResponse locationSavedResponse = new LocationSavedResponse();
				locationSavedResponse.setStatus(SUCCESS_STATUS);

				return JsonUtils.toJson(locationSavedResponse);
			} else {
				throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while saving driver location. driver ID : " + id, ex);
			throw ex;
		}
	}

	public String getAllDrivers() throws Exception {

		try {
			Iterable<Driver> driverIterable = driverDao.getAllDrivers();
			List<Driver> drivers = new ArrayList<>();
			driverIterable.forEach(drivers::add);
			DriverHolder driverHolder = new DriverHolder();
			driverHolder.setDrivers(drivers);
			return JsonUtils.toJson(driverHolder);
		} catch (Exception ex) {
			log.error("Error occurred while getting drivers.", ex);
			throw ex;
		}
	}

	private boolean validateRegisterDriverRequest(Driver driver) {

		return (!ObjectUtils.isEmpty(driver)
				&& !StringUtils.isEmpty(driver.getName())
				&& !StringUtils.isEmpty(driver.getEmail())
				&& !ObjectUtils.isEmpty(driver.getPhoneNumber())
				&& String.valueOf(driver.getPhoneNumber()).length() == 10
				&& !StringUtils.isEmpty(driver.getLicenseNumber())
				&& !StringUtils.isEmpty(driver.getCarNumber())
		);
	}

	private boolean validateSaveLocationRequest(Location location) {

		return (!ObjectUtils.isEmpty(location)
				&& !ObjectUtils.isEmpty(location.getLatitude())
				&& !ObjectUtils.isEmpty(location.getLongitude())
		);
	}

	private void setLocationForDriver(Location location, Driver driver) throws Exception {

		if (ObjectUtils.isEmpty(driver.getLocation())
		|| !driver.getLocation().getLatitude().equals(location.getLatitude())
		|| !driver.getLocation().getLongitude().equals(location.getLongitude())) {

			driver.setLocation(location);
			log.info("Saving location to DB for driver : " + driver.getId());
			driverDao.updateDriver(driver);
		}
	}
}
