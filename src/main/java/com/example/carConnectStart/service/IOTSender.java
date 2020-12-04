package com.example.carConnectStart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubConnectionStatusChangeCallback;
import com.microsoft.azure.sdk.iot.device.IotHubConnectionStatusChangeReason;
import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.transport.IotHubConnectionStatus;


@Service
public class IOTSender {
	//@Autowired
	//private IOTDeviceApi deviceapi;
	
	private static final int D2C_MESSAGE_TIMEOUT = 200000;   // 2 seconds
	private static List failedMessageListOnClose = new ArrayList(); // List of messages that failed on close
	public void sendMessageToIOTHUB(int numRequests ,DeviceClient client) {
	
	

	// Set your token expiry time limit here
			long time = 240000;
			// client.setOption("SetSASTokenExpiryTime", time);
			client.registerConnectionStatusChangeCallback(new IotHubConnectionStatusChangeCallbackLogger(), new Object());
			try {
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

			for (int i = 0; i < numRequests; ++i) {
				temperature = 20 + Math.random() * 10;
				humidity = 30 + Math.random() * 20;

				String msgStr = "{\"deviceId\":\"" + deviceId + "\",\"messageId\":" + i + ",\"speed\":" + ((int)(100*Math.random()%150))
						+ ",\"fuel\":" + ((int)((100*Math.random())%60)) + ",\"rpm\":" + ((int)(1000*Math.random()%1500)) + ",\"coolanttemp\":" + ((int)(200*Math.random()%150)) +",\"engineload\":" + ((int)(100*Math.random()%100)) +  "}";
				try {
					Message msg = new Message(msgStr);
					msg.setContentTypeFinal("application/json");
					msg.setProperty("temperatureAlert", temperature > 28 ? "true" : "false");
					msg.setMessageId(java.util.UUID.randomUUID().toString());
					msg.setExpiryTime(D2C_MESSAGE_TIMEOUT);
					System.out.println(msgStr);

					EventCallback eventCallback = new EventCallback();
					client.sendEventAsync(msg, eventCallback, msg);
				}

				catch (Exception e) {
					e.printStackTrace(); // Trace the exception
				}

			}
			System.out.println("Wait for " + D2C_MESSAGE_TIMEOUT / 1000 + " second(s) for response from the IoT Hub...");

			// Wait for IoT Hub to respond.
			try {
				Thread.sleep(D2C_MESSAGE_TIMEOUT);
			}

			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

			protected static class EventCallback implements IotHubEventCallback {
				public void execute(IotHubStatusCode status, Object context) {
					Message msg = (Message) context;
					System.out.println("IoT Hub responded to message " + msg.getMessageId() + " with status " + status.name());
					if (status == IotHubStatusCode.MESSAGE_CANCELLED_ONCLOSE) {
						failedMessageListOnClose.add(msg.getMessageId());
					}
				}
			}
			protected static class IotHubConnectionStatusChangeCallbackLogger implements IotHubConnectionStatusChangeCallback {
				@Override
				public void execute(IotHubConnectionStatus status, IotHubConnectionStatusChangeReason statusChangeReason,
						Throwable throwable, Object callbackContext) {
					System.out.println();
					System.out.println("CONNECTION STATUS UPDATE: " + status);
					System.out.println("CONNECTION STATUS REASON: " + statusChangeReason);
					System.out.println("CONNECTION STATUS THROWABLE: " + (throwable == null ? "null" : throwable.getMessage()));
					System.out.println();

					if (throwable != null) {
						throwable.printStackTrace();
					}

					if (status == IotHubConnectionStatus.DISCONNECTED) {
						// connection was lost, and is not being re-established. Look at provided
						// exception for
						// how to resolve this issue. Cannot send messages until this issue is resolved,
						// and you manually
						// re-open the device client
					} else if (status == IotHubConnectionStatus.DISCONNECTED_RETRYING) {
						// connection was lost, but is being re-established. Can still send messages,
						// but they won't
						// be sent until the connection is re-established
					} else if (status == IotHubConnectionStatus.CONNECTED) {
						// Connection was successfully re-established. Can send messages.
					}
				}
			}
	}
