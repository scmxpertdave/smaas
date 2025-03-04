

package com.SCMXPert.sbmongodb.document;

import java.util.Comparator;

import org.springframework.data.mongodb.core.mapping.Field;

public class Devicedatatemp {
	public String getUtcforAlarmzone() {
		return utcforAlarmzone;
	}
	public void setUtcforAlarmzone(String utcforAlarmzone) {
		this.utcforAlarmzone = utcforAlarmzone;
	}
	public String getSensorTemp() {
		return sensorTemp;
	}
	public void setSensorTemp(String sensorTemp) {
		this.sensorTemp = sensorTemp;
	}
	private String Internal_Temperature;
	public String getReportDateTime() {
		return reportDateTime;
	}
	public void setReportDateTime(String reportDateTime) {
		this.reportDateTime = reportDateTime;
	}
	private String Humidity;
	private String UTC;
	private String utcforAlarmzone;
	private String Address;
	private String reportDateTime;
	private String Message_Type;
	private String Latitude;
	private String Longitude;
	private String battery;
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
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
	public String getMessage_Type() {
		return Message_Type;
	}
	public void setMessage_Type(String message_Type) {
		Message_Type = message_Type;
	}
	public static Comparator<Devicedatatemp> getuTCComparator() {
		return uTCComparator;
	}
	public static void setuTCComparator(Comparator<Devicedatatemp> uTCComparator) {
		Devicedatatemp.uTCComparator = uTCComparator;
	}
	public String getMaxTempThresh() {
		return maxTempThresh;
	}
	public void setMaxTempThresh(String maxTempThresh) {
		this.maxTempThresh = maxTempThresh;
	}
	public String getMinTempThresh() {
		return minTempThresh;
	}
	public void setMinTempThresh(String minTempThresh) {
		this.minTempThresh = minTempThresh;
	}
	private String maxTempThresh;
	private String minTempThresh;
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Devicedatatemp(String internal_Temperature, String humidity, String uTC, String device_Id,
			String shipment_Num, String sensorTemp) {
		super();
		Internal_Temperature = internal_Temperature;
		Humidity = humidity;
		UTC = uTC;
		Device_Id = device_Id;
		Shipment_Num = shipment_Num;
		this.sensorTemp = sensorTemp;
	}
	public Devicedatatemp() {
		super();
	}
	private String Device_Id;
	private String Shipment_Num;
	@Field(value = "First_Sensor_temperature")
	private String sensorTemp;
	
	public String getInternal_Temperature() {
		return Internal_Temperature;
	}
	public void setInternal_Temperature(String internal_Temperature) {
		Internal_Temperature = internal_Temperature;
	}
	public String getHumidity() {
		return Humidity;
	}
	public void setHumidity(String humidity) {
		Humidity = humidity;
	}
	public String getUTC() {
		return UTC;
	}
	public void setUTC(String uTC) {
		UTC = uTC;
	}
	public String getDevice_Id() {
		return Device_Id;
	}
	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}
	public String getShipment_Num() {
		return Shipment_Num;
	}
	public void setShipment_Num(String shipment_Num) {
		Shipment_Num = shipment_Num;
	}
	public static Comparator<Devicedatatemp> uTCComparator = new Comparator<Devicedatatemp>() {

		public int compare(Devicedatatemp s1, Devicedatatemp s2) {
		   String utc1 = s1.getUTC().toUpperCase();
		   String utc2 = s2.getUTC().toUpperCase();

		   //ascending order
		   return utc1.compareTo(utc2);

		   //descending order
		   //return StudentName2.compareTo(StudentName1);
	    }};
}
