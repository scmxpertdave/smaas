package com.SCMXPert.sbmongodb.document;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class ShipmentDraftPartialGet {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Internal_Shipment_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String CustomerName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String PartnerName;
	private String[] Shipment;
	private String[] TempRange;
	private String[] Rhrange;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Mode;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Inco;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Shipment_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String TypeOfReference;
	@Pattern(message="Invalid Input", regexp = "^[a-zA-Z+(a-zA-Z0-9-?)]*$")
	private String ShipmentDescription;
	@Pattern(message="Invalid Input", regexp = "^[a-zA-Z+(a-zA-Z0-9-?)]*$")
	private String RouteDetails;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Destination;
	@Pattern(message="Invalid Input", regexp = "^[a-zA-Z+(a-zA-Z0-9-?)]*$")
	private String GoodsType;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Device;
	private String ExpectedDelDate;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String AddTagInfo;

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

}
