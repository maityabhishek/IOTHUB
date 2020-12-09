package com.example.carConnectStart.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carConnectStart.utll.VehicleAllData;
import com.example.carConnectStart.utll.VehicleConditionData;
import com.example.carConnectStart.utll.VehicleEmissionData;
import com.example.carConnectStart.utll.VehicleTripData;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

@Service
public class IOTService {
	
	@Autowired
	IOTSender iotSender;
	
	@Autowired
	IOTReceiver iotReceiver;
	public void connect(int datamodelno) {

		System.out.println("Starting...");
		System.out.println("Beginning setup.");
		int numRequests=200;
		String pathToCertificate = null;

		String connString = "HostName=giciothub.azure-devices.net;DeviceId=giciotdevice;SharedAccessKey=SOaCbgQqpuheExFC6efzueLOojywcjOmuZAn8EPGhu4=";
		//String connString ="HostName=iot-eventhub.azure-devices.net;DeviceId=dev1;SharedAccessKey=6o57ILHnYxDv1xYETcg2QrinmS52ppq4Y9n4kKoSAZI=";
		IotHubClientProtocol protocol= IotHubClientProtocol.HTTPS;

		String protocolStr = "https";

		System.out.println("Successfully read input parameters.");
		System.out.format("Using communication protocol %s.\n", protocol.name());

		DeviceClient client = null;
		try {
			System.out.println("S");
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
		
		switch(datamodelno)
		{
		case 1:
			iotSender.sendMessageToIOTHUB(numRequests, client,new VehicleTripData());
			break;
		case 2:
			iotSender.sendMessageToIOTHUB(numRequests, client,new VehicleEmissionData());
			break;
		case 3:
			iotSender.sendMessageToIOTHUB(numRequests, client,new VehicleConditionData());
			break;
		case 4:
			iotSender.sendMessageToIOTHUB(numRequests, client,new VehicleAllData());
			break;
		}
		
		
		
		/*
		 * Scanner scanner = new Scanner(System.in); scanner.nextLine();
		 * 
		 * // close the connection System.out.println("Closing"); try {
		 * client.closeNow(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * if (!failedMessageListOnClose.isEmpty()) {
		 * System.out.println("List of messages that were cancelled on close:" +
		 * failedMessageListOnClose.toString()); }
		 */
		//System.out.println("Shutting down...");
	}

	





	




}
