package com.SCMXPert.sbmongodb.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import jakarta.validation.constraints.Pattern;
import javax.validation.constraints.Pattern;

public class DeviceTransferTrackerDto {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String InternalTransferId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustomerId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String DeviceId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String NumberOfDevices;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String TrackingNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CourrierCompany;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String TransferDescription;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String FromOrigin;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String ToDestination;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String ReceivingPartner;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String DestinationAddress;
	private String DateOfTransfer;
	private String ExpectedDate;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String PersonReceiving;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String DeviceStatus;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String BpId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String GoodsType;
	private String Time;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Battery;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Location;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Longitude;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Latitude;

	public void setBpId(String bpId) {
		BpId = bpId;
	}

	public String getBpId() {
		return BpId;
	}

	public void setNumberOfDevices(String numberOfDevices) {
		NumberOfDevices = numberOfDevices;
	}

	public String getNumberOfDevices() {
		return NumberOfDevices;
	}

	public String getGoodsType() {
		return GoodsType;
	}

	public void setGoodsType(String goodsType) {
		GoodsType = goodsType;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getBattery() {
		return Battery;
	}

	public void setBattery(String battery) {
		Battery = battery;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getInternalTransferId() {
		return InternalTransferId;
	}

	public void setInternalTransferId(String internalTransferId) {
		InternalTransferId = internalTransferId;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getTrackingNumber() {
		return TrackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}

	public String getCourrierCompany() {
		return CourrierCompany;
	}

	public void setCourrierCompany(String courrierCompany) {
		CourrierCompany = courrierCompany;
	}

	public String getTransferDescription() {
		return TransferDescription;
	}

	public void setTransferDescription(String transferDescription) {
		TransferDescription = transferDescription;
	}

	public String getFromOrigin() {
		return FromOrigin;
	}

	public void setFromOrigin(String fromOrigin) {
		FromOrigin = fromOrigin;
	}

	public String getToDestination() {
		return ToDestination;
	}

	public void setToDestination(String toDestination) {
		ToDestination = toDestination;
	}

	public String getReceivingPartner() {
		return ReceivingPartner;
	}

	public void setReceivingPartner(String receivingPartner) {
		ReceivingPartner = receivingPartner;
	}

	public String getDestinationAddress() {
		return DestinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		DestinationAddress = destinationAddress;
	}

	public String getDateOfTransfer() {
		return DateOfTransfer;
	}

	public void setDateOfTransfer(String dateOfTransfer) {
		DateOfTransfer = dateOfTransfer;
	}

	public String getExpectedDate() {
		return ExpectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		ExpectedDate = expectedDate;
	}

	public String getPersonReceiving() {
		return PersonReceiving;
	}

	public void setPersonReceiving(String personReceiving) {
		PersonReceiving = personReceiving;
	}

	public String getDeviceStatus() {
		return DeviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		DeviceStatus = deviceStatus;
	}

}
