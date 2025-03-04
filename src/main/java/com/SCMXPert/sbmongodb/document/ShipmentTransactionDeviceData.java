package com.SCMXPert.sbmongodb.document;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class ShipmentTransactionDeviceData {
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Shipment_Id;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Shipment_Num;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Event_Name;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private long Event_SNo;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String batch_Id;

private String[] Comments;
private String material_number;


public String getBatch_Id() {
	return batch_Id;
}
public void setBatch_Id(String batch_Id) {
	this.batch_Id = batch_Id;
}
public long getEvent_SNo() {
	return Event_SNo;
}
public void setEvent_SNo(long event_SNo) {
	Event_SNo = event_SNo;
}
private String Mode_of_Transport;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Device_Id;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Altitude;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String BatteryLevel;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Distance;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Internal_temperature;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Latitude;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Longitude;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Message_Number;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Report_type;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Sensor_id;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Speed;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Temp_Measurment;
private String UTC;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Partner_From;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Partner_To;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
private String Event_Status;

private String Address;
private String Event_Exec_Date;
private String Po_Number;
private String PoItmNumber;	
private String Ndc_Number;
private String Invoice_Number;
private String Shipper_Number;
private String Serial_Number_of_goods;
private String Cmo_Ref_Number;

private String Estimated_Delivery_Date;
private String Route_Details;
private String Goods_Type;

private String fromPlant;

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
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getPartner_From() {
	return Partner_From;
}
public void setPartner_From(String partner_From) {
	Partner_From = partner_From;
}
public String getPartner_To() {
	return Partner_To;
}
public void setPartner_To(String partner_To) {
	Partner_To = partner_To;
}
public String getShipment_Id() {
return Shipment_Id;
}
public void setShipment_Id(String shipment_Id) {
Shipment_Id = shipment_Id;
}
public String getShipment_Num() {
return Shipment_Num;
}
public void setShipment_Num(String shipment_Num) {
Shipment_Num = shipment_Num;
}
public String getEvent_Name() {
return Event_Name;
}
public void setEvent_Name(String event_Name) {
Event_Name = event_Name;
}
public String getMode_of_Transport() {
return Mode_of_Transport;
}
public void setMode_of_Transport(String mode_of_Transport) {
Mode_of_Transport = mode_of_Transport;
}
public String getDevice_Id() {
return Device_Id;
}
public void setDevice_Id(String device_Id) {
Device_Id = device_Id;
}
public String getAltitude() {
return Altitude;
}
public void setAltitude(String altitude) {
Altitude = altitude;
}

public String getBatteryLevel() {
	return BatteryLevel;
}
public void setBatteryLevel(String batteryLevel) {
	BatteryLevel = batteryLevel;
}
public String getDistance() {
return Distance;
}
public void setDistance(String distance) {
Distance = distance;
}
public String getInternal_temperature() {
return Internal_temperature;
}
public void setInternal_temperature(String internal_temperature) {
Internal_temperature = internal_temperature;
}
public String getLatitude() {
return Latitude;
}
public void setLatitude(String latitude) {
Latitude = latitude;
}
public String getLongitude() {
return Longitude;
}
public void setLongitude(String longitude) {
Longitude = longitude;
}
public String getMessage_Number() {
return Message_Number;
}
public void setMessage_Number(String message_Number) {
Message_Number = message_Number;
}
public String getReport_type() {
return Report_type;
}
public void setReport_type(String report_type) {
Report_type = report_type;
}
public String getSensor_id() {
return Sensor_id;
}
public void setSensor_id(String sensor_id) {
Sensor_id = sensor_id;
}
public String getSpeed() {
return Speed;
}
public void setSpeed(String speed) {
Speed = speed;
}
public String getTemp_Measurment() {
return Temp_Measurment;
}
public void setTemp_Measurment(String temp_Measurment) {
Temp_Measurment = temp_Measurment;
}
public String getUTC() {
return UTC;
}
public void setUTC(String uTC) {
UTC = uTC;
}
public String getEvent_Status() {
	return Event_Status;
}
public void setEvent_Status(String event_Status) {
	Event_Status = event_Status;
}
public String getEvent_Exec_Date() {
	return Event_Exec_Date;
}
public void setEvent_Exec_Date(String event_Exec_Date) {
	Event_Exec_Date = event_Exec_Date;
}
public String getCmo_Ref_Number() {
	return Cmo_Ref_Number;
}
public void setCmo_Ref_Number(String cmo_Ref_Number) {
	Cmo_Ref_Number = cmo_Ref_Number;
}
public String getEstimated_Delivery_Date() {
	return Estimated_Delivery_Date;
}
public void setEstimated_Delivery_Date(String estimated_Delivery_Date) {
	Estimated_Delivery_Date = estimated_Delivery_Date;
}
public String getRoute_Details() {
	return Route_Details;
}
public void setRoute_Details(String route_Details) {
	Route_Details = route_Details;
}
public String getGoods_Type() {
	return Goods_Type;
}
public void setGoods_Type(String goods_Type) {
	Goods_Type = goods_Type;
}
public String[] getComments() {
	return Comments;
}
public void setComments(String[] comments) {
	Comments = comments;
}
public String getMaterial_number() {
	return material_number;
}
public void setMaterial_number(String material_number) {
	this.material_number = material_number;
}
public String getPoItmNumber() {
	return PoItmNumber;
}
public void setPoItmNumber(String poItmNumber) {
	PoItmNumber = poItmNumber;
}
public String getFromPlant() {
	return fromPlant;
}
public void setFromPlant(String fromPlant) {
	this.fromPlant = fromPlant;
}

 

}

 