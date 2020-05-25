package com.example.cabBooking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class Driver {

	@Id
	@Column (unique = true)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id;

	@Column (nullable = false)
	@JsonProperty("name")
	private String name;

	@Column (unique = true, nullable = false)
	@JsonProperty("email")
	private String email;

	@Column (unique = true, nullable = false)
	@JsonProperty("phone_number")
	private Long phoneNumber;

	@Column (unique = true, nullable = false)
	@JsonProperty("license_number")
	private String licenseNumber;

	@Column (unique = true, nullable = false)
	@JsonProperty("car_number")
	private String carNumber;

	@OneToOne (targetEntity = Location.class, cascade = CascadeType.ALL)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Location location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Driver{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", phoneNumber=" + phoneNumber +
				", licenseNumber='" + licenseNumber + '\'' +
				", carNumber='" + carNumber + '\'' +
				", location=" + location +
				'}';
	}
}
