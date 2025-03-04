package com.SCMXPert.sbmongodb.document;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Abid
 **/

public class Calculations {
	
	private String customerName ;
	private String partnerName;
	private String shipmentNo ;
	private String shipmentDesc;
	private String deviceNo ;
	private String routeDetails;
//	private String maxTempThres;
//	private String minTempThres;
	private String Temperature_Max;
	private String Temperature_Min;
	
//	private Float highestTemp;
//	private Float lowestTemp;
	private String highestTemp;
	private String lowestTemp;
//	private Double avgTemp;
//	private Double mktValue;
	private String avgTemp;
	private String mktValue;
	private String batterystart;
	
	private String startTime;
	private String endTime;
	private String elapsedTime;
	private String dataPoints;
	private String batteryend;
	
	private String Shipment_Id;
//	private String Invoice_Number;
	///shipper number is container number
//	private String Shipper_Number;
	private String Route_Id;
	private String Goods_Id;
//	private String Goods_Desc;
	private String Goods_Type;
//	private String Estimated_Delivery_Date;
	private String Expected_Delivery_Date;
	private String Po_Number;
	private String PoItmNumber;
	private String Ndc_Number;
	private String batch_Id;
	private String Serial_Number_of_goods;
//	private String Created_Date;
	private String Delivery_Number;
	private String Container_Number;
	private String Cmo_Ref_Number;
	private String number_of_Excursion_and_Incursion;
	private String time_out_of_threshold;
    private String date;
    
    private String Storage_Condition;
    private String temperature24Max;
    private String temperature24Min;
    
    private String mandt; 
    private String mandt_number; 
    private String docComplete; 
    private String zException; 
    private String material_number;
    private String fda;
    
    private String TempUnitsofmeasure;

//    private Devicedatatemp cals;
    private List<Devicedatatemp> devDataList;

	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

//	public String getMaxTempThres() {
//		return maxTempThres;
//	}
//
//	public void setMaxTempThres(String maxTempThres) {
//		this.maxTempThres = maxTempThres;
//	}

//	public String getMinTempThres() {
//		return minTempThres;
//	}
//
//	public void setMinTempThres(String minTempThres) {
//		this.minTempThres = minTempThres;
//	}

//	public Float getHighestTemp() {
//		return highestTemp;
//	}
//
//	public void setHighestTemp(Float highestTemp) {
//		this.highestTemp = highestTemp;
//	}

//	public Float getLowestTemp() {
//		return lowestTemp;
//	}
//
//	public void setLowestTemp(Float lowestTemp) {
//		this.lowestTemp = lowestTemp;
//	}

//	public Double getAvgTemp() {
//		return avgTemp;
//	}
//
//	public void setAvgTemp(Double avgTemp) {
//		this.avgTemp = avgTemp;
//	}

//	public Double getMktValue() {
//		return mktValue;
//	}
//
//	public void setMktValue(Double mktValue) {
//		this.mktValue = mktValue;
//	}

	public String getBatterystart() {
		return batterystart;
	}

