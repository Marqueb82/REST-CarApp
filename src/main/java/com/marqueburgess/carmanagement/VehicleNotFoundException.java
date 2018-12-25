package com.marqueburgess.carmanagement;

@SuppressWarnings("serial")
class VehicleNotFoundException extends RuntimeException {

	// custom exception
	VehicleNotFoundException(Long id) {
		super("Could not find vehicle " + id);
	}

}
