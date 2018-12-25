package com.marqueburgess.carmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	// domain type set as Vehicle and id type is Long
	// interface responsible for:
	// Creating new instances
	// Updating existing ones
	// Deleting
	// Finding (one, all, by simple or complex properties)
}
