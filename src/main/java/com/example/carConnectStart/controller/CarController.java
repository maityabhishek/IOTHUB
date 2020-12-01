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

@RestController
@Data
public class CarController {

	@Autowired
	CarService service;
	
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
	public void addRecord(@RequestBody Car car) throws IllegalArgumentException, URISyntaxException {
		connect();
		   service.saveUpdate(car);
		   
	}
	
	
	// Copyright (c) Microsoft. All rights reserved.
	// Licensed under the MIT license. See LICENSE file in the project root for full license information.

	public void connect()  {

	        System.out.println("Starting...");
	        System.out.println("Beginning setup.");

	        String pathToCertificate = null ;
			/*
			 * if (args.length <= 1 || args.length >= 5) { System.out.format(
			 * "Expected 2 or 3 arguments but received: %d.\n" +
			 * "The program should be called with the following args: \n" +
			 * "1. [Device connection string] - String containing Hostname, Device Id & Device Key in one of the following formats: HostName=<iothub_host_name>;DeviceId=<device_id>;SharedAccessKey=<device_key>\n"
			 * + "2. [number of requests to send]\n" +
			 * "3. (mqtt | https | amqps | amqps_ws | mqtt_ws)\n" +
			 * "4. (optional) path to certificate to enable one-way authentication over ssl for amqps \n"
			 * , args.length); return; }
			 */

	        String connString ="HostName=giciothub.azure-devices.net;DeviceId=giciotdevice;SharedAccessKey=nHeP0GXc4FqjDbUk2yDVk3d6lCyjm9Gzt0/0D30VDcE=";
	        int numRequests=2;
			/*
			 * try { numRequests = Integer.parseInt(args[1]); } catch (NumberFormatException
			 * e) { System.out.format( "Could not parse the number of requests to send. " +
			 * "Expected an int but received:\n%s.\n", args[1]); return; }
			 */
	        IotHubClientProtocol protocol;
	       
	            String protocolStr = "https";
	            
	            protocol = IotHubClientProtocol.HTTPS;
	            

	        System.out.println("Successfully read input parameters.");
	        System.out.format("Using communication protocol %s.\n", protocol.name());

	        DeviceClient client = null;
			try {
				client = new DeviceClient(connString, protocol);
			} catch (IllegalArgumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (URISyntaxException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        
		/*
		 * if (pathToCertificate != null) { client.setOption("SetCertificatePath",
		 * pathToCertificate); }
		 */

	        System.out.println("Successfully created an IoT Hub client.");

	     
	            MessageCallback callback = new MessageCallback();
	            Counter counter = new Counter(0);
	            client.setMessageCallback(callback, counter);
	        
	        System.out.println("Successfully set message callback.");

	        // Set your token expiry time limit here
	        long time = 2400;
	       // client.setOption("SetSASTokenExpiryTime", time);
System.out.println("Ho");
	        client.registerConnectionStatusChangeCallback(new IotHubConnectionStatusChangeCallbackLogger(), new Object());
	        System.out.println("Ho1");
	        try {
	        	System.out.println("Ho2");
				client.open();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        System.out.println("Opened connection to IoT Hub.");

	        System.out.println("Beginning to receive messages...");

	        System.out.println("Sending the following event messages: ");

	        System.out.println("Updated token expiry time to " + time);

	        String deviceId = "giciotdevice";
	        double temperature = 0.0;
	        double humidity = 0.0;

	        for (int i = 0; i < numRequests; ++i)
	        {
	            temperature = 20 + Math.random() * 10;
	            humidity = 30 + Math.random() * 20;

	            String msgStr = "{\"deviceId\":\"" + deviceId +"\",\"messageId\":" + i + ",\"temperature\":"+ temperature +",\"humidity\":"+ humidity +"}";
	            
	            try
	            {
	                Message msg = new Message(msgStr);
	                msg.setContentTypeFinal("application/json");
	                msg.setProperty("temperatureAlert", temperature > 28 ? "true" : "false");
	                msg.setMessageId(java.util.UUID.randomUUID().toString());
	                msg.setExpiryTime(D2C_MESSAGE_TIMEOUT);
	                System.out.println(msgStr);
	                EventCallback eventCallback = new EventCallback();
	                client.sendEventAsync(msg, eventCallback, msg);
	            } 
	            
	            catch (Exception e)
	            {
	                e.printStackTrace(); // Trace the exception
	            }
	           
	        }
	        
	         System.out.println("Wait for " + D2C_MESSAGE_TIMEOUT / 1000 + " second(s) for response from the IoT Hub...");
	        
	        // Wait for IoT Hub to respond.
	        try
	        {
	          Thread.sleep(D2C_MESSAGE_TIMEOUT);
	        }
	      
	        catch (InterruptedException e)
	        {
	          e.printStackTrace();
	        }

	        System.out.println("In receive mode. Waiting for receiving C2D messages (only for MQTT and AMQP). Press ENTER to close. To recieve in Https, send message and then start the sample.");
	    
	        Scanner scanner = new Scanner(System.in);
	        scanner.nextLine();

	        // close the connection        
	        System.out.println("Closing"); 
	        try {
				client.closeNow();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        if (!failedMessageListOnClose.isEmpty())
	        {
	            System.out.println("List of messages that were cancelled on close:" + failedMessageListOnClose.toString()); 
	        }

	        System.out.println("Shutting down...");
	    }
	 private static final int D2C_MESSAGE_TIMEOUT = 2000; // 2 seconds
	    private static List failedMessageListOnClose = new ArrayList(); // List of messages that failed on close

	    /** Used as a counter in the message callback. */
	    protected static class Counter
	    {
	        protected int num;

	        public Counter(int num)
	        {
	            this.num = num;
	        }

	        public int get()
	        {
	            return this.num;
	        }

	        public void increment()
	        {
	            this.num++;
	        }

	        @Override
	        public String toString()
	        {
	            return Integer.toString(this.num);
	        }
	    }

	   protected static class MessageCallback
	             implements com.microsoft.azure.sdk.iot.device.MessageCallback
	    {
	        public IotHubMessageResult execute(Message msg,
	                Object context)
	        {
	            Counter counter = (Counter) context;
	            System.out.println(
	                    "Received message " + counter.toString()
	                            + " with content: " + new String(msg.getBytes(), Message.DEFAULT_IOTHUB_MESSAGE_CHARSET));
	            for (MessageProperty messageProperty : msg.getProperties())
	            {
	                System.out.println(messageProperty.getName() + " : " + messageProperty.getValue());
	            }

	            int switchVal = counter.get() % 3;
	            IotHubMessageResult res;
	            switch (switchVal)
	            {
	                case 0:
	                    res = IotHubMessageResult.COMPLETE;
	                    break;
	                case 1:
	                    res = IotHubMessageResult.ABANDON;
	                    break;
	                case 2:
	                    res = IotHubMessageResult.REJECT;
	                    break;
	                default:
	                    // should never happen.
	                    throw new IllegalStateException(
	                            "Invalid message result specified.");
	            }

	            System.out.println(
	                    "Responding to message " + counter.toString()
	                            + " with " + res.name());

	            counter.increment();

	            return res;
	        }
	    }

	    // Our MQTT doesn't support abandon/reject, so we will only display the messaged received
	    // from IoTHub and return COMPLETE
	    protected static class MessageCallbackMqtt implements com.microsoft.azure.sdk.iot.device.MessageCallback
	    {
	        public IotHubMessageResult execute(Message msg, Object context)
	        {
	            Counter counter = (Counter) context;
	            System.out.println(
	                    "Received message " + counter.toString()
	                            + " with content: " + new String(msg.getBytes(), Message.DEFAULT_IOTHUB_MESSAGE_CHARSET));
	            for (MessageProperty messageProperty : msg.getProperties())
	            {
	                System.out.println(messageProperty.getName() + " : " + messageProperty.getValue());
	            }

	            counter.increment();

	            return IotHubMessageResult.COMPLETE;
	        }
	    }

	    protected static class EventCallback implements IotHubEventCallback
	    {
	        public void execute(IotHubStatusCode status, Object context)
	        {
	            Message msg = (Message) context;
	            System.out.println("IoT Hub responded to message "+ msg.getMessageId()  + " with status " + status.name());
	            if (status==IotHubStatusCode.MESSAGE_CANCELLED_ONCLOSE)
	            {
	                failedMessageListOnClose.add(msg.getMessageId());
	            }
	        }
	    }

	    protected static class IotHubConnectionStatusChangeCallbackLogger implements IotHubConnectionStatusChangeCallback
	    {
	        @Override
	        public void execute(IotHubConnectionStatus status, IotHubConnectionStatusChangeReason statusChangeReason, Throwable throwable, Object callbackContext)
	        {
	            System.out.println();
	            System.out.println("CONNECTION STATUS UPDATE: " + status);
	            System.out.println("CONNECTION STATUS REASON: " + statusChangeReason);
	            System.out.println("CONNECTION STATUS THROWABLE: " + (throwable == null ? "null" : throwable.getMessage()));
	            System.out.println();

	            if (throwable != null)
	            {
	                throwable.printStackTrace();
	            }

	            if (status == IotHubConnectionStatus.DISCONNECTED)
	            {
	                //connection was lost, and is not being re-established. Look at provided exception for
	                // how to resolve this issue. Cannot send messages until this issue is resolved, and you manually
	                // re-open the device client
	            }
	            else if (status == IotHubConnectionStatus.DISCONNECTED_RETRYING)
	            {
	                //connection was lost, but is being re-established. Can still send messages, but they won't
	                // be sent until the connection is re-established
	            }
	            else if (status == IotHubConnectionStatus.CONNECTED)
	            {
	                //Connection was successfully re-established. Can send messages.
	            }
	        }
	    }
	}

