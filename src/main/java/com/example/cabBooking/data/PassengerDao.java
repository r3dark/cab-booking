package com.example.cabBooking.data;

import com.example.cabBooking.model.Driver;
import com.example.cabBooking.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassengerDao {

	@Autowired
	DriverRepository driverRepository;

	public Iterable<Driver> getAllDrivers() {

		return driverRepository.findAll();
	}
}
