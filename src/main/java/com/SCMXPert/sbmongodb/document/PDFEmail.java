package com.SCMXPert.sbmongodb.document;



public class PDFEmail {
	private String customerName ;
	private String partnerName;
	private String shipmentNo ;
	private String invoiceNum;
	private String shipmentDesc;
	private String poNumer  ;
	private String deviceNo ;
	private String routeDetails  ;
	private Double mktValue  ;
	private String batterystart;
	private String batteryend;
	
	private String number_of_excurson_and_incursion;
	private String time_out_of_threshold;
	
	private String temperature24Max;
	private String temperature24Min;
	    
	private String storageCondition;
	
	public String getBatteryend() {
		return batteryend;
	}
	public void setBatteryend(String batteryend) {
		this.batteryend = batteryend;
	}
	public String getBatterystart() {
		return batterystart;
	}
	public void setBatterystart(String batterystart) {
		this.batterystart = batterystart;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getMktValue() {
		return mktValue;
	}
	public void setMktValue(Double mktValue) {
		this.mktValue = mktValue;
	}
	@Override
	public String toString() {
		return "PDFEmail [customerName=" + customerName + ", partnerName=" + partnerName + ", shipmentNo=" + shipmentNo
				+ ", shipmentDesc=" + shipmentDesc + ", poNumer=" + poNumer + ", deviceNo=" + deviceNo
				+ ", routeDetails=" + routeDetails + ", maxTempThres=" + maxTempThres + ", minTempThres=" + minTempThres
				+ ", highestTemp=" + highestTemp + ", lowestTemp=" + lowestTemp + ", avgTemp=" + avgTemp
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", getCustomerName()=" + getCustomerName()
				+ ", getPartnerName()=" + getPartnerName() + ", getShipmentNo()=" + getShipmentNo()
				+ ", getShipmentDesc()=" + getShipmentDesc() + ", getPoNumer()=" + getPoNumer() + ", getDeviceNo()="
				+ getDeviceNo() + ", getRouteDetails()=" + getRouteDetails() + ", getMaxTempThres()="
				+ getMaxTempThres() + ", getMinTempThres()=" + getMinTempThres() + ", getHighestTemp()="
				+ getHighestTemp() + ", getLowestTemp()=" + getLowestTemp() + ", getAvgTemp()=" + getAvgTemp()
				+ ", getStartTime()=" + getStartTime() + ", getEndTime()=" + getEndTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getShipmentNo() {
		return shipmentNo;
	}
	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}
	public String getShipmentDesc() {
		return shipmentDesc;
	}
	public void setShipmentDesc(String shipmentDesc) {
		this.shipmentDesc = shipmentDesc;
	}
	public String getPoNumer() {
		return poNumer;
	}
	public void setPoNumer(String poNumer) {
		this.poNumer = poNumer;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getRouteDetails() {
		return routeDetails;
	}
	public void setRouteDetails(String routeDetails) {
		this.routeDetails = routeDetails;
	}
	public String getMaxTempThres() {
		return maxTempThres;
	}
	public void setMaxTempThres(String maxTempThres) {
		this.maxTempThres = maxTempThres;
	}
	public String getMinTempThres() {
		return minTempThres;
	}
	public void setMinTempThres(String minTempThres) {
		this.minTempThres = minTempThres;
	}
	public Float getHighestTemp() {
		return highestTemp;
	}
	public void setHighestTemp(Float highestTemp) {
		this.highestTemp = highestTemp;
	}
	public Float getLowestTemp() {
		return lowestTemp;
	}
	public void setLowestTemp(Float lowestTemp) {
		this.lowestTemp = lowestTemp;
	}
	public Double getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(Double avgTemp) {
		this.avgTemp = avgTemp;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	private String maxTempThres;
	private String minTempThres;
	public String getDataPoints() {
		return dataPoints;
	}
	public void setDataPoints(String dataPoints) {
		this.dataPoints = dataPoints;
	}
	private Float highestTemp;
	private Float lowestTemp ;
	private Double avgTemp ;
	private String startTime;
	private String endTime;
	private String elapsedTime;
	private String dataPoints;
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getNumber_of_excurson_and_incursion() {
		return number_of_excurson_and_incursion;
	}
	public void setNumber_of_excurson_and_incursion(String number_of_excurson_and_incursion) {
		this.number_of_excurson_and_incursion = number_of_excurson_and_incursion;
	}
	public String getTime_out_of_threshold() {
		return time_out_of_threshold;
	}
	public void setTime_out_of_threshold(String time_out_of_threshold) {
		this.time_out_of_threshold = time_out_of_threshold;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public String getStorageCondition() {
		return storageCondition;
	}
	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}
	public String getTemperature24Max() {
		return temperature24Max;
	}
	public void setTemperature24Max(String temperature24Max) {
		this.temperature24Max = temperature24Max;
	}
	public String getTemperature24Min() {
		return temperature24Min;
	}
	public void setTemperature24Min(String temperature24Min) {
		this.temperature24Min = temperature24Min;
	}
}
