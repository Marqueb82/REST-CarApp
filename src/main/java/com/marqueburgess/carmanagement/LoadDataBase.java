package com.marqueburgess.carmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDataBase {

	// runs when app starts
	// requests vehicle repository
	// uses it to create two Vehicle entities
	// new entities then logged

	@Bean
	CommandLineRunner initDatabase(VehicleRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Vehicle("Ford", "Mustang", 2018)));
			log.info("Preloading " + repository.save(new Vehicle("Dodge", "Charger SRT", 2018)));
		};
	}
}