	public void setBatterystart(String batterystart) {
		this.batterystart = batterystart;
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

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(String dataPoints) {
		this.dataPoints = dataPoints;
	}

	public String getBatteryend() {
		return batteryend;
	}

	public void setBatteryend(String batteryend) {
		this.batteryend = batteryend;
	}

	public String getShipment_Id() {
		return Shipment_Id;
	}

	public void setShipment_Id(String shipment_Id) {
		Shipment_Id = shipment_Id;
	}

//	public String getInvoice_Number() {
//		return Invoice_Number;
//	}
//
//	public void setInvoice_Number(String invoice_Number) {
//		Invoice_Number = invoice_Number;
//	}

//	public String getShipper_Number() {
//		return Shipper_Number;
//	}
//
//	public void setShipper_Number(String shipper_Number) {
//		Shipper_Number = shipper_Number;
//	}

	public String getRoute_Id() {
		return Route_Id;
	}

	public void setRoute_Id(String route_Id) {
		Route_Id = route_Id;
	}

	public String getGoods_Id() {
		return Goods_Id;
	}

	public void setGoods_Id(String goods_Id) {
		Goods_Id = goods_Id;
	}

//	public String getGoods_Desc() {
//		return Goods_Desc;
//	}
//
//	public void setGoods_Desc(String goods_Desc) {
//		Goods_Desc = goods_Desc;
//	}

//	public String getEstimated_Delivery_Date() {
//		return Estimated_Delivery_Date;
//	}
//
//	public void setEstimated_Delivery_Date(String estimated_Delivery_Date) {
//		Estimated_Delivery_Date = estimated_Delivery_Date;
//	}

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

	public String getBatch_Id() {
		return batch_Id;
	}

	public void setBatch_Id(String batch_Id) {
		this.batch_Id = batch_Id;
	}

	public String getSerial_Number_of_goods() {
		return Serial_Number_of_goods;
	}

	public void setSerial_Number_of_goods(String serial_Number_of_goods) {
		Serial_Number_of_goods = serial_Number_of_goods;
	}

//	public String getCreated_Date() {
//		return Created_Date;
//	}
//
//	public void setCreated_Date(String created_Date) {
//		Created_Date = created_Date;
//	}

	public String getDelivery_Number() {
		return Delivery_Number;
	}

	public void setDelivery_Number(String delivery_Number) {
		Delivery_Number = delivery_Number;
	}

	public String getCmo_Ref_Number() {
		return Cmo_Ref_Number;
	}

	public void setCmo_Ref_Number(String cmo_Ref_Number) {
		Cmo_Ref_Number = cmo_Ref_Number;
	}

	public String getContainer_Number() {
		return Container_Number;
	}

	public void setContainer_Number(String container_Number) {
		Container_Number = container_Number;
	}

	public String getGoods_Type() {
		return Goods_Type;
	}

	public void setGoods_Type(String goods_Type) {
		Goods_Type = goods_Type;
	}

	public String getExpected_Delivery_Date() {
		return Expected_Delivery_Date;
	}

	public void setExpected_Delivery_Date(String expected_Delivery_Date) {
		Expected_Delivery_Date = expected_Delivery_Date;
	}

	public String getTemperature_Max() {
		return Temperature_Max;
	}

	public void setTemperature_Max(String temperature_Max) {
		Temperature_Max = temperature_Max;
	}

	public String getTemperature_Min() {
		return Temperature_Min;
	}

	public void setTemperature_Min(String temperature_Min) {
		Temperature_Min = temperature_Min;
	}


	public String getNumber_of_Excursion_and_Incursion() {
		return number_of_Excursion_and_Incursion;
	}

	public void setNumber_of_Excursion_and_Incursion(String number_of_Excursion_and_Incursion) {
		this.number_of_Excursion_and_Incursion = number_of_Excursion_and_Incursion;
	}

	public String getTime_out_of_threshold() {
		return time_out_of_threshold;
	}

	public void setTime_out_of_threshold(String time_out_of_threshold) {
		this.time_out_of_threshold = time_out_of_threshold;
	}

	public String getHighestTemp() {
		return highestTemp;
	}

	public void setHighestTemp(String highestTemp) {
		this.highestTemp = highestTemp;
	}

	public String getLowestTemp() {
		return lowestTemp;
	}

	public void setLowestTemp(String lowestTemp) {
		this.lowestTemp = lowestTemp;
	}

	public String getAvgTemp() {
		return avgTemp;
	}

	public void setAvgTemp(String avgTemp) {
		this.avgTemp = avgTemp;
	}

	public String getMktValue() {
		return mktValue;
	}

	public void setMktValue(String mktValue) {
		this.mktValue = mktValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public List<Devicedatatemp> getDevDataList() {
		return devDataList;
	}

	public void setDevDataList(List<Devicedatatemp> devDataList) {
		this.devDataList = devDataList;
	}

	public String getStorage_Condition() {
		return Storage_Condition;
	}

	public void setStorage_Condition(String storage_Condition) {
		Storage_Condition = storage_Condition;
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

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getMandt_number() {
		return mandt_number;
	}

	public void setMandt_number(String mandt_number) {
		this.mandt_number = mandt_number;
	}

	public String getDocComplete() {
		return docComplete;
	}

	public void setDocComplete(String docComplete) {
		this.docComplete = docComplete;
	}

	public String getzException() {
		return zException;
	}

	public void setzException(String zException) {
		this.zException = zException;
	}

	public String getMaterial_number() {
		return material_number;
	}

	public void setMaterial_number(String material_number) {
		this.material_number = material_number;
	}

	public String getTempUnitsofmeasure() {
		return TempUnitsofmeasure;
	}

	public void setTempUnitsofmeasure(String tempUnitsofmeasure) {
		TempUnitsofmeasure = tempUnitsofmeasure;
	}

	public String getPoItmNumber() {
		return PoItmNumber;
	}

	public void setPoItmNumber(String poItmNumber) {
		PoItmNumber = poItmNumber;
	}

	public String getFda() {
		return fda;
	}

	public void setFda(String fda) {
		this.fda = fda;
	}

//	public DateTimeFormatter getDate() {
//		return date;
//	}
//
//	public void setDate(DateTimeFormatter date) {
//		this.date = date;
//	}

}
