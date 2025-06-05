package com.herodevs.nes.trial.spring;


import com.herodevs.nes.trial.spring.model.Pet;
import com.herodevs.nes.trial.spring.repository.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Boot2ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot2ExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadApplicationData(PetRepository petRepository) {
		return args -> {
			// Add some sample data to the database
			petRepository.save(new Pet("Buddy", "Dog"));
			petRepository.save(new Pet("Spot", "Dog"));
			petRepository.save(new Pet("Mittens", "Cat"));
			petRepository.save(new Pet("Goldie", "Fish"));
			petRepository.save(new Pet("Tweety", "Bird"));
		};
	}
}
