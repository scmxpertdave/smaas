package com.SCMXPert.sbmongodb.document;

public class UpdateEventMassLoad {

	private String invoiceNumber;
	private String deliveryNumber;
	private String deviceId;
	private String partner;
	private String partnerFrom;
	private String eventName;
	private String eventStatus;
	
	private String secondDevice;
	private String thirdDevice;
	private String fourthDevice;
	private String fifthDevice;
	private String numberOfDevices;
		
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getPartnerFrom() {
		return partnerFrom;
	}
	public void setPartnerFrom(String partnerFrom) {
		this.partnerFrom = partnerFrom;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getSecondDevice() {
		return secondDevice;
	}
	public void setSecondDevice(String secondDevice) {
		this.secondDevice = secondDevice;
	}
	public String getThirdDevice() {
		return thirdDevice;
	}
	public void setThirdDevice(String thirdDevice) {
		this.thirdDevice = thirdDevice;
	}
	public String getFourthDevice() {
		return fourthDevice;
	}
	public void setFourthDevice(String fourthDevice) {
		this.fourthDevice = fourthDevice;
	}
	@Override
	public String toString() {
		return "UpdateEventMassLoad [invoiceNumber=" + invoiceNumber + ", deliveryNumber=" + deliveryNumber
				+ ", deviceId=" + deviceId + ", partner=" + partner + ", partnerFrom=" + partnerFrom + ", eventName="
				+ eventName + ", eventStatus=" + eventStatus + ", secondDevice=" + secondDevice + ", thirdDevice="
				+ thirdDevice + ", fourthDevice=" + fourthDevice + "]";
	}
	public String getFifthDevice() {
		return fifthDevice;
	}
	public void setFifthDevice(String fifthDevice) {
		this.fifthDevice = fifthDevice;
	}
	public String getNumberOfDevices() {
		return numberOfDevices;
	}
	public void setNumberOfDevices(String numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
	}
	
}
