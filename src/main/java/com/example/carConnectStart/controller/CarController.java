package com.example.carConnectStart.controller;

import com.microsoft.azure.sdk.iot.device.*;
import com.microsoft.azure.sdk.iot.device.transport.IotHubConnectionStatus;

import lombok.Data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carConnectStart.model.Car;
import com.example.carConnectStart.service.CarService;
import com.example.carConnectStart.service.IOTService;

@RestController
@Data
public class CarController {

	@Autowired
	CarService service;
	
	@Autowired
	IOTService iotService;
	/*
	 * @PutMapping("/connect") public void listAllUsers(){
	 * 
	 * Car car1= new Car ("1", "1", "Rochit", "1", 100.50, 100.50, 80,1, null, 1, 1,
	 * 1, 1, 1, 1, 1, 1, 1, 1, 1, "2"); Car car2= new Car ("2", "2", "Rishabh","1",
	 * 200.50, 200.50, 90,1, null, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, "2");
	 * service.addDummy(Arrays.asList(car1,car2));
	 * 
	 * }
	 */

	
	@RequestMapping("/connect/obddata")
	public List<Car> viewRecord(){

		return service.listAll();
	}
	
	@PostMapping("/connect/obddata")
	public void addRecord(@RequestBody Car car) {
		iotService.connect();
		  // service.saveUpdate(car);
		   
	}
	
	
	// Copyright (c) Microsoft. All rights reserved.
	// Licensed under the MIT license. See LICENSE file in the project root for full license information.
}

