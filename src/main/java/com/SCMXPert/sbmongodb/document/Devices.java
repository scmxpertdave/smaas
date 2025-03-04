package com.SCMXPert.sbmongodb.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Devices")
public class Devices {

	
	
	@Id
	private ObjectId id;

	@Field(value = "Device_Id")
	private String deviceId;

	@Field(value = "IMEI_Number")
	private String imeiNumber;

	@Field(value = "BP_Id")
	private String bpId;

	@Field(value = "Scan_Code")
	private String scanCode;

	@Field(value = "OEM")
	private String oem;

	@Field(value = "Device_Type")
	private String deviceType;

	@Field(value = "Carrier")
	private String carrier;

	@Field(value = "Protocol")
	private String protocol;

	@Field(value = "Active")
	private String active;
	private String para1;
	private String para2;

	private String DeviceStatusReferred;
	private String Device_Location;
	private String Customer_Id;

	// Getters and Setters

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}

	public String getDevice_Location() {
		return Device_Location;
	}

	public void setDevice_Location(String device_Location) {
		Device_Location = device_Location;
	}

	public void setDeviceStatusReferred(String deviceStatusReferred) {
		DeviceStatusReferred = deviceStatusReferred;
	}

	public String getDeviceStatusReferred() {
		return DeviceStatusReferred;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getImeiNumber() {
		return imeiNumber;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public String getBpId() {
		return bpId;
	}

	public void setBpId(String bpId) {
		this.bpId = bpId;
	}

	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPara1() {
		return para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	// toString()
	@Override
	public String toString() {
		return "Devices [deviceId=" + deviceId + ", imeiNumber=" + imeiNumber + ", bpId=" + bpId + ", scanCode="
				+ scanCode + ", oem=" + oem + ", deviceType=" + deviceType + ", carrier=" + carrier + ", protocol="
				+ protocol + ", active=" + active + ", para1=" + para1 + ", para2=" + para2 + "]";
	}

}

/*
 * @Id private String _id;
 * 
 * @Indexed(unique = true)
 * 
 * @Field(value = "Device_Id") private String deviceId; public String
 * getdeviceId() { return deviceId; }
 * 
 * @Field(value = "IMEI_Number") private String IMEI_Number; public String
 * getIMEI_Number() { return IMEI_Number; }
 * 
 * @Field(value = "BP_Id") private String BP_Id; public String getBP_Id() {
 * return BP_Id; }
 * 
 * @Field(value = "Scan_Code") private String Scan_Code; public String
 * getScan_Code() { return Scan_Code; }
 * 
 * @Field(value = "OEM") private String OEM; public String getDOEM() { return
 * OEM; }
 * 
 * @Field(value = "Device_Type") private String Device_Type; public String
 * getDevice_Type() { return Device_Type; }
 * 
 * @Field(value = "Carrier") private String Carrier; public String getCarrier()
 * { return Carrier; } @Field(value = "Protocol") private String Protocol;
 * public String getProtocol() { return Protocol; } @Field(value = "Active")
 * private String Active; public String getActive() { return Active;
 * } @Field(value = "para1") private String para1; public String getpara1() {
 * return para1; } @Field(value = "para2") private String para2; public String
 * getpara2() { return para2; }
 * 
 * @Override public String toString() { return "id:" + this._id +
 * ", Device_Id: " + this.deviceId+", IMEI_Number: " + this.IMEI_Number +
 * ", BP_Id: " + this.BP_Id + ", Scan_Code: " + this.Scan_Code+ ", OEM: " +
 * this.OEM + ", Protocol: " + this.Protocol + ", Carrier: " + this.Carrier +
 * ", Protocol: " + this.Protocol+", Active: " + this.Active+ ", para1: " +
 * this.para1 + ", para2: " + this.para2; +", Report_type: " + this.Report_type+
 * ", Signal: "+this.Signal +", Internal_temperature: " +
 * this.Internal_temperature +", Modem_Registration_status: " +
 * this.Modem_Registration_status+", BAT: " + this.BAT+", Modem_IMEI: " +
 * this.Modem_IMEI+", Course: " + this.Course +", Tag_Life_Time: " +
 * this.Tag_Life_Time+", Report_type: " + this.Report_type+", MessageType: " +
 * this.MessageType+", Distance: " + this.Distance +", Altitude: " +
 * this.Altitude+", Extended_power_report: " + this.Extended_power_report;
 * 
 * }
 */