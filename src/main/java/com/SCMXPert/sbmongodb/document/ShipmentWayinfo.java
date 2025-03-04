package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

public class ShipmentWayinfo {
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Address;
	private String SensorUTC;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Device_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Shipment_Num;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String wayPoints;
	
	

	public String getWayPoints() {
		return wayPoints;
	}
	public void setWayPoints(String wayPoints) {
		this.wayPoints = wayPoints;
	}
	
	public String getShipment_Num() {
		return Shipment_Num;
	}
	public void setShipment_Num(String shipment_Num) {
		Shipment_Num = shipment_Num;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getSensorUTC() {
		return SensorUTC;
	}
	public void setSensorUTC(String sensorUTC) {
		SensorUTC = sensorUTC;
	}
	public String getDevice_Id() {
		return Device_Id;
	}
	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}	

}
