package com.example.carConnectStart.service;

import org.springframework.stereotype.Service;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubMessageResult;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.MessageProperty;
@Service
public class IOTReceiver {

	public void receiveMessageFromIOTHUB(int numRequests ,DeviceClient client) {

	MessageCallback callback = new MessageCallback();
	Counter counter = new Counter(0);
	client.setMessageCallback(callback, counter);

	System.out.println("Successfully set message callback.");
	System.out.println(
			"In receive mode. Waiting for receiving C2D messages (only for MQTT and AMQP). Press ENTER to close. To recieve in Https, send message and then start the sample.");


	}
	protected static class MessageCallback implements com.microsoft.azure.sdk.iot.device.MessageCallback {
		public IotHubMessageResult execute(Message msg, Object context) {
			Counter counter = (Counter) context;
			System.out.println("Received message " + counter.toString() + " with content: "
					+ new String(msg.getBytes(), Message.DEFAULT_IOTHUB_MESSAGE_CHARSET));
			for (MessageProperty messageProperty : msg.getProperties()) {
				System.out.println(messageProperty.getName() + " : " + messageProperty.getValue());
			}

			int switchVal = counter.get() % 3;
			IotHubMessageResult res;
			switch (switchVal) {
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
				throw new IllegalStateException("Invalid message result specified.");
			}

			System.out.println("Responding to message " + counter.toString() + " with " + res.name());

			counter.increment();

			return res;
		}
	}
	// Our MQTT doesn't support abandon/reject, so we will only display the messaged
		// received
		// from IoTHub and return COMPLETE
		protected static class MessageCallbackMqtt implements com.microsoft.azure.sdk.iot.device.MessageCallback {
			public IotHubMessageResult execute(Message msg, Object context) {
				Counter counter = (Counter) context;
				System.out.println("Received message " + counter.toString() + " with content: "
						+ new String(msg.getBytes(), Message.DEFAULT_IOTHUB_MESSAGE_CHARSET));
				for (MessageProperty messageProperty : msg.getProperties()) {
					System.out.println(messageProperty.getName() + " : " + messageProperty.getValue());
				}

				counter.increment();

				return IotHubMessageResult.COMPLETE;
			}
		}
		
		/** Used as a counter in the message callback. */
		protected static class Counter {
			protected int num;

			public Counter(int num) {
				this.num = num;
			}

			public int get() {
				return this.num;
			}

			public void increment() {
				this.num++;
			}

			@Override
			public String toString() {
				return Integer.toString(this.num);
			}
		}
		}


