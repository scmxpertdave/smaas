package com.SCMXPert.sbmongodb.document;

import java.util.List;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Pattern;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


public class BusinessPartnerDto {

	// General Information
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String BP_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Company_Name; //
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Name1; //
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Name2; //
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Communication_Method;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String fullname;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String role;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Partner_Role;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String UserName;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String AdminName;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Partner_Type;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private List<Addresses> Locations;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Status;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Escallation_Manager;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z  ]+")
	private String Event_Status;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z ]+")
	private String Location;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 _.,’-]+")
	private String Customer_Name;
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String[] rolevalue;
	public String[] getRolevalue() {
		return rolevalue;
	}
	public void setRolevalue(String[] rolevalue) {
		this.rolevalue = rolevalue;
	}
	
	private List<Roles> AllRoles;
	public List<Roles> getALLRoles() {
		return AllRoles;
	}
	public void setALLRoles(List<Roles> aLLRoles) {
		AllRoles = aLLRoles;
	}
	public String getCommunication_Method() {
		return Communication_Method;
	}
	public void setCommunication_Method(String communication_Method) {
		Communication_Method = communication_Method;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String External_Number; //
	//private String[] Partner_Status; // Business partner Status
	
	// Communication
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 .@ ]+")
	private String Email_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 .@ ]+")
	private String Email;
	@Pattern(message="Type can contain numbers only", regexp = "|.[0-9 ]+")
	private String TelephoneNumber;
	@Pattern(message="Type can contain numbers only", regexp = "|.[0-9 ]+")
	private String CellPhoneNumber;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Escalation;
	
	// User //Adminstartor
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String UserId;                       
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9&&[@#$] ]+")
	private String Password;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String credentials;
	
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Language;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Time_Zone;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Date_Format;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String UserBP_Id;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Customer_Id;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private List<@Valid Events> AllEvent;
	/* private String NewBP_Id; */
	
	//Partner Access Matrix
	
	public String getUserBP_Id() {
		return UserBP_Id;
	}
	public void setUserBP_Id(String userBP_Id) {
		UserBP_Id = userBP_Id;
	}
	public String getBP_Id() {
		return BP_Id;
	}
	public String getPartner_Role() {
		return Partner_Role;
	}
	public void setPartner_Role(String partner_Role) {
		Partner_Role = partner_Role;
	}
	public void setBP_Id(String bP_Id) {
		BP_Id = bP_Id;
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
	public String getExternal_Number() {
		return External_Number;
	}
	public void setExternal_Number(String external_Number) {
		External_Number = external_Number;
	}

	/*
	 * public String[] getPartner_Status() { return Partner_Status; } public void
	 * setPartner_Status(String[] partner_Status) { Partner_Status = partner_Status;
	 * }
	 */
	public String getEmail_Id() {
		return Email_Id;
	}
	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}
	public String getTelephoneNumber() {
		return TelephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		TelephoneNumber = telephoneNumber;
	}
	public String getCellPhoneNumber() {
		return CellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumber) {
		CellPhoneNumber = cellPhoneNumber;
	}
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getTime_Zone() {
		return Time_Zone;
	}
	public void setTime_Zone(String time_Zone) {
		Time_Zone = time_Zone;
	}
	public String getDate_Format() {
		return Date_Format;
	}
	public void setDate_Format(String date_Format) {
		Date_Format = date_Format;
	}
	public List<Events> getAllEvent() {
		return AllEvent;
	}
	public void setAllEvent(List<Events> allEvent) {
		AllEvent = allEvent;
	}
	public String getCustomer_Id() {
		return Customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public List<Roles> getAllRoles() {
		return AllRoles;
	}
	public void setAllRoles(List<Roles> allRoles) {
		AllRoles = allRoles;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
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
	public String getEscalation() {
		return Escalation;
	}

	public void setEscalation(String escalation) {
		Escalation = escalation;
	}

	public String getEscallation_Manager() {
		return Escallation_Manager;
	}
	public void setEscallation_Manager(String escallation_Manager) {
		Escallation_Manager = escallation_Manager;
	}
	public String getEvent_Id() {
		return Event_Id;
	}
	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
	}
	public String getEvent_Status() {
		return Event_Status;
	}
	public void setEvent_Status(String event_Status) {
		Event_Status = event_Status;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}



	

}
