package com.example.cabSelection.data;

import com.example.cabSelection.model.Driver;
import com.example.cabSelection.repository.DriverRepository;
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
