package com.SCMXPert.sbmongodb.document;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "DeviceDataStream")
public class DeviceDataStream {

	public String getFirst_Sensor_temperature() {
		return First_Sensor_temperature;
	}

	public void setFirst_Sensor_temperature(String first_Sensor_temperature) {
		First_Sensor_temperature = first_Sensor_temperature;
	}

	@Id
	private ObjectId id;
	
	@Field(value ="Battery_Level")
	private String Battery_Level;
	
	@Field(value = "Device_Id")
	private String Device_Id;

	@Field(value = "locationType")
	private String locationType;

	@Field(value = "reportPeriod")
	private String reportPeriod;

	@Field(value = "maxHumidityThreshold")
	private String maxHumidityThreshold;

	@Field(value = "reporting_zone")
	private String reporting_zone;

	@Field(value = "iccid")
	private String iccid;

	@Field(value = "Modem_IMEI")
	private String Modem_IMEI;

	@Field(value = "shakeThreshold")
	private String shakeThreshold;

	@Field(value = "minTempThreshold")
	private String minTempThreshold;

	@Field(value = "sensorPhySampleCycle")
	private String sensorPhySampleCycle;

	@Field(value = "Current_Terminal_Zone")
	private String Current_Terminal_Zone;

	@Field(value = "Report_type")
	private String Report_type;

	@Field(value = "humdity_2")
	private String humdity_2;

	@Field(value = "mileage")
	private String mileage;

	@Field(value = "reporting_type")
	private String reporting_type;

	@Field(value = "airpressure")
	private String airpressure;

	@Field(value = "sensorReportCycle")
	private String sensorReportCycle;

	@Field(value = "humidity_1")
	private String humidity_1;

	@Field(value = "active")
	private String active;

	@Field(value = "device_slno")
	private String device_slno;

//	@Field(value = "MessageType")
//	private String MessageType;
	
	@Field(value = "Message_Type")
	private String Message_Type;
	
	public String getMessage_Type() {
		return Message_Type;
	}

	public void setMessage_Type(String message_Type) {
		Message_Type = message_Type;
	}

	@Field(value ="Address")
	private String Address;

	@Field(value = "Longitude")
	private String Longitude;

	@Field(value = "tiltAngle")
	private String tiltAngle;

	@Field(value = "Internal_temperature")
	private String Internal_temperature;

	@Field(value = "temp_2")
	private String temp_2;

	@Field(value = "stop")
	private String stop;

	@Field(value = "reporting_Time")
	private String reporting_time;

	@Field(value = "base_cycle")
	private String base_cycle;

	@Field(value = "drive")
	private String drive;

	@Field(value = "status")
	private String status;

	@Field(value = "minHumidityThreshold")
	private String minHumidityThreshold;

	@Field(value = "boxT")
	private String boxT;


	@Field(value = "cycle")
	private String cycle;

	@Field(value = "Speed_in_mph")
	private String Speed_in_mph;

	@Field(value = "reportDistance")
	private String reportDistance;

	@Field(value = "total_mileage")
	private String total_mileage;

	@Field(value = "Location_way")
	private String Location_way;

	@Field(value = "current_terminal_time")
	private String current_terminal_time;

	@Field(value = "boxRH")
	private String boxRH;

	@Field(value = "event")
	private String event;

	@Field(value = "value")
	private String value;

	@Field(value = "ip_port")
	private String ip_port;

	@Field(value = "lightSampleCycle")
	private String lightSampleCycle;

	@Field(value = "lightDlt")
	private String lightDlt;

	@Field(value = "lightadc")
	private String lightadc;

	@Field(value = "ip_address")
	private String ip_address;

	@Field(value = "maxTempThreshold")
	private String maxTempThreshold;

	@Field(value = "sensorSampleCycle")
	private String sensorSampleCycle;

	@Field(value = "Latitude")
	private String Latitude;

	@Field(value = "num_of_samples")
	private String num_of_samples;

	@Field(value = "device_oem")
	private String device_oem;

	@Field(value = "current_terminal_date")
	private String current_terminal_date;
	
	@Field(value ="Reporting_Date")
	private String Reporting_Date;
	
//	@Field(value ="Reporting_Date")
//private String reporting_date2;
//
//	public String getReporting_date2() {
//		return reporting_date2;
//	}
//
//	public void setReporting_date2(String reporting_date2) {
//		this.reporting_date2 = reporting_date2;
//	}

