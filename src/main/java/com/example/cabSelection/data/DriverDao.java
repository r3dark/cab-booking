package com.example.cabSelection.data;

import lombok.extern.slf4j.Slf4j;
import com.example.cabSelection.exception.ErrorMessage;
import com.example.cabSelection.exception.InvalidRequestException;
import com.example.cabSelection.model.Driver;
import com.example.cabSelection.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DriverDao {

	@Autowired
	DriverRepository driverRepository;

	public Driver saveDriver(Driver driver) throws Exception {

		try {
			return driverRepository.save(driver);
		} catch (DataIntegrityViolationException de) {
			log.error("Driver already exists.", de);
			throw new InvalidRequestException(ErrorMessage.DRIVER_ALREADY_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			log.error("Error occurred while saving driver.", ex);
			throw ex;
		}
	}

	public Iterable<Driver> getAllDrivers() {

		return driverRepository.findAll();
	}

	public Driver getDriverById(Long id) {

		return driverRepository.findById(id).orElse(null);
	}

	public void updateDriver(Driver driver) throws Exception {

		saveDriver(driver);
	}
}
