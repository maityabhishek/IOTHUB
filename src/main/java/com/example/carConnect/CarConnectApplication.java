package com.example.carConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.carConnect.repository.CarRepository;
@EnableMongoRepositories(basePackageClasses = CarRepository.class)
@SpringBootApplication
public class CarConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarConnectApplication.class, args);
	}

}
