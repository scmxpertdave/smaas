package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

public class UpdateEvent {
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
	private String PartnerFrom;
	@Override
	public String toString() {
		return "UpdateEvent [Shipment_Number=" + Shipment_Number + ", Partner=" + Partner + ", Event=" + Event
				+ ", DateandTime=" + DateandTime + ", EventId=" + EventId + ", EventType=" + EventType
				+ ", PartnerFrom=" + PartnerFrom + ", EventReferenceNumber=" + EventReferenceNumber
				+ ", TypeOfReference=" + Arrays.toString(TypeOfReference) + ", Event_Description="
				+ Arrays.toString(Event_Description) + ", Evidence_Description=" + Arrays.toString(Evidence_Description)
				+ ", EventStatus=" + EventStatus + ", Event_Exec_Date=" + Event_Exec_Date + ", Evidence=" + Evidence
				+ ", EvidenceURL=" + Arrays.toString(EvidenceURL) + ", Invoice_Number=" + Invoice_Number
				+ ", Evidence_For=" + Arrays.toString(Evidence_For) + ", Shipment_Num=" + Shipment_Num + ", Device_Id="
				+ Device_Id + ", Evidencelist=" + Arrays.toString(Evidencelist) + "]";
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventReferenceNumber;
	//private String TypeOfReference;	
	private String[] TypeOfReference;
	//private String Comments;
	private String[] Event_Description;
	private String[] Evidence_Description;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventStatus;
	private String Event_Exec_Date;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Evidence;
	
	private String[] EvidenceURL;
	private String Invoice_Number;
	private String[] Evidence_For;
	private String Shipment_Num;
	
	public String getDevice_Id() {
		return Device_Id;
	}
	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Device_Id;
	
	
	
	public String getEvent_Exec_Date() {
		return Event_Exec_Date;
	}
	public void setEvent_Exec_Date(String event_Exec_Date) {
		Event_Exec_Date = event_Exec_Date;
	}
	private String[] Evidencelist;
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
	public String getEventReferenceNumber() {
		return EventReferenceNumber;
	}
	public void setEventReferenceNumber(String eventReferenceNumber) {
		EventReferenceNumber = eventReferenceNumber;
	}
//	public String getTypeOfReference() {
//		return TypeOfReference;
//	}
//	public void setTypeOfReference(String typeOfReference) {
//		TypeOfReference = typeOfReference;
//	}
//	public String getComments() {
//		return Comments;
//	}
//	public void setComments(String comments) {
//		Comments = comments;
//	}
	public String getEventStatus() {
		return EventStatus;
	}
	public void setEventStatus(String eventStatus) {
		EventStatus = eventStatus;
	}
	public String[] getEvidencelist() {
		return Evidencelist;
	}
	public void setEvidencelist(String[] evidencelist) {
		Evidencelist = evidencelist;
	}
	public String getEvidence() {
		return Evidence;
	}
	public void setEvidence(String evidence) {
		Evidence = evidence;
	}
	public String[] getEvidenceURL() {
		return EvidenceURL;
	}
	public void setEvidenceURL(String[] evidenceURL) {
		EvidenceURL = evidenceURL;
	}
	public String getInvoice_Number() {
		return Invoice_Number;
	}
	public void setInvoice_Number(String invoice_Number) {
		Invoice_Number = invoice_Number;
	}
	public String[] getEvidence_For() {
		return Evidence_For;
	}
	public void setEvidence_For(String[] evidence_For) {
		Evidence_For = evidence_For;
	}
	public String getShipment_Num() {
		return Shipment_Num;
	}
	public void setShipment_Num(String shipment_Num) {
		Shipment_Num = shipment_Num;
	}
//	public String[] getComments() {
//		return Comments;
//	}
//	public void setComments(String[] comments) {
//		Comments = comments;
//	}
	public String[] getEvent_Description() {
		return Event_Description;
	}
	public void setEvent_Description(String[] event_Description) {
		Event_Description = event_Description;
	}
	public String[] getEvidence_Description() {
		return Evidence_Description;
	}
	public void setEvidence_Description(String[] evidence_Description) {
		Evidence_Description = evidence_Description;
	}
	public String[] getTypeOfReference() {
		return TypeOfReference;
	}
	public void setTypeOfReference(String[] typeOfReference) {
		TypeOfReference = typeOfReference;
	}
	
	
	
	
}
