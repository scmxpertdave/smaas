package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

public class UpdateNewPlusEventDto {

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String CustomerId; // shipment and shipmentTxns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String Shipment_Number; // shipment and and shipmentTxns
	private String[] Comments; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String DeviceId; // Shpment txns
	private String EstimatedDeliveryDate; // Shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String ParnterFrom; // Shipment Txns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String PartnerTo; // Shipment Txns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String EventName; // Shipment Txns
	private String DateAndTime; // Shipment Txns Event executionTime
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String Shipment_Num;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _,]+")
	private String Mode;
	private String Partner;
	
	private String[] EvidenceList;
	private String[] Evidence_URL;
	private String[] Evidence_Description;

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getShipment_Number() {
		return Shipment_Number;
	}

	public void setShipment_Number(String shipment_Number) {
		Shipment_Number = shipment_Number;
	}

	public String[] getComments() {
		return Comments;
	}

	public void setComments(String[] comments) {
		Comments = comments;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getEstimatedDeliveryDate() {
		return EstimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		EstimatedDeliveryDate = estimatedDeliveryDate;
	}

	public String getParnterFrom() {
		return ParnterFrom;
	}

	public void setParnterFrom(String parnterFrom) {
		ParnterFrom = parnterFrom;
	}

	public String getPartnerTo() {
		return PartnerTo;
	}

	public void setPartnerTo(String partnerTo) {
		PartnerTo = partnerTo;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getDateAndTime() {
		return DateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		DateAndTime = dateAndTime;
	}

	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
	}

	public String getEvent_Id() {
		return Event_Id;
	}

	public String getShipment_Num() {
		return Shipment_Num;
	}

	public void setShipment_Num(String shipment_Num) {
		Shipment_Num = shipment_Num;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getPartner() {
		return Partner;
	}

	public void setPartner(String partner) {
		Partner = partner;
	}

	public String[] getEvidenceList() {
		return EvidenceList;
	}

	public void setEvidenceList(String[] evidenceList) {
		EvidenceList = evidenceList;
	}

	public String[] getEvidence_URL() {
		return Evidence_URL;
	}

	public void setEvidence_URL(String[] evidence_URL) {
		Evidence_URL = evidence_URL;
	}

	public String[] getEvidence_Description() {
		return Evidence_Description;
	}

	public void setEvidence_Description(String[] evidence_Description) {
		Evidence_Description = evidence_Description;
	}

}
