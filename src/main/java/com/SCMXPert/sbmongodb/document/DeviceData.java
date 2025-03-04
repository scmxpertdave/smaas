package com.SCMXPert.sbmongodb.document;

public class DeviceData {

	private String Device_Id;
	private String Sensor_id;
	private String Message_Number;
	private String UTC;
	private String Speed;
	private String Report_type;
	private String Temp_Measurment;
	private String Internal_Temperature;
	private String BAT;
	private String Latitude;
	private String Longitude;
	private String Distance;
	private String Altitude;
	public String getDevice_Id() {
		return Device_Id;
	}
	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}
	public String getSensor_id() {
		return Sensor_id;
	}
	public void setSensor_id(String sensor_id) {
		Sensor_id = sensor_id;
	}
	public String getMessage_Number() {
		return Message_Number;
	}
	public void setMessage_Number(String message_Number) {
		Message_Number = message_Number;
	}
	public String getUTC() {
		return UTC;
	}
	public void setUTC(String uTC) {
		UTC = uTC;
	}
	public String getSpeed() {
		return Speed;
	}
	public void setSpeed(String speed) {
		Speed = speed;
	}
	public String getReport_type() {
		return Report_type;
	}
	public void setReport_type(String report_type) {
		Report_type = report_type;
	}
	public String getTemp_Measurment() {
		return Temp_Measurment;
	}
	public void setTemp_Measurment(String temp_Measurment) {
		Temp_Measurment = temp_Measurment;
	}
	public String getInternal_Temperature() {
		return Internal_Temperature;
	}
	public void setInternal_Temperature(String internal_Temperature) {
		Internal_Temperature = internal_Temperature;
	}
	public String getBAT() {
		return BAT;
	}
	public void setBAT(String bAT) {
		BAT = bAT;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getAltitude() {
		return Altitude;
	}
	public void setAltitude(String altitude) {
		Altitude = altitude;
	}
	@Override
	public String toString() {
		return "DeviceData [Device_Id=" + Device_Id + ", Sensor_id=" + Sensor_id + ", Message_Number=" + Message_Number
				+ ", UTC=" + UTC + ", Speed=" + Speed + ", Report_type=" + Report_type + ", Temp_Measurment="
				+ Temp_Measurment + ", Internal_Temperature=" + Internal_Temperature + ", BAT=" + BAT + ", Latitude="
				+ Latitude + ", Longitude=" + Longitude + ", Distance=" + Distance + ", Altitude=" + Altitude + "]";
	}

	
	
	
}
