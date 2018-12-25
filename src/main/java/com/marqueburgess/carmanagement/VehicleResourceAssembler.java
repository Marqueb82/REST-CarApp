package com.marqueburgess.carmanagement;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class VehicleResourceAssembler implements ResourceAssembler<Vehicle, Resource<Vehicle>> {

	// converts Vehicle objects to Resource<Vehicle> objects

	@Override
	public Resource<Vehicle> toResource(Vehicle vehicle) {

		return new Resource<>(vehicle,
				linkTo(methodOn(VehicleController.class).findVehicle(vehicle.getVehicleId())).withSelfRel(),
				linkTo(methodOn(VehicleController.class).findAll()).withRel("vehicles"));
	}

}
