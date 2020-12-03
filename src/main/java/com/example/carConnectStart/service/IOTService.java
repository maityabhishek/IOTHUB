package com.example.carConnectStart.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

@Service
public class IOTService {
	
	@Autowired
	IOTSender iotSender;
	
	@Autowired
	IOTReceiver iotReceiver;
	public void connect() {

		System.out.println("Starting...");
		System.out.println("Beginning setup.");
		int numRequests=5;
		String pathToCertificate = null;

		String connString = "HostName=maity-hub.azure-devices.net;DeviceId=maity-device;SharedAccessKey=Xo9RgKAxY5WtwMxr2qvNYLhhrdBQFGJVpmrgN7GDI2I=";

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
		iotSender.sendMessageToIOTHUB(numRequests, client);
		
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
