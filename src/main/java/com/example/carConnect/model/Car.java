package com.example.carConnect.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
public class Car {

	@Id
	private String TripId;
	private String UserId;
	private String Name;
	private String TripPointId;
	private double Lat;
	private double Lon;
	private double Speed;
	private int Sequence;
	@Temporal(TemporalType.DATE)
	private Date RecordedTimeStamp;

	private double EngineRPM;
	private double ShortTermFuelBank;
	private double LargeTermFuelBank;
	private double ThrottlePosition;
	private double RelativeThrottlePosition;
	private int Runtime;
	private double DistancewithMIL;

	private double EngineLoad;
	private double MAFFlowRate;
	private double OutsideTemperature;
	private double EngineFuelRate;
	private String vin;
	public Car() {
		super();
	}

	public Car(String tripId, String userId, String name, String tripPointId, double lat, double lon, double speed,
			int sequence, Date recordedTimeStamp, double engineRPM, double shortTermFuelBank, double largeTermFuelBank,
			double throttlePosition, double relativeThrottlePosition, int runtime, double distancewithMIL,
			double engineLoad, double mAFFlowRate, double outsideTemperature, double engineFuelRate, String vin) {
		super();
		TripId = tripId;
		UserId = userId;
		Name = name;
		TripPointId = tripPointId;
		Lat = lat;
		Lon = lon;
		Speed = speed;
		Sequence = sequence;
		RecordedTimeStamp = recordedTimeStamp;
		EngineRPM = engineRPM;
		ShortTermFuelBank = shortTermFuelBank;
		LargeTermFuelBank = largeTermFuelBank;
		ThrottlePosition = throttlePosition;
		RelativeThrottlePosition = relativeThrottlePosition;
		Runtime = runtime;
		DistancewithMIL = distancewithMIL;
		EngineLoad = engineLoad;
		MAFFlowRate = mAFFlowRate;
		OutsideTemperature = outsideTemperature;
		EngineFuelRate = engineFuelRate;
		this.vin = vin;
	}

	public String getTripId() {
		return TripId;
	}
	public void setTripId(String tripId) {
		TripId = tripId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTripPointId() {
		return TripPointId;
	}
	public void setTripPointId(String tripPointId) {
		TripPointId = tripPointId;
	}
	public double getLat() {
		return Lat;
	}
	public void setLat(double lat) {
		Lat = lat;
	}
	public double getLon() {
		return Lon;
	}
	public void setLon(double lon) {
		Lon = lon;
	}
	public double getSpeed() {
		return Speed;
	}
	public void setSpeed(double speed) {
		Speed = speed;
	}
	public Date getRecordedTimeStamp() {
		return RecordedTimeStamp;
	}
	public void setRecordedTimeStamp(Date recordedTimeStamp) {
		RecordedTimeStamp = recordedTimeStamp;
	}
	public int getSequence() {
		return Sequence;
	}
	public void setSequence(int sequence) {
		Sequence = sequence;
	}
	public double getEngineRPM() {
		return EngineRPM;
	}
	public void setEngineRPM(double engineRPM) {
		EngineRPM = engineRPM;
	}
	public double getShortTermFuelBank() {
		return ShortTermFuelBank;
	}
	public void setShortTermFuelBank(double shortTermFuelBank) {
		ShortTermFuelBank = shortTermFuelBank;
	}
	public double getLargeTermFuelBank() {
		return LargeTermFuelBank;
	}
	public void setLargeTermFuelBank(double largeTermFuelBank) {
		LargeTermFuelBank = largeTermFuelBank;
	}
	public double getThrottlePosition() {
		return ThrottlePosition;
	}
	public void setThrottlePosition(double throttlePosition) {
		ThrottlePosition = throttlePosition;
	}
	public double getRelativeThrottlePosition() {
		return RelativeThrottlePosition;
	}
	public void setRelativeThrottlePosition(double relativeThrottlePosition) {
		RelativeThrottlePosition = relativeThrottlePosition;
	}
	public int getRuntime() {
		return Runtime;
	}
	public void setRuntime(int runtime) {
		Runtime = runtime;
	}
	public double getDistancewithMIL() {
		return DistancewithMIL;
	}
	public void setDistancewithMIL(double distancewithMIL) {
		DistancewithMIL = distancewithMIL;
	}
	public double getEngineLoad() {
		return EngineLoad;
	}
	public void setEngineLoad(double engineLoad) {
		EngineLoad = engineLoad;
	}
	public double getMAFFlowRate() {
		return MAFFlowRate;
	}
	public void setMAFFlowRate(double mAFFlowRate) {
		MAFFlowRate = mAFFlowRate;
	}
	public double getOutsideTemperature() {
		return OutsideTemperature;
	}
	public void setOutsideTemperature(double outsideTemperature) {
		OutsideTemperature = outsideTemperature;
	}
	public double getEngineFuelRate() {
		return EngineFuelRate;
	}
	public void setEngineFuelRate(double engineFuelRate) {
		EngineFuelRate = engineFuelRate;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Car(int sequence, String tripId, String userId, String name, String tripPointId, double lat, double lon,
			double speed, Date recordedTimeStamp, double engineRPM, double shortTermFuelBank, double largeTermFuelBank,
			double throttlePosition, double relativeThrottlePosition, int runtime, double distancewithMIL,
			double engineLoad, double mAFFlowRate, double outsideTemperature, double engineFuelRate, String vin) {
		super();
		Sequence = sequence;
		TripId = tripId;
		UserId = userId;
		Name = name;
		TripPointId = tripPointId;
		Lat = lat;
		Lon = lon;
		Speed = speed;
		RecordedTimeStamp = recordedTimeStamp;
		EngineRPM = engineRPM;
		ShortTermFuelBank = shortTermFuelBank;
		LargeTermFuelBank = largeTermFuelBank;
		ThrottlePosition = throttlePosition;
		RelativeThrottlePosition = relativeThrottlePosition;
		Runtime = runtime;
		DistancewithMIL = distancewithMIL;
		EngineLoad = engineLoad;
		MAFFlowRate = mAFFlowRate;
		OutsideTemperature = outsideTemperature;
		EngineFuelRate = engineFuelRate;
		this.vin = vin;
	}

}
