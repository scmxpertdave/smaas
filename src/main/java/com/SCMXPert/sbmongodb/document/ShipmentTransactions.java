package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;

import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ShipmentTransactions")
public class ShipmentTransactions {

	@Id
	private ObjectId id;
	
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Customer_Id;
	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Shipment_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Shipment_Num;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Shipment_Number;

    @Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private long Event_SNo; // changed from String to Long
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Mode_of_Transport;

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Device_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String IMEI_Number;
	
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Parent_Device_Id;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Ship_Date_From_BP;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Expected_Date_At_BP;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .,- ]+")
	private String Partner_From;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .,-]+")
	private String Partner_To;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .,-]+")
	private String Event_Status;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .,-]+")
	private String[] Comments;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Internal_temperature;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .-,]+")
	private String temp_1;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Current_Terminal;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .,-]+")
	private String Evidence;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .,-]+")
	private String Event_Statusa;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String[] EvidenceList;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  .-]+")
	private String Partner;
	private String[] Evidence_URL;
	private String Invoice_Number;
	private String batch_Id;

	private String[] Event_Description;
	private String[] Evidence_Description;
	private String[] Evidence_For;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String material_number;
	private String[] DocCreatedDate;
	private String[] DocId;
	
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
	
	
//	DocCreatedDate
//	DocId
//	EvidenceList
//	Evidence_Description
//	Evidence_For
//	Evidence_URL
//	TypeOfReference
		
	
	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getPartner() {
		return Partner;
	}

	public void setPartner(String partner) {
		Partner = partner;
	}

	public String getTemp_1() {
		return temp_1;
	}

	public void setTemp_1(String temp_1) {
		this.temp_1 = temp_1;
	}

	public String getCurrent_Terminal() {
		return Current_Terminal;
	}

	public void setCurrent_Terminal(String current_Terminal) {
		Current_Terminal = current_Terminal;
	}

	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Event_Id;

	

	
	
	/*
	 * private String Event_Id; public String getEvent_Id() { return Event_Id; }
	 * 
	 * public void setEvent_Id(String event_Id) { Event_Id = event_Id; }
	 */

	private String Event_Exec_Date;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventReferenceNumber; // added this Property
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String[] TypeOfReference;   	 // added this Property
	//private String TypeOfReference;

	
	public String getShipment_Number() {
		return Shipment_Number;
	}

	public void setShipment_Number(String shipment_Number) {
		Shipment_Number = shipment_Number;
	}

	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
	}

	public String getEvent_Id() {
		return Event_Id;
	}
	
	public void setTypeOfReference(String[] typeOfReference) {
		TypeOfReference = typeOfReference;
	}
	
	public String[] getTypeOfReference() {
		return TypeOfReference;
	}
	
	public void setEventReferenceNumber(String eventReferenceNumber) {
		EventReferenceNumber = eventReferenceNumber;
	}
	
	
	public String getEventReferenceNumber() {
		return EventReferenceNumber;
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
	/*
	 * public String getEvent_SNo() { return Event_SNo; } public void
	 * setEvent_SNo(String event_SNo) { Event_SNo = event_SNo; }
	 */

	public void setEvent_SNo(long event_SNo) {
		Event_SNo = event_SNo;
	}

	public long getEvent_SNo() {
		return Event_SNo;
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

	public String getIMEI_Number() {
		return IMEI_Number;
	}

	public void setIMEI_Number(String iMEI_Number) {
		IMEI_Number = iMEI_Number;
	}

	public String getParent_Device_Id() {
		return Parent_Device_Id;
	}

	public void setParent_Device_Id(String parent_Device_Id) {
		Parent_Device_Id = parent_Device_Id;
	}

	public String getShip_Date_From_BP() {
		return Ship_Date_From_BP;
	}

	public void setShip_Date_From_BP(String ship_Date_From_BP) {
		Ship_Date_From_BP = ship_Date_From_BP;
	}

	public String getExpected_Date_At_BP() {
		return Expected_Date_At_BP;
	}

	public void setExpected_Date_At_BP(String expected_Date_At_BP) {
		Expected_Date_At_BP = expected_Date_At_BP;
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

	public String getEvent_Status() {
		return Event_Status;
	}

	public void setEvent_Status(String event_Status) {
		Event_Status = event_Status;
	}

	public String[] getComments() {
		return Comments;
	}

	public void setComments(String[] comments) {
		Comments = comments;
	}

	public String getEvent_Exec_Date() {
		return Event_Exec_Date;
	}

	public void setEvent_Exec_Date(String event_Exec_Date) {
		Event_Exec_Date = event_Exec_Date;
	}

	public String getInternal_temperature() {
		return Internal_temperature;
	}

	public void setInternal_temperature(String internal_temperature) {
		Internal_temperature = internal_temperature;
	}

	public String getEvidence() {
		return Evidence;
	}

	public void setEvidence(String evidence) {
		Evidence = evidence;
	}

	public String getEvent_Statusa() {
		return Event_Statusa;
	}

	public void setEvent_Statusa(String event_Statusa) {
		Event_Statusa = event_Statusa;
	}

	

	public String[] getEvidenceList() {
		return EvidenceList;
	}

	public void setEvidenceList(String[] evidenceList) {
		EvidenceList = evidenceList;
	}

//	@Override
//	public String toString() {
//		return "ShipmentTransactions [id=" + id + ", Customer_Id=" + Customer_Id + ", Shipment_Id=" + Shipment_Id
//				+ ", Shipment_Num=" + Shipment_Num + ", Shipment_Number=" + Shipment_Number + ", Event_SNo=" + Event_SNo
//				+ ", Event_Name=" + Event_Name + ", Mode_of_Transport=" + Mode_of_Transport + ", Device_Id=" + Device_Id
//				+ ", IMEI_Number=" + IMEI_Number + ", Parent_Device_Id=" + Parent_Device_Id + ", Ship_Date_From_BP="
//				+ Ship_Date_From_BP + ", Expected_Date_At_BP=" + Expected_Date_At_BP + ", Partner_From=" + Partner_From
//				+ ", Partner_To=" + Partner_To + ", Event_Status=" + Event_Status + ", Comments="
//				+ Arrays.toString(Comments) + ", Internal_temperature=" + Internal_temperature + ", temp_1=" + temp_1
//				+ ", Current_Terminal=" + Current_Terminal + ", Evidence=" + Evidence + ", Event_Statusa="
//				+ Event_Statusa + ", EvidenceList=" + Arrays.toString(EvidenceList) + ", Partner=" + Partner
//				+ ", Event_Id=" + Event_Id + ", Event_Exec_Date=" + Event_Exec_Date + ", EventReferenceNumber="
//				+ EventReferenceNumber + ", TypeOfReference=" + TypeOfReference + "]";
//	}
	
	
	

	public String[] getEvidence_URL() {
		return Evidence_URL;
	}
	
///Below toString is commented when the addition of multiple devices has happened
	
//	@Override
//	public String toString() {
//		return "ShipmentTransactions [id=" + id + ", Customer_Id=" + Customer_Id + ", Shipment_Id=" + Shipment_Id
//				+ ", Shipment_Num=" + Shipment_Num + ", Shipment_Number=" + Shipment_Number + ", Event_SNo=" + Event_SNo
//				+ ", Event_Name=" + Event_Name + ", Mode_of_Transport=" + Mode_of_Transport + ", Device_Id=" + Device_Id
//				+ ", IMEI_Number=" + IMEI_Number + ", Parent_Device_Id=" + Parent_Device_Id + ", Ship_Date_From_BP="
//				+ Ship_Date_From_BP + ", Expected_Date_At_BP=" + Expected_Date_At_BP + ", Partner_From=" + Partner_From
//				+ ", Partner_To=" + Partner_To + ", Event_Status=" + Event_Status + ", Comments="
//				+ Arrays.toString(Comments) + ", Internal_temperature=" + Internal_temperature + ", temp_1=" + temp_1
//				+ ", Current_Terminal=" + Current_Terminal + ", Evidence=" + Evidence + ", Event_Statusa="
//				+ Event_Statusa + ", EvidenceList=" + Arrays.toString(EvidenceList) + ", Partner=" + Partner
//				+ ", Evidence_URL=" + Arrays.toString(Evidence_URL) + ", Invoice_Number=" + Invoice_Number
//				+ ", batch_Id=" + batch_Id + ", Event_Description=" + Arrays.toString(Event_Description)
//				+ ", Evidence_Description=" + Arrays.toString(Evidence_Description) + ", Evidence_For="
//				+ Arrays.toString(Evidence_For) + ", material_number=" + material_number + ", DocCreatedDate="
//				+ Arrays.toString(DocCreatedDate) + ", DocId=" + Arrays.toString(DocId) + ", plant=" + plant
//				+ ", Event_Id=" + Event_Id + ", Event_Exec_Date=" + Event_Exec_Date + ", EventReferenceNumber="
//				+ EventReferenceNumber + ", TypeOfReference=" + Arrays.toString(TypeOfReference) + "]";
//	}

	public void setEvidence_URL(String[] evidence_URL) {
		Evidence_URL = evidence_URL;
	}

///Below toString is commented when the addition of multiple parameters has happened in swiss model	
	
//	@Override
//	public String toString() {
//		return "ShipmentTransactions [id=" + id + ", Customer_Id=" + Customer_Id + ", Shipment_Id=" + Shipment_Id
//				+ ", Shipment_Num=" + Shipment_Num + ", Shipment_Number=" + Shipment_Number + ", Event_SNo=" + Event_SNo
//				+ ", Event_Name=" + Event_Name + ", Mode_of_Transport=" + Mode_of_Transport + ", Device_Id=" + Device_Id
//				+ ", IMEI_Number=" + IMEI_Number + ", Parent_Device_Id=" + Parent_Device_Id + ", Ship_Date_From_BP="
//				+ Ship_Date_From_BP + ", Expected_Date_At_BP=" + Expected_Date_At_BP + ", Partner_From=" + Partner_From
//				+ ", Partner_To=" + Partner_To + ", Event_Status=" + Event_Status + ", Comments="
//				+ Arrays.toString(Comments) + ", Internal_temperature=" + Internal_temperature + ", temp_1=" + temp_1
//				+ ", Current_Terminal=" + Current_Terminal + ", Evidence=" + Evidence + ", Event_Statusa="
//				+ Event_Statusa + ", EvidenceList=" + Arrays.toString(EvidenceList) + ", Partner=" + Partner
//				+ ", Evidence_URL=" + Arrays.toString(Evidence_URL) + ", Invoice_Number=" + Invoice_Number
//				+ ", batch_Id=" + batch_Id + ", Event_Description=" + Arrays.toString(Event_Description)
//				+ ", Evidence_Description=" + Arrays.toString(Evidence_Description) + ", Evidence_For="
//				+ Arrays.toString(Evidence_For) + ", material_number=" + material_number + ", DocCreatedDate="
//				+ Arrays.toString(DocCreatedDate) + ", DocId=" + Arrays.toString(DocId) + ", plant=" + plant
//				+ ", secondDevice=" + secondDevice + ", thirdDevice=" + thirdDevice + ", fourthDevice=" + fourthDevice
//				+ ", Event_Id=" + Event_Id + ", Event_Exec_Date=" + Event_Exec_Date + ", EventReferenceNumber="
//				+ EventReferenceNumber + ", TypeOfReference=" + Arrays.toString(TypeOfReference) + "]";
//	}
	
	
	@Override
	public String toString() {
		return "ShipmentTransactions [id=" + id + ", Customer_Id=" + Customer_Id + ", Shipment_Id=" + Shipment_Id
				+ ", Shipment_Num=" + Shipment_Num + ", Shipment_Number=" + Shipment_Number + ", Event_SNo=" + Event_SNo
				+ ", Event_Name=" + Event_Name + ", Mode_of_Transport=" + Mode_of_Transport + ", Device_Id=" + Device_Id
				+ ", IMEI_Number=" + IMEI_Number + ", Parent_Device_Id=" + Parent_Device_Id + ", Ship_Date_From_BP="
				+ Ship_Date_From_BP + ", Expected_Date_At_BP=" + Expected_Date_At_BP + ", Partner_From=" + Partner_From
				+ ", Partner_To=" + Partner_To + ", Event_Status=" + Event_Status + ", Comments="
				+ Arrays.toString(Comments) + ", Internal_temperature=" + Internal_temperature + ", temp_1=" + temp_1
				+ ", Current_Terminal=" + Current_Terminal + ", Evidence=" + Evidence + ", Event_Statusa="
				+ Event_Statusa + ", EvidenceList=" + Arrays.toString(EvidenceList) + ", Partner=" + Partner
				+ ", Evidence_URL=" + Arrays.toString(Evidence_URL) + ", Invoice_Number=" + Invoice_Number
				+ ", batch_Id=" + batch_Id + ", Event_Description=" + Arrays.toString(Event_Description)
				+ ", Evidence_Description=" + Arrays.toString(Evidence_Description) + ", Evidence_For="
				+ Arrays.toString(Evidence_For) + ", material_number=" + material_number + ", DocCreatedDate="
				+ Arrays.toString(DocCreatedDate) + ", DocId=" + Arrays.toString(DocId) + ", plant=" + plant
				+ ", secondDevice=" + secondDevice + ", thirdDevice=" + thirdDevice + ", fourthDevice=" + fourthDevice
				+ ", fifthDevice=" + fifthDevice + ", numberOfDevices=" + numberOfDevices + ", previousDelivery="
				+ previousDelivery + ", previousInvoice=" + previousInvoice + ", previousPlant=" + previousPlant
				+ ", shipmentModel=" + shipmentModel + ", Event_Id=" + Event_Id + ", Event_Exec_Date=" + Event_Exec_Date
				+ ", EventReferenceNumber=" + EventReferenceNumber + ", TypeOfReference="
				+ Arrays.toString(TypeOfReference) + "]";
	}
	
	public String getInvoice_Number() {
		return Invoice_Number;
	}

	public void setInvoice_Number(String invoice_Number) {
		Invoice_Number = invoice_Number;
	}

	public String getBatch_Id() {
		return batch_Id;
	}

	public void setBatch_Id(String batch_Id) {
		this.batch_Id = batch_Id;
	}

	public String[] getEvent_Description() {
		return Event_Description;
	}

	public void setEvent_Description(String[] event_Description) {
		Event_Description = event_Description;
	}

	public String[] getEvidence_Description() {
		return Evidence_Description;
	}

	public void setEvidence_Description(String[] evidence_Description) {
		Evidence_Description = evidence_Description;
	}

	public String[] getEvidence_For() {
		return Evidence_For;
	}

	public void setEvidence_For(String[] evidence_For) {
		Evidence_For = evidence_For;
	}

	public String getMaterial_number() {
		return material_number;
	}

	public void setMaterial_number(String material_number) {
		this.material_number = material_number;
	}

	public String[] getDocCreatedDate() {
		return DocCreatedDate;
	}

	public void setDocCreatedDate(String[] docCreatedDate) {
		DocCreatedDate = docCreatedDate;
	}

	public String[] getDocId() {
		return DocId;
	}

	public void setDocId(String[] docId) {
		DocId = docId;
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
 * 
 * 
 * @Indexed(unique = true)
 * 
 * @Field(value = "Shipment_Id") private String shipmentId; public String
 * getshipmentId() { return shipmentId; }
 * 
 * 
 * @Field(value = "Customer_Id") private String CustomerId; public String
 * getCustomerId() { return CustomerId; }
 * 
 * 
 * 
 * 
 * 
 * @Field(value = "BP_Id") private String BP_Id; public String getBPId() {
 * return BP_Id; }
 * 
 * @Field(value = "Event_SNo") private String Event_SNo; public String
 * getEvent_SNo() { return Event_SNo; }
 * 
 * @Field(value = "Event") private String Event; public String getEvent() {
 * return Event; }
 * 
 * @Field(value = "Type_of_Preference") private String Type_of_Preference;
 * public String getType_of_Preference() { return Type_of_Preference; }
 * 
 * @Field(value = "Mode_of_Transport") private String Mode_of_Transport; public
 * String getModeofTransport() { return Mode_of_Transport; }
 * 
 * @Field(value = "Device_Id") private String Device_Id; public String
 * getDeviceId() { return Device_Id; }
 * 
 * @Field(value = "IMEI_Number") private String IMEI_Number; public String
 * getIMEINumber() { return IMEI_Number; }
 * 
 * @Field(value = "Parent_Device_Id") private String Parent_Device_Id; public
 * String getParent_DeviceId() { return Parent_Device_Id; }
 * 
 * @Field(value = "Ship_Date") private Map<String, String> Ship_Date; public
 * Map<String, String> getShipDate() { return Ship_Date; }
 * 
 * @Field(value = "Expected_Date") private Map<String, String> Expected_Date;
 * public Map<String, String> getExpectedDate() { return Expected_Date; }
 * 
 * @Field(value = "Partner_From") private String Partner_From; public String
 * getPartnerFrom() { return Partner_From; }
 * 
 * @Field(value = "Partner_To") private String Partner_To; public String
 * getPartnerTo() { return Partner_To; }
 * 
 * @Field(value = "Event_Status") private String Event_Status; public String
 * getEventStatus() { return Event_Status; }
 * 
 * @Field(value = "Comments") private String Comments[]; public String[]
 * getComments() { return Comments; }
 * 
 * @Field(value = "Event_Exec_Date") private Map<String, String>
 * Event_Exec_Date; public Map<String, String> getEvent_Exec_Date() { return
 * Event_Exec_Date; }
 * 
 * 
 * @Override public String toString() { return "id:" + this._id +
 * ", Customer_Id: " + this.CustomerId+", Shipment_Id: " + this.shipmentId +
 * ", BP_Id: " + this.BP_Id + ", Event_SNo: " + this.Event_SNo+ ", Event: " +
 * this.Event + ", Type_of_Preference: " + this.Type_of_Preference +
 * ", Mode_of_Transport: " + this.Mode_of_Transport + ", Device_Id: " +
 * this.Device_Id+", IMEI_Number: " + this.IMEI_Number+ ", Parent_Device_Id: " +
 * this.Parent_Device_Id+", Ship_Date: " + this.Ship_Date +", Expected_Date: " +
 * this.Expected_Date+ ", Partner_From: "+this.Partner_From +", Partner_To: " +
 * this.Partner_To +", Event_Status: " + this.Event_Status+", Comments: " +
 * this.Comments+", Event_Exec_Date: " + this.Event_Exec_Date; }
 */