package com.example.carConnectStart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.carConnectStart.model.Car;

@Service
public class CarService {


	private List<Car> car = new ArrayList<Car>(Arrays.asList(
			new Car("1", "1", "Rochit", "1", 100.50, 100.50, 80, 1, null, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "2"),
			new Car("2", "2", "Rishabh", "1", 200.50, 200.50, 90, 1, null, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, "2")

	));

	/*
	  public void addDummy(List<Car> ls){ repo.saveAll(ls); }
	  
	  
	  public List<Car> listAll(){ return repo.findAll(); }
	  
	  public void saveUpdate(Car car) { repo.save(car); }
	 */
	public List<Car> listAll(){ 
		return car;
	}
	 public void saveUpdate(Car car1) { 
		 car.add(car1);
	 }
	
}
