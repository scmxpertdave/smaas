package com.SCMXPert.sbmongodb.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.validation.constraints.Pattern;
import javax.validation.constraints.Pattern;
@Document(collection = "DeviceTransferTracker")
public class DeviceTransferTracker {

	@Id
	private ObjectId id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String InternalTransferId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustomerId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Device_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String NumberOfDevices;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String TrackingNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CourrierCompany;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String TransferDescription;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
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

	public ObjectId getId() {
		return id;
	}

	public void setNumberOfDevices(String numberOfDevices) {
		NumberOfDevices = numberOfDevices;
	}

	public String getNumberOfDevices() {
		return NumberOfDevices;
	}

	public void setDeviceStatus(String deviceStatus) {
		DeviceStatus = deviceStatus;
	}

	public String getDeviceStatus() {
		return DeviceStatus;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public String getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(String partnerId) {
		PartnerId = partnerId;
	}

	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}

	public String getDevice_Id() {
		return Device_Id;
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

}
