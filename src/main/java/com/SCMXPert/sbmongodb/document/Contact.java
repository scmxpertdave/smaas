
package com.SCMXPert.sbmongodb.document;

import java.util.List;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class Contact {

	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Type;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Contact_Name; //
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Designation; // (added now this field)
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Email_Id;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String TelephoneNumber;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String CellPhoneNumber;
	// private String AltrContactName;
	// private String AltrEmailAddress;
	
	private List<Phone> Phone;
	private AlternateContacts AlternateContact;

	public void setAlternateContact(AlternateContacts alternateContact) {
		AlternateContact = alternateContact;
	}

	public AlternateContacts getAlternateContact() {
		return AlternateContact;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		CellPhoneNumber = cellPhoneNumber;
	}

	public String getCellPhoneNumber() {
		return CellPhoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		TelephoneNumber = telephoneNumber;
	}

	public String getTelephoneNumber() {
		return TelephoneNumber;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getDesignation() {
		return Designation;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getContact_Name() {
		return Contact_Name;
	}

	public void setContact_Name(String contact_Name) {
		Contact_Name = contact_Name;
	}

	public void setPhone(List<Phone> phone) {
		Phone = phone;
	}

	public List<Phone> getPhone() {
		return Phone;
	}

	public String getEmail_Id() {
		return Email_Id;
	}

	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}

}

/*
 * public class PhoneC {
 * 
 * public String getPhone() { return Phone; }
 * 
 * public void setPhone(String phone) { Phone = phone; }
 * 
 * public String getType() { return Type; }
 * 
 * public void setType(String type) { Type = type; }
 * 
 * public String getNumber() { return Number; }
 * 
 * public void setNumber(String number) { Number = number; }
 * 
 * private String Phone;
 * 
 * private String Type;
 * 
 * private String Number;
 * 
 * 
 * 
 * }
 * 
 * public String getType() { return Type; } public void setType(String type) {
 * Type = type; } public String getContact_Name() { return Contact_Name; }
 * public void setContact_Name(String contact_Name) { Contact_Name =
 * contact_Name; } public String getEmail_Id() { return Email_Id; } public void
 * setEmail_Id(String email_Id) { Email_Id = email_Id; } public
 * ArrayList<PhoneC> getPhone() { return Phone; } public void
 * setPhone(ArrayList<PhoneC> phone) { Phone = phone; } private String Type;
 * 
 * private String Contact_Name; // private //
 * 
 * private String Email_Id; private ArrayList <PhoneC> Phone; // setter and
 * getters }
 */