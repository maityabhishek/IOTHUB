package com.example.carConnect.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carConnect.model.Car;
import com.example.carConnect.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	CarRepository repo;
	
	public void addDummy(List<Car> ls){
		repo.saveAll(ls);
	}
	
	public List<Car> listAll(){
		return repo.findAll();
	}
	
	public void saveUpdate(Car car) {
		repo.save(car);
	}
	
}
