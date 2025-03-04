package com.SCMXPert.sbmongodb.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CompleteShipmentTrigger")
public class CompleteShipmentTrigger {
		
	@Id
	private ObjectId id;
	
	private String shipmentId;
	
	private String deliveryNumber;
	
	private String completionTriggerType;	// "Manual" or "944"
	
	private String shipmentCompletionDate; // Date of "Manual" or "944" trigger for Shipment Completion 
	
	private String timeZone;	//Basically UTC 
	
	private String status;	//By default it is stored as Blank	
	
	private String processedDateTime;	// TimeStamp of Actual Completion of shipment
	
	private String deviceId;
	
	private String graphImage;
	
	private String partnerFrom;
	
	private String partner;
	
	private String invoiceNumber;
	
	private String dependencyStatus;
		
	public String getPartner() {
		return partner;
	}


	public void setPartner(String partner) {
		this.partner = partner;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public String getPartnerFrom() {
		return partnerFrom;
	}


	public void setPartnerFrom(String partnerFrom) {
		this.partnerFrom = partnerFrom;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public String getProcessedDateTime() {
		return processedDateTime;
	}


	public void setProcessedDateTime(String processedDateTime) {
		this.processedDateTime = processedDateTime;
	}


	public String getDeliveryNumber() {
		return deliveryNumber;
	}


	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public String getCompletionTriggerType() {
		return completionTriggerType;
	}


	public void setCompletionTriggerType(String completionTriggerType) {
		this.completionTriggerType = completionTriggerType;
	}

	public String getGraphImage() {
		return graphImage;
	}


	public void setGraphImage(String graphImage) {
		this.graphImage = graphImage;
	}


	public String getShipmentCompletionDate() {
		return shipmentCompletionDate;
	}


	public void setShipmentCompletionDate(String shipmentCompletionDate) {
		this.shipmentCompletionDate = shipmentCompletionDate;
	}


	public String getTimeZone() {
		return timeZone;
	}


	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public String getShipmentId() {
		return shipmentId;
	}


	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}


//	@Override
//	public String toString() {
//		return "CompleteShipmentTrigger [id=" + id + ", shipmentId=" + shipmentId + ", deliveryNumber=" + deliveryNumber
//				+ ", completionTriggerType=" + completionTriggerType + ", shipmentCompletionDate="
//				+ shipmentCompletionDate + ", timeZone=" + timeZone + ", status=" + status + ", processedDateTime="
//				+ processedDateTime + ", deviceId=" + deviceId + ", graphImage=" + graphImage + ", partnerFrom="
//				+ partnerFrom + ", partner=" + partner + "]";
//	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	@Override
	public String toString() {
		return "CompleteShipmentTrigger [id=" + id + ", shipmentId=" + shipmentId + ", deliveryNumber=" + deliveryNumber
				+ ", completionTriggerType=" + completionTriggerType + ", shipmentCompletionDate="
				+ shipmentCompletionDate + ", timeZone=" + timeZone + ", status=" + status + ", processedDateTime="
				+ processedDateTime + ", deviceId=" + deviceId + ", graphImage=" + graphImage + ", partnerFrom="
				+ partnerFrom + ", partner=" + partner + ", invoiceNumber=" + invoiceNumber + ", dependencyStatus="
				+ dependencyStatus + "]";
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public String getDependencyStatus() {
		return dependencyStatus;
	}


	public void setDependencyStatus(String dependencyStatus) {
		this.dependencyStatus = dependencyStatus;
	}


	
}