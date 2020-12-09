package com.example.carConnectStart.model;

public class ObdData {
	private int speed;
	private int rpm;
	private int fuel_level;
	private int engine_coolant_temp;
	private int calculated_engine_load;
	private int fuel_pressure;
	private int intake_manifold_absolute_pressure;
	private int intake_air_temp;
	private int MAF_air_flow_rate;
	private int throttle_position;
	public int getSpeed() {
		return speed;
	}
	@Override
	public String toString() {
		return "{\"speed\"=" + speed + ", \"rpm\"=" + rpm + ", \"fuel_level\"=" + fuel_level + ", \"engine_coolant_temp\"="
				+ engine_coolant_temp + ", \"calculated_engine_load\"=" + calculated_engine_load + ", \"fuel_pressure\"="
				+ fuel_pressure + ", \"intake_manifold_absolute_pressure\"=" + intake_manifold_absolute_pressure
				+ ", \"intake_air_temp\"=" + intake_air_temp + ", \"MAF_air_flow_rate\"=" + MAF_air_flow_rate
				+ ", \"throttle_position\"=" + throttle_position +"\"vin\":5"+ "}";
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getRpm() {
		return rpm;
	}
	public void setRpm(int rpm) {
		this.rpm = rpm;
	}
	public int getFuel_level() {
		return fuel_level;
	}
	public void setFuel_level(int fuel_level) {
		this.fuel_level = fuel_level;
	}
	public int getEngine_coolant_temp() {
		return engine_coolant_temp;
	}
	public void setEngine_coolant_temp(int engine_coolant_temp) {
		this.engine_coolant_temp = engine_coolant_temp;
	}
	public int getCalculated_engine_load() {
		return calculated_engine_load;
	}
	public void setCalculated_engine_load(int calculated_engine_load) {
		this.calculated_engine_load = calculated_engine_load;
	}
	public int getFuel_pressure() {
		return fuel_pressure;
	}
	public void setFuel_pressure(int fuel_pressure) {
		this.fuel_pressure = fuel_pressure;
	}
	public int getIntake_manifold_absolute_pressure() {
		return intake_manifold_absolute_pressure;
	}
	public void setIntake_manifold_absolute_pressure(int intake_manifold_absolute_pressure) {
		this.intake_manifold_absolute_pressure = intake_manifold_absolute_pressure;
	}
	public int getIntake_air_temp() {
		return intake_air_temp;
	}
	public void setIntake_air_temp(int intake_air_temp) {
		this.intake_air_temp = intake_air_temp;
	}
	public int getMAF_air_flow_rate() {
		return MAF_air_flow_rate;
	}
	public void setMAF_air_flow_rate(int mAF_air_flow_rate) {
		MAF_air_flow_rate = mAF_air_flow_rate;
	}
	public int getThrottle_position() {
		return throttle_position;
	}
	public void setThrottle_position(int throttle_position) {
		this.throttle_position = throttle_position;
	}
	public ObdData(int speed, int rpm, int fuel_level, int engine_coolant_temp, int calculated_engine_load,
			int fuel_pressure, int intake_manifold_absolute_pressure, int intake_air_temp, int mAF_air_flow_rate,
			int throttle_position) {
		super();
		this.speed = speed;
		this.rpm = rpm;
		this.fuel_level = fuel_level;
		this.engine_coolant_temp = engine_coolant_temp;
		this.calculated_engine_load = calculated_engine_load;
		this.fuel_pressure = fuel_pressure;
		this.intake_manifold_absolute_pressure = intake_manifold_absolute_pressure;
		this.intake_air_temp = intake_air_temp;
		MAF_air_flow_rate = mAF_air_flow_rate;
		this.throttle_position = throttle_position;
	}
	public ObdData() {
		super();
		
	}
	
	
}
	
