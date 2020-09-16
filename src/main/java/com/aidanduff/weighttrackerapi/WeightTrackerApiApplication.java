package com.aidanduff.weighttrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aidanduff.weighttrackerapi.dao.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class WeightTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeightTrackerApiApplication.class, args);
	}

}
