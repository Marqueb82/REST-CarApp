package com.marqueburgess.carmanagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
// Lombok annotation to create all the getters, setters, 
// equals, hash, and toString methods, based on the fields

@Entity
class Vehicle {

	private @Id @GeneratedValue Long vehicleId;
	private String vehicleMake;
	private String vehicleModel;
	private Integer vehicleYear;

	public Vehicle() {
	}

	public Vehicle(String vehicleMake, String vehicleModel, Integer vehicleYear) {
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
		this.vehicleYear = vehicleYear;
	}

}
