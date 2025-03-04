package com.SCMXPert.sbmongodb.document;

import java.util.List;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class CreateNewShipmentDto {

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
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String InternalShipmentId;
	
	private String CustomerId; // shipment and shipmentTxns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Shipment_Number; // shipment and and shipmentTxns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Shipment_Num; // shipment and and shipmentTxns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String TypeOfReference; // shipment
	
	private String[] Comments; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String RouteId; // shipment
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ,-]+")
	private String RouteFrom; // shipment
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ,-]+")
	private String RouteTo; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String GoodsId; // shipment
	@Pattern(message="Invalid Input", regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String GoodsType; // shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String DeviceId; // Shpment txns
	private String EstimatedDeliveryDate; // Shipment
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String ParnterFrom; // Shipment Txns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerTo; // Shipment Txns
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String EventName; // Shipment Txns
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private List<AllEvents> AllEvents;
	private String DateAndTime; // Shipment Txns Event executionTime
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String IncoTerms;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String batch_Id;
	
	private String[] EvidenceList;
	private String[] Evidence_URL;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String material_number;
	
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
	
	
	public String getBatch_Id() {
		return batch_Id;
	}

	public void setBatch_Id(String batch_Id) {
		this.batch_Id = batch_Id;
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

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Mode;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Temp;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Partner;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Bp_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Po_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PoItmNumber;	
	public String getPoItmNumber() {
		return PoItmNumber;
	}

	public void setPoItmNumber(String poItmNumber) {
		PoItmNumber = poItmNumber;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Ndc_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Invoice_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Shipper_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Serial_Number_of_goods;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Cmo_Ref_Number;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustRouteId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String CustGoodId;
	
	public String getCmo_Ref_Number() {
		return Cmo_Ref_Number;
	}

	public void setCmo_Ref_Number(String cmo_Ref_Number) {
		Cmo_Ref_Number = cmo_Ref_Number;
	}

	public String getBp_Id() {
		return Bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		Bp_Id = bp_Id;
	}

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Event;
	private String Datee;
//	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String RH;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Mode_of_Transport;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -,]+")
	private String Event_Status;
	
	private String Created_Date;
	// private DropDownShipmentDetails DropDown;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String plant;

	public String getEvent_Status() {
		return Event_Status;
	}

	public void setEvent_Status(String event_Status) {
		Event_Status = event_Status;
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

	public void setAllEvents(List<AllEvents> allEvents) {
		AllEvents = allEvents;
	}

	public List<AllEvents> getAllEvents() {
		return AllEvents;
	}

	public void setInternalShipmentId(String internalShipmentId) {
		InternalShipmentId = internalShipmentId;
	}

	public String getInternalShipmentId() {
		return InternalShipmentId;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getShipment_Number() {
		return Shipment_Number;
	}

	public void setShipment_Number(String shipment_Number) {
		Shipment_Number = shipment_Number;
	}

	public String getShipment_Num() {
		return Shipment_Num;
	}

	public void setShipment_Num(String shipment_Num) {
		Shipment_Num = shipment_Num;
	}

	public String getTypeOfReference() {
		return TypeOfReference;
	}

	public void setTypeOfReference(String typeOfReference) {
		TypeOfReference = typeOfReference;
	}

	public String[] getComments() {
		return Comments;
	}

	public void setComments(String[] comments) {
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

	public String getDatee() {
		return Datee;
	}

	public void setDatee(String datee) {
		Datee = datee;
	}

	public String getMode_of_Transport() {
		return Mode_of_Transport;
	}

	public void setMode_of_Transport(String mode_of_Transport) {
		Mode_of_Transport = mode_of_Transport;
	}

	public String getCreated_Date() {
		return Created_Date;
	}

	public void setCreated_Date(String created_Date) {
		Created_Date = created_Date;
	}

	public String getCustRouteId() {
		return CustRouteId;
	}

	public void setCustRouteId(String custRouteId) {
		CustRouteId = custRouteId;
	}

	public String getCustGoodId() {
		return CustGoodId;
	}

	public void setCustGoodId(String custGoodId) {
		CustGoodId = custGoodId;
	}

	public String[] getEvidenceList() {
		return EvidenceList;
	}

	public void setEvidenceList(String[] evidenceList) {
		EvidenceList = evidenceList;
	}

	public String[] getEvidence_URL() {
		return Evidence_URL;
	}

	public void setEvidence_URL(String[] evidence_URL) {
		Evidence_URL = evidence_URL;
	}

	public String getMaterial_number() {
		return material_number;
	}

	public void setMaterial_number(String material_number) {
		this.material_number = material_number;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
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

}
