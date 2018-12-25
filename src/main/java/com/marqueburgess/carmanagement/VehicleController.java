package com.marqueburgess.carmanagement;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

	private VehicleRepository repository;
	private VehicleResourceAssembler assembler;

	VehicleController(VehicleRepository repository, VehicleResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/vehicles")
	Resources<Resource<Vehicle>> findAll() {
		List<Resource<Vehicle>> vehicles = repository.findAll().stream().map(assembler::toResource)
				.collect(Collectors.toList());

		return new Resources<>(vehicles, linkTo(methodOn(VehicleController.class).findAll()).withSelfRel());
	}

	@PostMapping("/vehicles")
	ResponseEntity<Resource<Vehicle>> newVehicle(@RequestBody Vehicle vehicle) {
		Vehicle newVehicle = repository.save(vehicle);

		return ResponseEntity
				.created(linkTo(methodOn(VehicleController.class).findVehicle(newVehicle.getVehicleId())).toUri())
				.body(assembler.toResource(newVehicle));
	}

	@GetMapping("/vehicles/{id}")
	Resource<Vehicle> findVehicle(@PathVariable Long id) {
		return assembler.toResource(repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id)));
	}

	@PutMapping("/vehicles/{id}")
	ResponseEntity<?> updateVehicle(@RequestBody Vehicle newVehicle, @PathVariable Long id) throws URISyntaxException {

		Vehicle updatedVehicle = repository.findById(id).map(vehicle -> {
			vehicle.setVehicleMake(newVehicle.getVehicleMake());
			vehicle.setVehicleModel(newVehicle.getVehicleModel());
			vehicle.setVehicleYear(newVehicle.getVehicleYear());
			return repository.save(vehicle);
		}).orElseGet(() -> {
			newVehicle.setVehicleId(id);
			return repository.save(newVehicle);
		});

		Resource<Vehicle> resource = assembler.toResource(updatedVehicle);

		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);

	}

	@DeleteMapping("/vehicles/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
