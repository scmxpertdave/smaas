package com.SCMXPert.sbmongodb.document;

import java.util.List;
import java.util.Map;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class CompleteShipment {
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Number;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Partner;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event;
	
	private String DateandTime;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventId;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventType;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String conenctedDevice;
	
	private String Evidence;
	private String[] EvidenceList;
		
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String shipmentNum;
	
	private String graphImage;
	
	private List<Map<String, String>> graphUrl;
	
	private String invoiceNumber;
	
	public String getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}

	public String getGraphImage() {
		return graphImage;
	}

	public void setGraphImage(String graphImage) {
		this.graphImage = graphImage;
	}

	
	public String getConenctedDevice() {
		return conenctedDevice;
	}

	public void setConenctedDevice(String conenctedDevice) {
		this.conenctedDevice = conenctedDevice;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerFrom;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ReceivingLocation; // PartnerTo
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ReceivingReferenceNumber; // EventRefNumber
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String TypeOfReference;
//	private String Comments;
//	private String[] Comments;
//	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private List<String> Comments;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String DeviceReturnLocation;

	public String getShipment_Number() {
		return Shipment_Number;
	}

	public void setShipment_Number(String shipment_Number) {
		Shipment_Number = shipment_Number;
	}

	public String getPartner() {
		return Partner;
	}

	public void setPartner(String partner) {
		Partner = partner;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getDateandTime() {
		return DateandTime;
	}

	public void setDateandTime(String dateandTime) {
		DateandTime = dateandTime;
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

	public String getTypeOfReference() {
		return TypeOfReference;
	}

	public void setTypeOfReference(String typeOfReference) {
		TypeOfReference = typeOfReference;
	}

//	public String getComments() {
//		return Comments;
//	}
//
//	public void setComments(String comments) {
//		Comments = comments;
//	}

	public void setDeviceReturnLocation(String deviceReturnLocation) {
		DeviceReturnLocation = deviceReturnLocation;
	}

	public String getDeviceReturnLocation() {
		return DeviceReturnLocation;
	}

	public void setReceivingLocation(String receivingLocation) {
		ReceivingLocation = receivingLocation;
	}

	public String getReceivingLocation() {
		return ReceivingLocation;
	}

	public void setReceivingReferenceNumber(String receivingReferenceNumber) {
		ReceivingReferenceNumber = receivingReferenceNumber;
	}

	public String getReceivingReferenceNumber() {
		return ReceivingReferenceNumber;
	}

	public String getEvidence() {
		return Evidence;
	}

	public void setEvidence(String evidence) {
		Evidence = evidence;
	}

	public String[] getEvidenceList() {
		return EvidenceList;
	}

	public void setEvidenceList(String[] evidenceList) {
		EvidenceList = evidenceList;
	}

	public List<String> getComments() {
		return Comments;
	}

	public void setComments(List<String> comments) {
		Comments = comments;
	}

	public List<Map<String, String>> getGraphUrl() {
		return graphUrl;
	}

	public void setGraphUrl(List<Map<String, String>> graphUrl) {
		this.graphUrl = graphUrl;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

//	public String[] getComments() {
//		return Comments;
//	}
//
//	public void setComments(String[] comments) {
//		Comments = comments;
//	}

}
