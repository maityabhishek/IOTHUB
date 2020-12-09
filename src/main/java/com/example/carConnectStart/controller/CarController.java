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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.carConnectStart.model.Car;
import com.example.carConnectStart.model.ObdData;
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
	
	
	@RequestMapping(path="/senddata/trip",method=RequestMethod.GET)
	public ResponseEntity<?> sendTripData()
	{
		try {
			
			iotService.connect(1);
		}
		catch(Exception e)
		{
			new ResponseEntity<>("Data Sending Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Data Sent to IOT HUB",HttpStatus.OK);
	}
	@RequestMapping(path="/senddata/vcondition",method=RequestMethod.GET)
	public ResponseEntity<?> sendVehicleData()
	{
		try {
			
			iotService.connect(3);
		}
		catch(Exception e)
		{
			new ResponseEntity<>("Data Sending Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Data Sent to IOT HUB",HttpStatus.OK);
	}
	@RequestMapping(path="/senddata/econdition",method=RequestMethod.GET)
	public ResponseEntity<?> sendEmissionData()
	{
		try {
			
			iotService.connect(2);
		}
		catch(Exception e)
		{
			new ResponseEntity<>("Data Sending Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Data Sent to IOT HUB",HttpStatus.OK);
	}
	@RequestMapping(path="/senddata/all",method=RequestMethod.GET)
	public ResponseEntity<?> sendAllData()
	{
		try {
			
			iotService.connect(4);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Data Sending Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Data Sent to IOT HUB",HttpStatus.OK);
	}
	
	
	
	// Copyright (c) Microsoft. All rights reserved.
	// Licensed under the MIT license. See LICENSE file in the project root for full license information.
}

