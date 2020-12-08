package com.example.carConnectStart.utll;



import com.microsoft.azure.sdk.iot.device.Message;

public class VehicleEmissionData implements CarDataGenerator {
	

	@Override
	public Message generateData() {
		String msgStr = "{\"co2level\":"+ ((int)(200*Math.random()%150)) +",\"nolevel\":"+ ((int)(200*Math.random()%60))+ ",\"vin\":"+ ((int)(10000*Math.random()))+"}";
		Message msg = new Message(msgStr);
		System.out.println(msgStr);
		return msg;
	}

}
