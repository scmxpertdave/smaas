package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.validation.constraints.Pattern;

@Document(collection = "ShipmentSavedDrafts")
public class ShipmentDraftDto {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Internal_Shipment_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Customer_Id; // shipment and shipmentTxns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Number; // shipment and and shipmentTxns
	private String TypeOfReference; // shipment
	private String Comments; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String RouteId; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String RouteFrom; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String RouteTo; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String GoodsId; // shipment
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?) -]*$")
	private String GoodsType; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String DeviceId; // Shpment txns
	private String EstimatedDeliveryDate; // Shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ParnterFrom; // Shipment Txns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerTo; // Shipment Txns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventName; // Shipment Txns
	private String DateAndTime; // Shipment Txns Event executionTime
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Mode;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Inco;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String RouteInfo;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String batch_Id;
	
	
	

	public String getBatch_Id() {
		return batch_Id;
	}

	public void setBatch_Id(String batch_Id) {
		this.batch_Id = batch_Id;
	}

	@Override
	public String toString() {
		return "ShipmentDraftDto [Internal_Shipment_Id=" + Internal_Shipment_Id + ", Customer_Id=" + Customer_Id
				+ ", Shipment_Number=" + Shipment_Number + ", TypeOfReference=" + TypeOfReference + ", Comments="
				+ Comments + ", RouteId=" + RouteId + ", RouteFrom=" + RouteFrom + ", RouteTo=" + RouteTo + ", GoodsId="
				+ GoodsId + ", GoodsType=" + GoodsType + ", DeviceId=" + DeviceId + ", EstimatedDeliveryDate="
				+ EstimatedDeliveryDate + ", ParnterFrom=" + ParnterFrom + ", PartnerTo=" + PartnerTo + ", EventName="
				+ EventName + ", DateAndTime=" + DateAndTime + ", Mode=" + Mode + ", Inco=" + Inco + ", RouteInfo="
				+ RouteInfo + ", SelectEventId=" + SelectEventId + ", Po_Number=" + Po_Number + ", Ndc_Number="
				+ Ndc_Number + ", Invoice_Number=" + Invoice_Number + ", Shipper_Number=" + Shipper_Number
				+ ", Serial_Number_of_goods=" + Serial_Number_of_goods + ", getPo_Number()=" + getPo_Number()
				+ ", getNdc_Number()=" + getNdc_Number() + ", getInvoice_Number()=" + getInvoice_Number()
				+ ", getShipper_Number()=" + getShipper_Number() + ", getSerial_Number_of_goods()="
				+ getSerial_Number_of_goods() + ", getSelectEventId()=" + getSelectEventId()
				+ ", getInternal_Shipment_Id()=" + getInternal_Shipment_Id() + ", getCustomer_Id()=" + getCustomer_Id()
				+ ", getShipment_Number()=" + getShipment_Number() + ", getTypeOfReference()=" + getTypeOfReference()
				+ ", getComments()=" + getComments() + ", getRouteId()=" + getRouteId() + ", getRouteFrom()="
				+ getRouteFrom() + ", getRouteTo()=" + getRouteTo() + ", getGoodsId()=" + getGoodsId()
				+ ", getGoodsType()=" + getGoodsType() + ", getDeviceId()=" + getDeviceId()
				+ ", getEstimatedDeliveryDate()=" + getEstimatedDeliveryDate() + ", getParnterFrom()="
				+ getParnterFrom() + ", getPartnerTo()=" + getPartnerTo() + ", getEventName()=" + getEventName()
				+ ", getDateAndTime()=" + getDateAndTime() + ", getInco()=" + getInco() + ", getMode()=" + getMode()
				+ ", getRouteInfo()=" + getRouteInfo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String SelectEventId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Po_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Ndc_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Invoice_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipper_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Serial_Number_of_goods;

	// private DropDown1 DropDown;

	public String getPo_Number() {
		return Po_Number;
	}

	public void setPo_Number(String po_Number) {
		Po_Number = po_Number;
	}

	public String getNdc_Number() {
		return Ndc_Number;
	}

	public void setNdc_Number(String ndc_Number) {
		Ndc_Number = ndc_Number;
	}

	public String getInvoice_Number() {
		return Invoice_Number;
	}

	public void setInvoice_Number(String invoice_Number) {
		Invoice_Number = invoice_Number;
	}

	public String getShipper_Number() {
		return Shipper_Number;
	}

	public void setShipper_Number(String shipper_Number) {
		Shipper_Number = shipper_Number;
	}

	public String getSerial_Number_of_goods() {
		return Serial_Number_of_goods;
	}

	public void setSerial_Number_of_goods(String serial_Number_of_goods) {
		Serial_Number_of_goods = serial_Number_of_goods;
	}

	public String getSelectEventId() {
		return SelectEventId;
	}

	public void setSelectEventId(String selectEventId) {
		SelectEventId = selectEventId;
	}

	public void setInternal_Shipment_Id(String internal_Shipment_Id) {
		Internal_Shipment_Id = internal_Shipment_Id;
	}

	public String getInternal_Shipment_Id() {
		return Internal_Shipment_Id;
	}

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customerId) {
		Customer_Id = customerId;
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

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getRouteId() {
		return RouteId;
	}

	public void setRouteId(String routeId) {
		RouteId = routeId;
	}

	public String getRouteFrom() {
		return RouteFrom;
	}

	public void setRouteFrom(String routeFrom) {
		RouteFrom = routeFrom;
	}

	public String getRouteTo() {
		return RouteTo;
	}

	public void setRouteTo(String routeTo) {
		RouteTo = routeTo;
	}

	public String getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}

	public String getGoodsType() {
		return GoodsType;
	}

	public void setGoodsType(String goodsType) {
		GoodsType = goodsType;
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

	public void setInco(String inco) {
		Inco = inco;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getInco() {
		return Inco;
	}

	public String getMode() {
		return Mode;
	}
	/*
	 * public DropDown1 getDropDown() { return DropDown; } public void
	 * setDropDown(DropDown1 dropDown) { DropDown = dropDown; }
	 */

	public String getRouteInfo() {
		return RouteInfo;
	}

	public void setRouteInfo(String routeInfo) {
		RouteInfo = routeInfo;
	}

}
