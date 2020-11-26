package com.example.carConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.carConnect.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, Integer> {

}
