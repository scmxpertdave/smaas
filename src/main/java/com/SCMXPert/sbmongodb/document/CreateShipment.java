package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.validation.constraints.Pattern;

@Document(collection="CreateShipment")
public class CreateShipment {

	@Id
	private ObjectId id;

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Internal_Shipment_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustomerName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String[] Shipment;
	private String[] TempRange;
	private String[] Rhrange;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Mode;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Inco;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String TypeOfReference;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ShipmentDescription;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String RouteDetails;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Destination;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String GoodsType;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Device;
	private String ExpectedDelDate;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String AddTagInfo;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventType;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerFrom;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventReferenceNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventDescription;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ReceivingLocation;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ReceivingReferenceNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ReceivingDescription;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String DeviceReturnLocation;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getInternal_Shipment_Id() {
		return Internal_Shipment_Id;
	}
	public void setInternal_Shipment_Id(String internal_Shipment_Id) {
		Internal_Shipment_Id = internal_Shipment_Id;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getPartnerName() {
		return PartnerName;
	}
	public void setPartnerName(String partnerName) {
		PartnerName = partnerName;
	}
	public String[] getShipment() {
		return Shipment;
	}
	public void setShipment(String[] shipment) {
		Shipment = shipment;
	}
	public String[] getTempRange() {
		return TempRange;
	}
	public void setTempRange(String[] tempRange) {
		TempRange = tempRange;
	}
	public String[] getRhrange() {
		return Rhrange;
	}
	public void setRhrange(String[] rhrange) {
		Rhrange = rhrange;
	}
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}
	public String getInco() {
		return Inco;
	}
	public void setInco(String inco) {
		Inco = inco;
	}
	public String getShipment_Number() {
		return Shipment_Number;
	}
	public void setShipment_Number(String shipment_Number) {
		Shipment_Number = shipment_Number;
	}
	public String getTypeOfReference() {
		return TypeOfReference;
	}
	public void setTypeOfReference(String typeOfReference) {
		TypeOfReference = typeOfReference;
	}
	public String getShipmentDescription() {
		return ShipmentDescription;
	}
	public void setShipmentDescription(String shipmentDescription) {
		ShipmentDescription = shipmentDescription;
	}
	public String getRouteDetails() {
		return RouteDetails;
	}
	public void setRouteDetails(String routeDetails) {
		RouteDetails = routeDetails;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getGoodsType() {
		return GoodsType;
	}
	public void setGoodsType(String goodsType) {
		GoodsType = goodsType;
	}
	public String getDevice() {
		return Device;
	}
	public void setDevice(String device) {
		Device = device;
	}
	public String getExpectedDelDate() {
		return ExpectedDelDate;
	}
	public void setExpectedDelDate(String expectedDelDate) {
		ExpectedDelDate = expectedDelDate;
	}
	public String getAddTagInfo() {
		return AddTagInfo;
	}
	public void setAddTagInfo(String addTagInfo) {
		AddTagInfo = addTagInfo;
	}
	public String getEventId() {
		return EventId;
	}
	public void setEventId(String eventId) {
		EventId = eventId;
	}
	public String getEventType() {
		return EventType;
	}
	public void setEventType(String eventType) {
		EventType = eventType;
	}
	public String getPartnerFrom() {
		return PartnerFrom;
	}
	public void setPartnerFrom(String partnerFrom) {
		PartnerFrom = partnerFrom;
	}
	public String getEventReferenceNumber() {
		return EventReferenceNumber;
	}
	public void setEventReferenceNumber(String eventReferenceNumber) {
		EventReferenceNumber = eventReferenceNumber;
	}
	public String getEventDescription() {
		return EventDescription;
	}
	public void setEventDescription(String eventDescription) {
		EventDescription = eventDescription;
	}
	public String getReceivingLocation() {
		return ReceivingLocation;
	}
	public void setReceivingLocation(String receivingLocation) {
		ReceivingLocation = receivingLocation;
	}
	public String getReceivingReferenceNumber() {
		return ReceivingReferenceNumber;
	}
	public void setReceivingReferenceNumber(String receivingReferenceNumber) {
		ReceivingReferenceNumber = receivingReferenceNumber;
	}
	public String getReceivingDescription() {
		return ReceivingDescription;
	}
	public void setReceivingDescription(String receivingDescription) {
		ReceivingDescription = receivingDescription;
	}
	public String getDeviceReturnLocation() {
		return DeviceReturnLocation;
	}
	public void setDeviceReturnLocation(String deviceReturnLocation) {
		DeviceReturnLocation = deviceReturnLocation;
	}
	
	
	
}
