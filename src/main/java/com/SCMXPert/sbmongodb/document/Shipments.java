package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import jakarta.validation.constraints.Pattern;

@Document(collection = "Shipments")
public class Shipments {

	@Id
	private ObjectId id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String InternalShipmentId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Customer_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Num;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private float Event_Status;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9]+")
	private String batch_Id;

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Type_Of_Reference;
	
	private String Route_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Route_From;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Route_To;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Route_Desc;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Goods_Id;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?) ]*$")
	private String Goods_Desc;
	private String Estimated_Delivery_Date;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Created_By;
	private String Created_Date;
	private String Delivered_Date;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Delivered_Status;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Po_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PoItmNumber;	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Ndc_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Invoice_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipper_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Serial_Number_of_goods;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Cmo_Ref_Number;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustRouteId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustGoodId;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String material_number;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String plant;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String secondDevice;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String thirdDevice;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String fourthDevice;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String fifthDevice;

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String numberOfDevices;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String previousDelivery;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String previousInvoice;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String previousPlant;
		
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String shipmentModel;
	
	
	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getCmo_Ref_Number() {
		return Cmo_Ref_Number;
	}

	public void setCmo_Ref_Number(String cmo_Ref_Number) {
		Cmo_Ref_Number = cmo_Ref_Number;
	}

	public String getBatch_Id() {
		return batch_Id;
	}

	public void setBatch_Id(String batch_Id) {
		this.batch_Id = batch_Id;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	
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

	public String getDelivered_Status() {
		return Delivered_Status;
	}

	public void setDelivered_Status(String delivered_Status) {
		Delivered_Status = delivered_Status;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shipment_Status;
	private String[] Comments;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String DeviceReturnLocation;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Device_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String IncoTerms;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z]+")
	private String Mode;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Temp;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String RH;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Partner;
	private List<String[]> WayPoints;

	public List<String[]> getWayPoints() {
		return WayPoints;
	}

	public void setWayPoints(List<String[]> device) {
		WayPoints = device;
	}

	public String getShipment_Number() {
		return Shipment_Number;
	}

	public void setShipment_Number(String shipment_Number) {
		Shipment_Number = shipment_Number;
	}
	public String getIncoTerms() {
		return IncoTerms;
	}

	public void setIncoTerms(String incoTerms) {
		IncoTerms = incoTerms;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getTemp() {
		return Temp;
	}

	public void setTemp(String temp) {
		Temp = temp;
	}

	public String getRH() {
		return RH;
	}

	public void setRH(String rH) {
		RH = rH;
	}

	public void setInternalShipmentId(String internalShipmentId) {
		InternalShipmentId = internalShipmentId;
	}

	public String getInternalShipmentId() {
		return InternalShipmentId;
	}

	public void setDevice_Id(String device_Id) {
		Device_Id = device_Id;
	}

	public String getDevice_Id() {
		return Device_Id;
	}

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
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

	public String getType_Of_Reference() {
		return Type_Of_Reference;
	}

	public void setType_Of_Reference(String type_Of_Reference) {
		Type_Of_Reference = type_Of_Reference;
	}

	public String getRoute_Id() {
		return Route_Id;
	}

	public void setRoute_Id(String route_Id) {
		Route_Id = route_Id;
	}

	public String getRoute_From() {
		return Route_From;
	}

	public void setRoute_From(String route_From) {
		Route_From = route_From;
	}

	public String getRoute_To() {
		return Route_To;
	}

	public void setRoute_To(String route_To) {
		Route_To = route_To;
	}

	public String getRoute_Desc() {
		return Route_Desc;
	}

	public void setRoute_Desc(String route_Desc) {
		Route_Desc = route_Desc;
	}

	public String getGoods_Id() {
		return Goods_Id;
	}

	public void setGoods_Id(String goods_Id) {
		Goods_Id = goods_Id;
	}

	public String getGoods_Desc() {
		return Goods_Desc;
	}

	public void setGoods_Desc(String goods_Desc) {
		Goods_Desc = goods_Desc;
	}

	public String getEstimated_Delivery_Date() {
		return Estimated_Delivery_Date;
	}

	public void setEstimated_Delivery_Date(String estimated_Delivery_Date) {
		Estimated_Delivery_Date = estimated_Delivery_Date;
	}

	public String getCreated_By() {
		return Created_By;
	}

	public void setCreated_By(String created_By) {
		Created_By = created_By;
	}

	public String getCreated_Date() {
		return Created_Date;
	}

	public void setCreated_Date(String created_Date) {
		Created_Date = created_Date;
	}

	public String getDelivered_Date() {
		return Delivered_Date;
	}

	public void setDelivered_Date(String delivered_Date) {
		Delivered_Date = delivered_Date;
	}

	public String getShipment_Status() {
		return Shipment_Status;
	}

	public void setShipment_Status(String shipment_Status) {
		Shipment_Status = shipment_Status;
	}

	public String[] getComments() {
		return Comments;
	}

	public void setComments(String[] comments) {
		Comments = comments;
	}

	public void setDeviceReturnLocation(String deviceReturnLocation) {
		DeviceReturnLocation = deviceReturnLocation;
	}

	public String getDeviceReturnLocation() {
		return DeviceReturnLocation;
	}

	public String getPartner() {
		return Partner;
	}

	public void setPartner(String partner) {
		Partner = partner;
	}

	public float getEvent_Status() {
		return Event_Status;
	}

	public void setEvent_Status(float event_Status) {
		Event_Status = event_Status;
	}

//	@Override
//	public String toString() {
//		return "Shipments [id=" + id + ", InternalShipmentId=" + InternalShipmentId + ", Customer_Id=" + Customer_Id
//				+ ", Shipment_Id=" + Shipment_Id + ", Shipment_Num=" + Shipment_Num + ", Shipment_Number="
//				+ Shipment_Number + ", Event_Status=" + Event_Status + ", Type_Of_Reference=" + Type_Of_Reference
//				+ ", Route_Id=" + Route_Id + ", Route_From=" + Route_From + ", Route_To=" + Route_To + ", Route_Desc="
//				+ Route_Desc + ", Goods_Id=" + Goods_Id + ", Goods_Desc=" + Goods_Desc + ", Estimated_Delivery_Date="
//				+ Estimated_Delivery_Date + ", Created_By=" + Created_By + ", Created_Date=" + Created_Date
//				+ ", Delivered_Date=" + Delivered_Date + ", Delivered_Status=" + Delivered_Status + ", Po_Number="
//				+ Po_Number + ", Ndc_Number=" + Ndc_Number + ", Invoice_Number=" + Invoice_Number + ", Shipper_Number="
//				+ Shipper_Number + ", Serial_Number_of_goods=" + Serial_Number_of_goods + ", Shipment_Status="
//				+ Shipment_Status + ", Comments=" + Arrays.toString(Comments) + ", DeviceReturnLocation="
//				+ DeviceReturnLocation + ", Device_Id=" + Device_Id + ", IncoTerms=" + IncoTerms + ", Mode=" + Mode
//				+ ", Temp=" + Temp + ", RH=" + RH + ", Partner=" + Partner + ", WayPoints=" + WayPoints + ", getId()="
//				+ getId() + ", getPo_Number()=" + getPo_Number() + ", getNdc_Number()=" + getNdc_Number()
//				+ ", getInvoice_Number()=" + getInvoice_Number() + ", getShipper_Number()=" + getShipper_Number()
//				+ ", getSerial_Number_of_goods()=" + getSerial_Number_of_goods() + ", getDelivered_Status()="
//				+ getDelivered_Status() + ", getWayPoints()=" + getWayPoints() + ", getShipment_Number()="
//				+ getShipment_Number() + ", getIncoTerms()=" + getIncoTerms() + ", getMode()=" + getMode()
//				+ ", getTemp()=" + getTemp() + ", getRH()=" + getRH() + ", getInternalShipmentId()="
//				+ getInternalShipmentId() + ", getDevice_Id()=" + getDevice_Id() + ", getCustomer_Id()="
//				+ getCustomer_Id() + ", getShipment_Id()=" + getShipment_Id() + ", getShipment_Num()="
//				+ getShipment_Num() + ", getType_Of_Reference()=" + getType_Of_Reference() + ", getRoute_Id()="
//				+ getRoute_Id() + ", getRoute_From()=" + getRoute_From() + ", getRoute_To()=" + getRoute_To()
//				+ ", getRoute_Desc()=" + getRoute_Desc() + ", getGoods_Id()=" + getGoods_Id() + ", getGoods_Desc()="
//				+ getGoods_Desc() + ", getEstimated_Delivery_Date()=" + getEstimated_Delivery_Date()
//				+ ", getCreated_By()=" + getCreated_By() + ", getCreated_Date()=" + getCreated_Date()
//				+ ", getDelivered_Date()=" + getDelivered_Date() + ", getShipment_Status()=" + getShipment_Status()
//				+ ", getComments()=" + Arrays.toString(getComments()) + ", getDeviceReturnLocation()="
//				+ getDeviceReturnLocation() + ", getPartner()=" + getPartner() + ", getEvent_Status()="
//				+ getEvent_Status() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
//				+ super.toString() + "]";
//	}

	public String getCustRouteId() {
		return CustRouteId;
	}

	
	
///Below toString is commented when the addition of multiple devices has happened
	
//	@Override
//	public String toString() {
//		return "Shipments [id=" + id + ", InternalShipmentId=" + InternalShipmentId + ", Customer_Id=" + Customer_Id
//				+ ", Shipment_Id=" + Shipment_Id + ", Shipment_Num=" + Shipment_Num + ", Shipment_Number="
//				+ Shipment_Number + ", Event_Status=" + Event_Status + ", batch_Id=" + batch_Id + ", Type_Of_Reference="
//				+ Type_Of_Reference + ", Route_Id=" + Route_Id + ", Route_From=" + Route_From + ", Route_To=" + Route_To
//				+ ", Route_Desc=" + Route_Desc + ", Goods_Id=" + Goods_Id + ", Goods_Desc=" + Goods_Desc
//				+ ", Estimated_Delivery_Date=" + Estimated_Delivery_Date + ", Created_By=" + Created_By
//				+ ", Created_Date=" + Created_Date + ", Delivered_Date=" + Delivered_Date + ", Delivered_Status="
//				+ Delivered_Status + ", Po_Number=" + Po_Number + ", PoItmNumber=" + PoItmNumber + ", Ndc_Number="
//				+ Ndc_Number + ", Invoice_Number=" + Invoice_Number + ", Shipper_Number=" + Shipper_Number
//				+ ", Serial_Number_of_goods=" + Serial_Number_of_goods + ", Cmo_Ref_Number=" + Cmo_Ref_Number
//				+ ", CustRouteId=" + CustRouteId + ", CustGoodId=" + CustGoodId + ", material_number=" + material_number
//				+ ", plant=" + plant + ", Shipment_Status=" + Shipment_Status + ", Comments="
//				+ Arrays.toString(Comments) + ", DeviceReturnLocation=" + DeviceReturnLocation + ", Device_Id="
//				+ Device_Id + ", IncoTerms=" + IncoTerms + ", Mode=" + Mode + ", Temp=" + Temp + ", RH=" + RH
//				+ ", Partner=" + Partner + ", WayPoints=" + WayPoints + "]";
//	}

	
///Below toString is commented when the addition of multiple parameters has happened in swiss model
	
//	@Override
//	public String toString() {
//		return "Shipments [id=" + id + ", InternalShipmentId=" + InternalShipmentId + ", Customer_Id=" + Customer_Id
//				+ ", Shipment_Id=" + Shipment_Id + ", Shipment_Num=" + Shipment_Num + ", Shipment_Number="
//				+ Shipment_Number + ", Event_Status=" + Event_Status + ", batch_Id=" + batch_Id + ", Type_Of_Reference="
//				+ Type_Of_Reference + ", Route_Id=" + Route_Id + ", Route_From=" + Route_From + ", Route_To=" + Route_To
//				+ ", Route_Desc=" + Route_Desc + ", Goods_Id=" + Goods_Id + ", Goods_Desc=" + Goods_Desc
//				+ ", Estimated_Delivery_Date=" + Estimated_Delivery_Date + ", Created_By=" + Created_By
//				+ ", Created_Date=" + Created_Date + ", Delivered_Date=" + Delivered_Date + ", Delivered_Status="
//				+ Delivered_Status + ", Po_Number=" + Po_Number + ", PoItmNumber=" + PoItmNumber + ", Ndc_Number="
//				+ Ndc_Number + ", Invoice_Number=" + Invoice_Number + ", Shipper_Number=" + Shipper_Number
//				+ ", Serial_Number_of_goods=" + Serial_Number_of_goods + ", Cmo_Ref_Number=" + Cmo_Ref_Number
//				+ ", CustRouteId=" + CustRouteId + ", CustGoodId=" + CustGoodId + ", material_number=" + material_number
//				+ ", secondDevice=" + secondDevice + ", thirdDevice=" + thirdDevice + ", fourthDevice=" + fourthDevice
//				+ ", plant=" + plant + ", Shipment_Status=" + Shipment_Status + ", Comments="
//				+ Arrays.toString(Comments) + ", DeviceReturnLocation=" + DeviceReturnLocation + ", Device_Id="
//				+ Device_Id + ", IncoTerms=" + IncoTerms + ", Mode=" + Mode + ", Temp=" + Temp + ", RH=" + RH
//				+ ", Partner=" + Partner + ", WayPoints=" + WayPoints + "]";
//	}
	
	public void setCustRouteId(String custRouteId) {
		CustRouteId = custRouteId;
	}

	@Override
	public String toString() {
		return "Shipments [id=" + id + ", InternalShipmentId=" + InternalShipmentId + ", Customer_Id=" + Customer_Id
				+ ", Shipment_Id=" + Shipment_Id + ", Shipment_Num=" + Shipment_Num + ", Shipment_Number="
				+ Shipment_Number + ", Event_Status=" + Event_Status + ", batch_Id=" + batch_Id + ", Type_Of_Reference="
				+ Type_Of_Reference + ", Route_Id=" + Route_Id + ", Route_From=" + Route_From + ", Route_To=" + Route_To
				+ ", Route_Desc=" + Route_Desc + ", Goods_Id=" + Goods_Id + ", Goods_Desc=" + Goods_Desc
				+ ", Estimated_Delivery_Date=" + Estimated_Delivery_Date + ", Created_By=" + Created_By
				+ ", Created_Date=" + Created_Date + ", Delivered_Date=" + Delivered_Date + ", Delivered_Status="
				+ Delivered_Status + ", Po_Number=" + Po_Number + ", PoItmNumber=" + PoItmNumber + ", Ndc_Number="
				+ Ndc_Number + ", Invoice_Number=" + Invoice_Number + ", Shipper_Number=" + Shipper_Number
				+ ", Serial_Number_of_goods=" + Serial_Number_of_goods + ", Cmo_Ref_Number=" + Cmo_Ref_Number
				+ ", CustRouteId=" + CustRouteId + ", CustGoodId=" + CustGoodId + ", material_number=" + material_number
				+ ", plant=" + plant + ", secondDevice=" + secondDevice + ", thirdDevice=" + thirdDevice
				+ ", fourthDevice=" + fourthDevice + ", fifthDevice=" + fifthDevice + ", numberOfDevices="
				+ numberOfDevices + ", previousDelivery=" + previousDelivery + ", previousInvoice=" + previousInvoice
				+ ", previousPlant=" + previousPlant + ", shipmentModel=" + shipmentModel + ", Shipment_Status="
				+ Shipment_Status + ", Comments=" + Arrays.toString(Comments) + ", DeviceReturnLocation="
				+ DeviceReturnLocation + ", Device_Id=" + Device_Id + ", IncoTerms=" + IncoTerms + ", Mode=" + Mode
				+ ", Temp=" + Temp + ", RH=" + RH + ", Partner=" + Partner + ", WayPoints=" + WayPoints + "]";
	}

	public String getCustGoodId() {
		return CustGoodId;
	}

	public void setCustGoodId(String custGoodId) {
		CustGoodId = custGoodId;
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

	public String getNumberOfDevices() {
		return numberOfDevices;
	}

	public void setNumberOfDevices(String numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
	}

	public String getPreviousDelivery() {
		return previousDelivery;
	}

	public void setPreviousDelivery(String previousDelivery) {
		this.previousDelivery = previousDelivery;
	}

	public String getPreviousInvoice() {
		return previousInvoice;
	}

	public void setPreviousInvoice(String previousInvoice) {
		this.previousInvoice = previousInvoice;
	}

	public String getPreviousPlant() {
		return previousPlant;
	}

	public void setPreviousPlant(String previousPlant) {
		this.previousPlant = previousPlant;
	}

	public String getShipmentModel() {
		return shipmentModel;
	}

	public void setShipmentModel(String shipmentModel) {
		this.shipmentModel = shipmentModel;
	}

	public String getFifthDevice() {
		return fifthDevice;
	}

	public void setFifthDevice(String fifthDevice) {
		this.fifthDevice = fifthDevice;
	}

}


/*
 * @Indexed(unique = true)
 * 
 * @Field(value = "Shipment_Id") private String shipmentID;
 * 
 * @Field(value = "Goods_Id") private String GoodsID;
 * 
 * @Field(value = "Customer_Id") private String customerId;
 * 
 * @Field(value = "Route_Id") private String RouteID;
 * 
 * @Field(value = "Goods_Desc") private String Goods_Desc;
 * 
 * 
 * @Field(value = "Created_Date") private Map<String, String> CreatedDate;
 * 
 * @Field(value = "Estimated_Delivery") private Map<String, String>
 * Estimated_Delivery;
 * 
 * @Field(value = "Delivered_Date") private Map<String, String> Delivered_Date;
 * 
 * @Field(value = "Date") private String Date;
 * 
 * @Field(value = "Delivered_Status") private String Delivered_Status;
 * 
 * @Field(value = "Para1") private String Para1;
 * 
 * @Field(value = "Para2") private String Para2;
 * 
 * @Field(value = "TimeZone") private String TimeZone;
 * 
 * @Field(value = "Comments") private String Comments;
 * 
 * @Field(value = "Goods_Desc") private String Goods_Desc;
 * 
 * @Field(value = "Goods_Desc") private String Goods_Desc;
 * 
 * 
 * public String getId() {
 * 
 * return _id; }
 * 
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * 
 * public String getGoodsID() { JSONObject row = new JSONObject(); JSONArray
 * json = new JSONArray();
 * 
 * return GoodsID; } public String getCustomerId() { return customerId; } public
 * String getshipmentID() { return shipmentID; }
 * 
 * 
 * 
 * public String getComments() { return Comments; }
 * 
 * public String getDeliveredStatus() { return Delivered_Status; } public String
 * getPara1() { return Para1; } public String getPara2() { return Para2; }
 * 
 * 
 * public Map<String, String> getCreatedDate() { return CreatedDate; } public
 * Map<String, String> getEstimatedDelivery() { return Estimated_Delivery; }
 * public Map<String, String> getDeliveredDate() { return Delivered_Date; }
 * 
 * 
 * public String getGoodsDesc() { return Goods_Desc; }
 * 
 * 
 * public void setEmpNo(String empNo) { this.empNo = empNo; }
 * 
 * 
 * 
 * public void setFullName(String fullName) { this.fullName = fullName; }
 * 
 * public String getRouteID() { return RouteID; }
 * 
 * 
 * public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
 * 
 * 
 * 
 * @GetMapping public Map<String, String> sayHello() { HashMap<String,
 * String>map = new HashMap<>(); map.put("id", this._id);
 * map.put("Shipment_Id",shipmentID); map.put("Route_Id", this.RouteID);
 * map.put("GoodsID",this.GoodsID); return map; }
 * 
 * 
 * @Override public String toString() { return "id:" + this._id +
 * ", CustomerId: " + this.customerId+", Shipment_Id: " + this.shipmentID +
 * ", Route_Id: " + this.RouteID + ", GoodsDesc: " + this.Goods_Desc+
 * ", CreatedDate: " + this.CreatedDate + ", Estimated_Delivery: " +
 * this.Estimated_Delivery + ", Delivered_Date: " + this.Delivered_Date +
 * ", GoodsID: " + this.GoodsID+", Delivered_Status: " + this.Delivered_Status+
 * ", Comments: " + this.Comments+", Para1: " + this.Para1 +", Para2: " +
 * this.Para2; }
 */