	@Field(value ="SensorUTC")
	private String SensorUTC;
	
	@Field(value ="First_Sensor_temperature")
	private String First_Sensor_temperature;

	public String getSensorUTC() {
		return SensorUTC;
	}

	public void setSensorUTC(String sensorUTC) {
		SensorUTC = sensorUTC;
	}

	public String getReporting_Date() {
		return Reporting_Date;
	}

	public void setReporting_Date(String reporting_Date) {
		Reporting_Date = reporting_Date;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	



	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getReportPeriod() {
		return reportPeriod;
	}

	public void setReportPeriod(String reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

	public String getMaxHumidityThreshold() {
		return maxHumidityThreshold;
	}

	public void setMaxHumidityThreshold(String maxHumidityThreshold) {
		this.maxHumidityThreshold = maxHumidityThreshold;
	}

	public String getReporting_zone() {
		return reporting_zone;
	}

	public void setReporting_zone(String reporting_zone) {
		this.reporting_zone = reporting_zone;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	

	public String getShakeThreshold() {
		return shakeThreshold;
	}

	public void setShakeThreshold(String shakeThreshold) {
		this.shakeThreshold = shakeThreshold;
	}

	public String getMinTempThreshold() {
		return minTempThreshold;
	}

	public void setMinTempThreshold(String minTempThreshold) {
		this.minTempThreshold = minTempThreshold;
	}

	public String getSensorPhySampleCycle() {
		return sensorPhySampleCycle;
	}

	public void setSensorPhySampleCycle(String sensorPhySampleCycle) {
		this.sensorPhySampleCycle = sensorPhySampleCycle;
	}

	public String getCurrent_Terminal_Zone() {
		return Current_Terminal_Zone;
	}

	public void setCurrent_Terminal_Zone(String current_Terminal_Zone) {
		Current_Terminal_Zone = current_Terminal_Zone;
	}

	

	public String getHumdity_2() {
		return humdity_2;
	}

	public void setHumdity_2(String humdity_2) {
		this.humdity_2 = humdity_2;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getReporting_type() {
		return reporting_type;
	}

	public void setReporting_type(String reporting_type) {
		this.reporting_type = reporting_type;
	}

	public String getAirpressure() {
		return airpressure;
	}

	public void setAirpressure(String airpressure) {
		this.airpressure = airpressure;
	}

	public String getSensorReportCycle() {
		return sensorReportCycle;
	}

	public void setSensorReportCycle(String sensorReportCycle) {
		this.sensorReportCycle = sensorReportCycle;
	}

	public String getHumidity_1() {
		return humidity_1;
	}

	public void setHumidity_1(String humidity_1) {
		this.humidity_1 = humidity_1;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDevice_slno() {
		return device_slno;
	}

	public void setDevice_slno(String device_slno) {
		this.device_slno = device_slno;
	}

	

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getTiltAngle() {
		return tiltAngle;
	}

	public void setTiltAngle(String tiltAngle) {
		this.tiltAngle = tiltAngle;
	}

	

	public String getInternal_temperature() {
		return Internal_temperature;
	}

	public void setInternal_temperature(String internal_temperature) {
		Internal_temperature = internal_temperature;
	}

	public String getTemp_2() {
		return temp_2;
	}

	public void setTemp_2(String temp_2) {
		this.temp_2 = temp_2;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getReporting_time() {
		return reporting_time;
	}

	public void setReporting_time(String reporting_time) {
		this.reporting_time = reporting_time;
	}

	public String getBase_cycle() {
		return base_cycle;
	}

	public void setBase_cycle(String base_cycle) {
		this.base_cycle = base_cycle;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMinHumidityThreshold() {
		return minHumidityThreshold;
	}

	public void setMinHumidityThreshold(String minHumidityThreshold) {
		this.minHumidityThreshold = minHumidityThreshold;
	}

	public String getBoxT() {
		return boxT;
	}

	public void setBoxT(String boxT) {
		this.boxT = boxT;
	}

	
	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getSpeed_in_mph() {
		return Speed_in_mph;
	}

	public void setSpeed_in_mph(String speed_in_mph) {
		Speed_in_mph = speed_in_mph;
	}

	public String getReportDistance() {
		return reportDistance;
	}

	public void setReportDistance(String reportDistance) {
		this.reportDistance = reportDistance;
	}

	public String getTotal_mileage() {
		return total_mileage;
	}

	public void setTotal_mileage(String total_mileage) {
		this.total_mileage = total_mileage;
	}

	public String getLocation_way() {
		return Location_way;
	}

	public void setLocation_way(String location_way) {
		Location_way = location_way;
	}

	public String getCurrent_terminal_time() {
		return current_terminal_time;
	}

	public void setCurrent_terminal_time(String current_terminal_time) {
		this.current_terminal_time = current_terminal_time;
	}

	public String getBoxRH() {
		return boxRH;
	}

	public void setBoxRH(String boxRH) {
		this.boxRH = boxRH;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIp_port() {
		return ip_port;
	}

	public void setIp_port(String ip_port) {
		this.ip_port = ip_port;
	}

	public String getLightSampleCycle() {
		return lightSampleCycle;
	}

	public void setLightSampleCycle(String lightSampleCycle) {
		this.lightSampleCycle = lightSampleCycle;
	}

	public String getLightDlt() {
		return lightDlt;
	}

	public void setLightDlt(String lightDlt) {
		this.lightDlt = lightDlt;
	}

	public String getLightadc() {
		return lightadc;
	}

	public void setLightadc(String lightadc) {
		this.lightadc = lightadc;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getMaxTempThreshold() {
		return maxTempThreshold;
	}

	public void setMaxTempThreshold(String maxTempThreshold) {
		this.maxTempThreshold = maxTempThreshold;
	}

	public String getSensorSampleCycle() {
		return sensorSampleCycle;
	}

	public void setSensorSampleCycle(String sensorSampleCycle) {
		this.sensorSampleCycle = sensorSampleCycle;
	}

	
	public String getNum_of_samples() {
		return num_of_samples;
	}

	public void setNum_of_samples(String num_of_samples) {
		this.num_of_samples = num_of_samples;
	}

	public String getDevice_oem() {
		return device_oem;
	}

	public void setDevice_oem(String device_oem) {
		this.device_oem = device_oem;
	}

	public String getCurrent_terminal_date() {
		return current_terminal_date;
	}

	public void setCurrent_terminal_date(String current_terminal_date) {
		this.current_terminal_date = current_terminal_date;
	}
	
	
	public String getDevice_Id() {
		return Device_Id;
	}

	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}

	

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getModem_IMEI() {
		return Modem_IMEI;
	}

	public void setModem_IMEI(String modem_IMEI) {
		Modem_IMEI = modem_IMEI;
	}

	public String getReport_type() {
		return Report_type;
	}

	public void setReport_type(String report_type) {
		Report_type = report_type;
	}

	

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	public String getBattery_Level() {
		return Battery_Level;
	}

	public void setBattery_Level(String battery_Level) {
		Battery_Level = battery_Level;
	}

	@Override
	public String toString() {
		return "DeviceDataStream [id=" + id + ", Battery_Level=" + Battery_Level + ", Device_Id=" + Device_Id
				+ ", locationType=" + locationType + ", reportPeriod=" + reportPeriod + ", maxHumidityThreshold="
				+ maxHumidityThreshold + ", reporting_zone=" + reporting_zone + ", iccid=" + iccid + ", Modem_IMEI="
				+ Modem_IMEI + ", shakeThreshold=" + shakeThreshold + ", minTempThreshold=" + minTempThreshold
				+ ", sensorPhySampleCycle=" + sensorPhySampleCycle + ", Current_Terminal_Zone=" + Current_Terminal_Zone
				+ ", Report_type=" + Report_type + ", humdity_2=" + humdity_2 + ", mileage=" + mileage
				+ ", reporting_type=" + reporting_type + ", airpressure=" + airpressure + ", sensorReportCycle="
				+ sensorReportCycle + ", humidity_1=" + humidity_1 + ", active=" + active + ", device_slno="
				+ device_slno + ", Message_Type=" + Message_Type + ", Address=" + Address + ", Longitude=" + Longitude
				+ ", tiltAngle=" + tiltAngle + ", Internal_temperature=" + Internal_temperature + ", temp_2=" + temp_2
				+ ", stop=" + stop + ", reporting_time=" + reporting_time + ", base_cycle=" + base_cycle + ", drive="
				+ drive + ", status=" + status + ", minHumidityThreshold=" + minHumidityThreshold + ", boxT=" + boxT
				+ ", cycle=" + cycle + ", Speed_in_mph=" + Speed_in_mph + ", reportDistance=" + reportDistance
				+ ", total_mileage=" + total_mileage + ", Location_way=" + Location_way + ", current_terminal_time="
				+ current_terminal_time + ", boxRH=" + boxRH + ", event=" + event + ", value=" + value + ", ip_port="
				+ ip_port + ", lightSampleCycle=" + lightSampleCycle + ", lightDlt=" + lightDlt + ", lightadc="
				+ lightadc + ", ip_address=" + ip_address + ", maxTempThreshold=" + maxTempThreshold
				+ ", sensorSampleCycle=" + sensorSampleCycle + ", Latitude=" + Latitude + ", num_of_samples="
				+ num_of_samples + ", device_oem=" + device_oem + ", current_terminal_date=" + current_terminal_date
				+ ", Reporting_Date=" + Reporting_Date + ", SensorUTC=" + SensorUTC + ", First_Sensor_temperature="
				+ First_Sensor_temperature + ", getFirst_Sensor_temperature()=" + getFirst_Sensor_temperature()
				+ ", getMessage_Type()=" + getMessage_Type() + ", getSensorUTC()=" + getSensorUTC()
				+ ", getReporting_Date()=" + getReporting_Date() + ", getId()=" + getId() + ", getLocationType()="
				+ getLocationType() + ", getReportPeriod()=" + getReportPeriod() + ", getMaxHumidityThreshold()="
				+ getMaxHumidityThreshold() + ", getReporting_zone()=" + getReporting_zone() + ", getIccid()="
				+ getIccid() + ", getShakeThreshold()=" + getShakeThreshold() + ", getMinTempThreshold()="
				+ getMinTempThreshold() + ", getSensorPhySampleCycle()=" + getSensorPhySampleCycle()
				+ ", getCurrent_Terminal_Zone()=" + getCurrent_Terminal_Zone() + ", getHumdity_2()=" + getHumdity_2()
				+ ", getMileage()=" + getMileage() + ", getReporting_type()=" + getReporting_type()
				+ ", getAirpressure()=" + getAirpressure() + ", getSensorReportCycle()=" + getSensorReportCycle()
				+ ", getHumidity_1()=" + getHumidity_1() + ", getActive()=" + getActive() + ", getDevice_slno()="
				+ getDevice_slno() + ", getLongitude()=" + getLongitude() + ", getTiltAngle()=" + getTiltAngle()
				+ ", getInternal_temperature()=" + getInternal_temperature() + ", getTemp_2()=" + getTemp_2()
				+ ", getStop()=" + getStop() + ", getReporting_time()=" + getReporting_time() + ", getBase_cycle()="
				+ getBase_cycle() + ", getDrive()=" + getDrive() + ", getStatus()=" + getStatus()
				+ ", getMinHumidityThreshold()=" + getMinHumidityThreshold() + ", getBoxT()=" + getBoxT()
				+ ", getCycle()=" + getCycle() + ", getSpeed_in_mph()=" + getSpeed_in_mph() + ", getReportDistance()="
				+ getReportDistance() + ", getTotal_mileage()=" + getTotal_mileage() + ", getLocation_way()="
				+ getLocation_way() + ", getCurrent_terminal_time()=" + getCurrent_terminal_time() + ", getBoxRH()="
				+ getBoxRH() + ", getEvent()=" + getEvent() + ", getValue()=" + getValue() + ", getIp_port()="
				+ getIp_port() + ", getLightSampleCycle()=" + getLightSampleCycle() + ", getLightDlt()=" + getLightDlt()
				+ ", getLightadc()=" + getLightadc() + ", getIp_address()=" + getIp_address()
				+ ", getMaxTempThreshold()=" + getMaxTempThreshold() + ", getSensorSampleCycle()="
				+ getSensorSampleCycle() + ", getNum_of_samples()=" + getNum_of_samples() + ", getDevice_oem()="
				+ getDevice_oem() + ", getCurrent_terminal_date()=" + getCurrent_terminal_date() + ", getDevice_Id()="
				+ getDevice_Id() + ", getLatitude()=" + getLatitude() + ", getModem_IMEI()=" + getModem_IMEI()
				+ ", getReport_type()=" + getReport_type() + ", getAddress()=" + getAddress() + ", getBattery_Level()="
				+ getBattery_Level() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


}

//
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
// 
//@Document(collection = "DeviceDataStream")
//public class DeviceDataStream {
//
//    @Id
//    private ObjectId id;
//    
//    @Field(value = "Device_Id")
//    private String Device_Id;
//    
//    @Field(value = "Speed")
//    private String Speed;
//    
//    @Field(value = "Sensor_id")
//    private String Sensor_id;
//    
//    @Field(value = "Temp_Measurment")
//    private String Temp_Measurment;
//    
//    @Field(value = "UTC")
//    private String UTC;
//    
//    @Field(value = "Report_Status_data")
//    private String Report_Status_data;
//    
//    @Field(value = "Time")
//    private String Time;
//    
//    @Field(value = "Latitude")
//    private String Latitude;
//    
//    @Field(value = "No_of_tags")
//    private String No_of_tags;
//    
//    @Field(value = "Longitude")
//    private String Longitude;
//    
//    @Field(value = "Date")
//    private String Date;
//    
//    @Field(value = "Message_Number")
//    private String Message_Number;
//    
//    @Field(value = "Report_type")
//    private String Report_type;
//    
//    @Field(value = "Signal")
//    private String Signal;
//    
//    @Field(value = "Internal_temperature")
//    private String Internal_temperature;
//    
//    @Field(value = "Modem_Registration_status")
//    private String Modem_Registration_status;
//    
//    @Field(value = "BAT")
//    private String BAT;
//    
//    
//    @Field(value = "Modem_IMEI")
//    private String Modem_IMEI;
//    
//    @Field(value = "Course")
//    private String Course;
//    
//    @Field(value = "Tag_Life_Time")
//    private String Tag_Life_Time;
//    
//    @Field(value = "MessageType")
//    private String MessageType;
//    
//    @Field(value = "Distance")
//    private String Distance;
//    
//    @Field(value = "Altitude")
//    private String Altitude;
//    
//    @Field(value = "Extended_power_report")
//    private String Extended_power_report;
//
//	public String getDevice_Id() {
//		return Device_Id;
//	}
//
//	public void setDevice_Id(String device_Id) {
//		Device_Id = device_Id;
//	}
//
//	public String getSpeed() {
//		return Speed;
//	}
//
//	public void setSpeed(String speed) {
//		Speed = speed;
//	}
//
//	public String getSensor_id() {
//		return Sensor_id;
//	}
//
//	public void setSensor_id(String sensor_id) {
//		Sensor_id = sensor_id;
//	}
//
//	public String getTemp_Measurment() {
//		return Temp_Measurment;
//	}
//
//	public void setTemp_Measurment(String temp_Measurment) {
//		Temp_Measurment = temp_Measurment;
//	}
//
//	public String getUTC() {
//		return UTC;
//	}
//
//	public void setUTC(String uTC) {
//		UTC = uTC;
//	}
//
//	public String getReport_Status_data() {
//		return Report_Status_data;
//	}
//
//	public void setReport_Status_data(String report_Status_data) {
//		Report_Status_data = report_Status_data;
//	}
//
//	public String getTime() {
//		return Time;
//	}
//
//	public void setTime(String time) {
//		Time = time;
//	}
//
//	public String getLatitude() {
//		return Latitude;
//	}
//
//	public void setLatitude(String latitude) {
//		Latitude = latitude;
//	}
//
//	public String getNo_of_tags() {
//		return No_of_tags;
//	}
//
//	public void setNo_of_tags(String no_of_tags) {
//		No_of_tags = no_of_tags;
//	}
//
//	public String getLongitude() {
//		return Longitude;
//	}
//
//	public void setLongitude(String longitude) {
//		Longitude = longitude;
//	}
//
//	public String getDate() {
//		return Date;
//	}
//
//	public void setDate(String date) {
//		Date = date;
//	}
//
//	public String getMessage_Number() {
//		return Message_Number;
//	}
//
//	public void setMessage_Number(String message_Number) {
//		Message_Number = message_Number;
//	}
//
//	public String getReport_type() {
//		return Report_type;
//	}
//
//	public void setReport_type(String report_type) {
//		Report_type = report_type;
//	}
//
//	public String getSignal() {
//		return Signal;
//	}
//
//	public void setSignal(String signal) {
//		Signal = signal;
//	}
//
//	public String getInternal_temperature() {
//		return Internal_temperature;
//	}
//
//	public void setInternal_temperature(String internal_temperature) {
//		Internal_temperature = internal_temperature;
//	}
//
//	public String getModem_Registration_status() {
//		return Modem_Registration_status;
//	}
//
//	public void setModem_Registration_status(String modem_Registration_status) {
//		Modem_Registration_status = modem_Registration_status;
//	}
//
//	public String getBAT() {
//		return BAT;
//	}
//
//	public void setBAT(String bAT) {
//		BAT = bAT;
//	}
//
//	public String getModem_IMEI() {
//		return Modem_IMEI;
//	}
//
//	public void setModem_IMEI(String modem_IMEI) {
//		Modem_IMEI = modem_IMEI;
//	}
//
//	public String getCourse() {
//		return Course;
//	}
//
//	public void setCourse(String course) {
//		Course = course;
//	}
//
//	public String getTag_Life_Time() {
//		return Tag_Life_Time;
//	}
//
//	public void setTag_Life_Time(String tag_Life_Time) {
//		Tag_Life_Time = tag_Life_Time;
//	}
//
//	public String getMessageType() {
//		return MessageType;
//	}
//
//	public void setMessageType(String messageType) {
//		MessageType = messageType;
//	}
//
//	public String getDistance() {
//		return Distance;
//	}
//
//	public void setDistance(String distance) {
//		Distance = distance;
//	}
//
//	public String getAltitude() {
//		return Altitude;
//	}
//
//	public void setAltitude(String altitude) {
//		Altitude = altitude;
//	}
//
//	public String getExtended_power_report() {
//		return Extended_power_report;
//	}
//
//	public void setExtended_power_report(String extended_power_report) {
//		Extended_power_report = extended_power_report;
//	}
//
//	@Override
//	public String toString() {
//		return "DeviceDataStream [Device_Id=" + Device_Id + ", Speed=" + Speed + ", Sensor_id=" + Sensor_id
//				+ ", Temp_Measurment=" + Temp_Measurment + ", UTC=" + UTC + ", Report_Status_data=" + Report_Status_data
//				+ ", Time=" + Time + ", Latitude=" + Latitude + ", No_of_tags=" + No_of_tags + ", Longitude="
//				+ Longitude + ", Date=" + Date + ", Message_Number=" + Message_Number + ", Report_type=" + Report_type
//				+ ", Signal=" + Signal + ", Internal_temperature=" + Internal_temperature
//				+ ", Modem_Registration_status=" + Modem_Registration_status + ", BAT=" + BAT + ", Modem_IMEI="
//				+ Modem_IMEI + ", Course=" + Course + ", Tag_Life_Time=" + Tag_Life_Time + ", MessageType="
//				+ MessageType + ", Distance=" + Distance + ", Altitude=" + Altitude + ", Extended_power_report="
//				+ Extended_power_report + ", getDevice_Id()=" + getDevice_Id() + ", getSpeed()=" + getSpeed()
//				+ ", getSensor_id()=" + getSensor_id() + ", getTemp_Measurment()=" + getTemp_Measurment()
//				+ ", getUTC()=" + getUTC() + ", getReport_Status_data()=" + getReport_Status_data() + ", getTime()="
//				+ getTime() + ", getLatitude()=" + getLatitude() + ", getNo_of_tags()=" + getNo_of_tags()
//				+ ", getLongitude()=" + getLongitude() + ", getDate()=" + getDate() + ", getMessage_Number()="
//				+ getMessage_Number() + ", getReport_type()=" + getReport_type() + ", getSignal()=" + getSignal()
//				+ ", getInternal_temperature()=" + getInternal_temperature() + ", getModem_Registration_status()="
//				+ getModem_Registration_status() + ", getBAT()=" + getBAT() + ", getModem_IMEI()=" + getModem_IMEI()
//				+ ", getCourse()=" + getCourse() + ", getTag_Life_Time()=" + getTag_Life_Time() + ", getMessageType()="
//				+ getMessageType() + ", getDistance()=" + getDistance() + ", getAltitude()=" + getAltitude()
//				+ ", getExtended_power_report()=" + getExtended_power_report() + ", getClass()=" + getClass()
//				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
//	}
//    
//    
//
//
//    
//
//    
//}
//
//
///*
//
//@Indexed(unique = true)
//@Field(value = "Sensor_id")
//private String sensorid;
//public String getSensorid() {
//	return sensorid;
//}
//
//
//
//@Field(value = "Speed")
//private String Speed;
//public String getSpeed() {
//	return Speed;
//}
//
//@Field(value = "Temp_Measurment")
//private String Temp_Measurment;
//public String getTempMeasurment() {
//	return Temp_Measurment;
//}
//@Field(value = "UTC")
//private String UTC;
//public String getUTC() {
//	return UTC;
//}
//@Field(value = "Report_Status_data")
//private String Report_Status_data;
//public String getReportStatusdata() {
//	return Report_Status_data;
//}
//@Field(value = "Time")
//private String Time;
//public String getTime() {
//	return Time;
//}
//@Field(value = "Latitude")
//private String Latitude;
//public String getLatitude() {
//	return Latitude;
//}
//@Field(value = "No_of_tags")
//private String No_of_tags;
//public String getNooftags() {
//	return No_of_tags;
//}
//@Field(value = "Message_Number")
//private String Message_Number;
//public String getMessageNumber() {
//	return Message_Number;
//}
//@Field(value = "Longitude")
//private String Longitude;
//public String getLongitude() {
//	return Longitude;
//}
//@Field(value = "Date")
//private String Date;
//public String getDate() {
//	return Date;
//}
//@Field(value = "Report_type")
//private String Report_type;
//public String getReporttype() {
//	return Report_type;
//}
//@Field(value = "Signal")
//private String Signal;
//public String getSignal() {
//	return Signal;
//}
//@Field(value = "Internal_temperature")
//private String Internal_temperature;
//public String getInternaltemperature() {
//	return Internal_temperature;
//}
//@Field(value = "Modem_Registration_status")
//private String Modem_Registration_status;
//public String getModemRegistrationstatus() {
//	return Modem_Registration_status;
//}
//@Field(value = "BAT")
//private String BAT;
//public String getBAT() {
//	return BAT;
//}
//@Field(value = "Modem_IMEI")
//private String Modem_IMEI;
//public String getModemIMEI() {
//	return Modem_IMEI;
//}
//@Field(value = "Course")
//private String Course;
//public String getCourse() {
//	return Course;
//}
//@Field(value = "Tag_Life_Time")
//private String Tag_Life_Time;
//public String getTag_LifeTime() {
//	return Tag_Life_Time;
//}
//@Field(value = "MessageType")
//private String MessageType;
//public String getMessageType() {
//	return MessageType;
//}
//@Field(value = "Distance")
//private String Distance;
//public String getDistance() {
//	return Distance;
//}
//@Field(value = "Altitude")
//private String Altitude;
//public String getAltitude() {
//	return Altitude;
//}
//@Field(value = "Extended_power_report")
//private String Extended_power_report;
//public String getExtendedpowerreport() {
//	return Extended_power_report;
//}
//
//@Override
//public String toString() {
//	return "id:" + this._id + ", Speed: " + this.Speed+", Sensor_id: " + this.sensorid + ", Temp_Measurment: " + this.Temp_Measurment + ", UTC: "
//			+ this.UTC+  ", Report_Status_data: " + this.Report_Status_data +  ", Time: " + this.Time  +
//			", Latitude: " + this.Latitude +  ", No_of_tags: " + this.No_of_tags+", Message_Number: " + this.Message_Number+
//			", Longitude: " + this.Longitude+", Date: " + this.Date +", Report_type: " + this.Report_type+ ", Signal: "+this.Signal +", Internal_temperature: " + this.Internal_temperature 
//			+", Modem_Registration_status: " + this.Modem_Registration_status+", BAT: " + this.BAT+", Modem_IMEI: " + this.Modem_IMEI+", Course: " + this.Course
//			+", Tag_Life_Time: " + this.Tag_Life_Time+", Report_type: " + this.Report_type+", MessageType: " + this.MessageType+", Distance: " + this.Distance
//			+", Altitude: " + this.Altitude+", Extended_power_report: " + this.Extended_power_report;
//}*/