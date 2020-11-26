package com.example.carConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carConnect.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
