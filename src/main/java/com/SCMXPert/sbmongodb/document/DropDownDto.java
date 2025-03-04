package com.SCMXPert.sbmongodb.document;

public class DropDownDto {

	private String Dp_Id;
	private String[] references;
	private String[] routeDetails;
	private String[] goodsType;
	private String[] device;
	private String[] expectedDelDate;
	private String[] eventType;
	private String[] partnerFrom;
	private String[] receivingLocation;
	private String[] deviceReturnLocation;

	public String getDp_Id() {
		return Dp_Id;
	}

	public void setDp_Id(String dp_Id) {
		Dp_Id = dp_Id;
	}

	public String[] getReferences() {
		return references;
	}

	public void setReferences(String[] references) {
		this.references = references;
	}

	public String[] getRouteDetails() {
		return routeDetails;
	}

	public void setRouteDetails(String[] routeDetails) {
		this.routeDetails = routeDetails;
	}

	public String[] getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String[] goodsType) {
		this.goodsType = goodsType;
	}

	public String[] getDevice() {
		return device;
	}

	public void setDevice(String[] device) {
		this.device = device;
	}

	public String[] getExpectedDelDate() {
		return expectedDelDate;
	}

	public void setExpectedDelDate(String[] expectedDelDate) {
		this.expectedDelDate = expectedDelDate;
	}

	public String[] getEventType() {
		return eventType;
	}

	public void setEventType(String[] eventType) {
		this.eventType = eventType;
	}

	public String[] getPartnerFrom() {
		return partnerFrom;
	}

	public void setPartnerFrom(String[] partnerFrom) {
		this.partnerFrom = partnerFrom;
	}

	public String[] getReceivingLocation() {
		return receivingLocation;
	}

	public void setReceivingLocation(String[] receivingLocation) {
		this.receivingLocation = receivingLocation;
	}

	public String[] getDeviceReturnLocation() {
		return deviceReturnLocation;
	}

	public void setDeviceReturnLocation(String[] deviceReturnLocation) {
		this.deviceReturnLocation = deviceReturnLocation;
	}

}
