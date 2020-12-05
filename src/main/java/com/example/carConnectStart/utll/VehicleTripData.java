package com.example.carConnectStart.utll;

import java.util.Date;

import com.microsoft.azure.sdk.iot.device.Message;

public class VehicleTripData implements CarDataGenerator {

	@Override
	public Message generateData() {
		String msgStr = "{\"speed\":" + ((int)(200*Math.random()%150)) +",\"timestamp\":" + new Date() +",\"fuel\":"+ ((int)(200*Math.random()%60))+"}";
		Message msg = new Message(msgStr);
		System.out.println(msgStr);
		return msg;
	}

}
