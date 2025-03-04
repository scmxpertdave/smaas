package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

public class UpdateShipmentEvent {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Number;
	private String TypeOfReference;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ShipmentDescription;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String EventId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventType;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerFrom;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventReferenceNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventDescription;
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
	
	
}
