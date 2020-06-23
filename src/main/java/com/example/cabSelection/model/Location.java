package com.example.cabSelection.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class Location {

	@Id
	@Column (unique = true)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column
	private Double latitude;

	@Column
	private Double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location{" +
				"id=" + id +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
