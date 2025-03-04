package com.SCMXPert.sbmongodb.document;

public class DeviceDataTempDto {

	private String Device_Id;
	private String[] Internal_temperature;
	private String[] Humidity;

	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}

	public String getDevice_Id() {
		return Device_Id;
	}

	public String[] getHumidity() {
		return Humidity;
	}

	public void setHumidity(String[] humidity) {
		Humidity = humidity;
	}

	public void setInternal_temperature(String[] internal_temperature) {
		Internal_temperature = internal_temperature;
	}

	public String[] getInternal_temperature() {
		return Internal_temperature;
	}

}
