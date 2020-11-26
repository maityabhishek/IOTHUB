package com.example.carConnect.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carConnect.model.Car;
import com.example.carConnect.service.CarService;

@RestController
public class CarController {

	@Autowired
	CarService service;

	@RequestMapping(value="/connect/obddata")
	public void listAllUsers(){
		 Car car1= new Car ("1", "1", "Rochit", "1", 100.50, 100.50, 80,1, null, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "2");
		 Car car2= new Car ("2", "2","2","Rishabh",200.50, 200.50, 90,1, null, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, "2");
		 service.addDummy(Arrays.asList(car1,car2));

	}


	@GetMapping("connect/obddata")
	public List<Car> viewRecord(){
		return service.listAll();
	}

	@PostMapping("connect/obddata")
	public String addRecord(@RequestBody Car car) {
		   service.saveUpdate(car);
		return "Added";
	}



}
