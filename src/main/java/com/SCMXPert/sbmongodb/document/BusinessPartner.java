package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Pattern;

@Document(collection = "BusinessPartner")
public class BusinessPartner {

	@Id
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private ObjectId id;

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String BP_Id;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String BP_Type;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String BP_Role;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Company_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Name1;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Name2;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String[] Device_Ids;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Escalation;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Time_Zone;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Communication_Method;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String External_Number;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Partner_Type;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private List<@Valid Addresses> Locations;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Status;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Partner_Status;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Escallation_Manager;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String[] Listrole;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Location;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Customer_Id;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Addresses;
//	private List<EventId> Event;
	
	public String[] getListrole() {
		return Listrole;
	}

	public void setListrole(String[] listrole) {
		Listrole = listrole;
	}

	/*
	 * @Field(value = "Locations") private List<Addresses> addresses;
	 */
	
	private List<Contact> contact;
//	@Field(value = "Events")
//	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?)]*$")
	private List<@Valid Events> Events;

	// Getters and Setters
	public String getExternal_Number() {
		return External_Number;
	}

	public void setExternal_Number(String external_Number) {
		External_Number = external_Number;
	}
	public String getBP_Id() {
		return BP_Id;
	}

	public void setBP_Id(String bP_Id) {
		BP_Id = bP_Id;
	}

	public String getBP_Type() {
		return BP_Type;
	}

	public void setBP_Type(String bP_Type) {
		BP_Type = bP_Type;
	}

	public String getBP_Role() {
		return BP_Role;
	}

	public void setBP_Role(String bP_Role) {
		BP_Role = bP_Role;
	}

	public String getCompany_Name() {
		return Company_Name;
	}

	public void setCompany_Name(String company_Name) {
		Company_Name = company_Name;
	}

	public String getName1() {
		return Name1;
	}

	public void setName1(String name1) {
		Name1 = name1;
	}

	public String getName2() {
		return Name2;
	}

	public void setName2(String name2) {
		Name2 = name2;
	}

	public String[] getDevice_Ids() {
		return Device_Ids;
	}

	public void setDevice_Ids(String[] device_Ids) {
		Device_Ids = device_Ids;
	}

	public String getEscalation() {
		return Escalation;
	}

	public void setEscalation(String escalation) {
		Escalation = escalation;
	}

	public String getTime_Zone() {
		return Time_Zone;
	}

	public void setTime_Zone(String time_Zone) {
		Time_Zone = time_Zone;
	}

//	public List<Addresses> getAddresses() {
//		return addresses;
//	}
//
//	public void setAddresses(List<Addresses> addresses) {
//		this.addresses = addresses;
//	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	public List<Events> getEvents() {
		return Events;
	}

	public void setEvents(List<Events> events) {
		this.Events = events;
	}

	public String getCommunication_Method() {
		return Communication_Method;
	}

	public void setCommunication_Method(String communication_Method) {
		Communication_Method = communication_Method;
	}

	/*
	 * public List<EventId> getEvent() { return Event; }
	 * 
	 * public void setEvent(List<EventId> eventList) { Event = eventList; }
	 */

	public String getPartner_Type() {
		return Partner_Type;
	}

	public void setPartner_Type(String partner_Type) {
		Partner_Type = partner_Type;
	}

	public List<Addresses> getLocations() {
		return Locations;
	}

	public void setLocations(List<Addresses> locations) {
		Locations = locations;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getEscallation_Manager() {
		return Escallation_Manager;
	}

	public void setEscallation_Manager(String escallation_Manager) {
		Escallation_Manager = escallation_Manager;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getPartner_Status() {
		return Partner_Status;
	}

	public void setPartner_Status(String partner_Status) {
		Partner_Status = partner_Status;
	}

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}

	public String getAddresses() {
		return Addresses;
	}

	public void setAddresses(String addresses) {
		Addresses = addresses;
	}

	@Override
	public String toString() {
		return "BusinessPartner [id=" + id + ", BP_Id=" + BP_Id + ", BP_Type=" + BP_Type + ", BP_Role=" + BP_Role
				+ ", Company_Name=" + Company_Name + ", Name1=" + Name1 + ", Name2=" + Name2 + ", Device_Ids="
				+ Arrays.toString(Device_Ids) + ", Escalation=" + Escalation + ", Time_Zone=" + Time_Zone
				+ ", Communication_Method=" + Communication_Method + ", External_Number=" + External_Number
				+ ", Partner_Type=" + Partner_Type + ", Locations=" + Locations + ", Status=" + Status
				+ ", Partner_Status=" + Partner_Status + ", Escallation_Manager=" + Escallation_Manager + ", Listrole="
				+ Arrays.toString(Listrole) + ", Location=" + Location + ", Customer_Id=" + Customer_Id + ", Addresses="
				+ Addresses + ", contact=" + contact + ", events=" + Events + "]";
	}

}

/*
 * @Indexed(unique = true)
 * 
 * @Field(value = "BP_Id") private String BPId; public String getBPId() { return
 * BPId; }
 * 
 * @Field(value = "BP_Type") private String BP_Type; public String getBP_Type()
 * { return BP_Type; }
 * 
 * @Field(value = "BP_Role") private String BP_Role; public String getBP_Role()
 * { return BP_Role; }
 * 
 * @Field(value = "Company_Name") private String Company_Name; public String
 * getCompany_Name() { return Company_Name; }
 * 
 * @Field(value = "Name1") private String Name1; public String getName1() {
 * return Name1; }
 * 
 * @Field(value = "Name2") private String Name2; public String getName2() {
 * return Name2; }
 * 
 * @Field(value = "Device_Ids") private String Device_Ids[]; public String[]
 * getDevice_Ids() { return Device_Ids; }
 * 
 * @Field(value = "Escalation") private String Escalation; public String
 * getEscalation() { return Escalation; }
 * 
 * @Field(value = "Time_Zone") private String Time_Zone; public String
 * getTime_Zone() { return Time_Zone; }
 * 
 * @Field(value = "Addresses") private Map<String, String> Addresses[]; public
 * Map<String, String>[] getAddresses() { return Addresses; }
 * 
 * @Field(value = "Contact")
 * 
 * private ArrayList <Contact> Contact; public ArrayList<Contact> getContact() {
 * return Contact;
 * 
 * }
 * 
 * @Field(value = "Events") private Map<String, String> Events[]; public
 * Map<String, String>[] getEvents() { return Events; }
 * 
 * @Override public String toString() { return "id:" + this._id + ", BP_Id: " +
 * this.BPId+", BP_Type: " + this.BP_Type + ", BP_Role: " + this.BP_Role +
 * ", Company_Name: " + this.Company_Name+ ", Name1: " + this.Name1 +
 * ", Name2: " + this.Name2 + ", Device_Ids: " + this.Device_Ids +
 * ", Escalation: " + this.Escalation+", Time_Zone: " + this.Time_Zone+
 * ", Addresses: " + this.Addresses+", Contact: " + this.Contact +", Events: " +
 * this.Events; }
 */