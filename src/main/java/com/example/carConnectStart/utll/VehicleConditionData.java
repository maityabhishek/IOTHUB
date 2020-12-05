package com.example.carConnectStart.utll;

import com.microsoft.azure.sdk.iot.device.Message;

public class VehicleConditionData implements CarDataGenerator {

	@Override
	public Message generateData()
	{
		String msgStr = "{\"coolanttemp\":" + ((int)(200*Math.random()%150)) +",\"engineload\":" + ((int)(100*Math.random()%100)) + "}";
		Message msg = new Message(msgStr);
		System.out.println(msgStr);
		return msg;
		
	}

}
