package com.SCMXPert.sbmongodb.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

@Document(collection = "AlertMaster")
public class AlertMaster {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Customer_Id")
	private String Customer_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Customer_Name")
	private String Customer_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Partner_Name")
	private String Partner_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Id")
	private String Alert_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Name")
	private String Alert_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Type")
	private String Alert_Type;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Priority")
	private String Alert_Priority;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Mode")
	private String Alert_Mode;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Email_Addresses")
	private String[] Email_Addresses;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Email_Subject")
	private String Email_Subject;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Email_Message")
	private String Email_Message;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Phone")
	private String Phone;

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Message")
	private String Message;
	


	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Voice_Phone")
	private String Voice_Phone;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Support_Center")
	private String Support_Center;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Chatbot_Id")
	private String Chatbot_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Contact")
	private String Alert_Contact;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Frequency")
	private String Alert_Frequency;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Remediation")
	private String Alert_Remediation;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Alert_Escallation")
	private String Alert_Escallation;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Field(value = "Escallation_Time")
	private String Escallation_Time;
	/////////////////////////////////////////////////
@Field(value="EDI_Message")
private String EDI_Message;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="EDI_Reason")
private String EDI_Reason;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="EDI_Service")
private String EDI_Service;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="WebService")
private String WebService;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="JSON_Format")
private String JSON_Format;
//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="JSON_Path")
private String JSON_Path;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="Partner_service")
private String Partner_service;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="BlockChain")
private String BlockChain;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
@Field(value="Blockchain_key")
private String Blockchain_key;
	
	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}

	public String getPartner_Name() {
		return Partner_Name;
	}

	public void setPartner_Name(String partner_Name) {
		Partner_Name = partner_Name;
	}

	public String getAlert_Id() {
		return Alert_Id;
	}

	public void setAlert_Id(String alert_Id) {
		Alert_Id = alert_Id;
	}

	public String getAlert_Name() {
		return Alert_Name;
	}

	public void setAlert_Name(String aLert_Name) {
		Alert_Name = aLert_Name;
	}

	public String getAlert_Type() {
		return Alert_Type;
	}

	public void setAlert_Type(String alert_Type) {
		Alert_Type = alert_Type;
	}

	public String getAlert_Priority() {
		return Alert_Priority;
	}

	public void setAlert_Priority(String alert_Priority) {
		Alert_Priority = alert_Priority;
	}

	public String getAlert_Mode() {
		return Alert_Mode;
	}

	public void setAlert_Mode(String alert_Mode) {
		Alert_Mode = alert_Mode;
	}

	public String[] getEmail_Addresses() {
		return Email_Addresses;
	}

	public void setEmail_Addresses(String[] email_Addresses) {
		Email_Addresses = email_Addresses;
	}

	public String getEmail_Subject() {
		return Email_Subject;
	}

	public void setEmail_Subject(String email_Subject) {
		Email_Subject = email_Subject;
	}

	public String getEmail_Message() {
		return Email_Message;
	}

	public String setEmail_Message(String email_Message) {
		return Email_Message = email_Message;
	}

	

	public String getVoice_Phone() {
		return Voice_Phone;
	}

	public void setVoice_Phone(String voice_Phone) {
		Voice_Phone = voice_Phone;
	}

	public String getSupport_Center() {
		return Support_Center;
	}

	public void setSupport_Center(String support_Center) {
		Support_Center = support_Center;
	}

	public String getChatbot_Id() {
		return Chatbot_Id;
	}

	public void setChatbot_Id(String chatbot_Id) {
		Chatbot_Id = chatbot_Id;
	}

	public String getAlert_Contact() {
		return Alert_Contact;
	}

	public void setAlert_Contact(String alert_Contact) {
		Alert_Contact = alert_Contact;
	}

	public String getAlert_Frequency() {
		return Alert_Frequency;
	}

	public void setAlert_Frequency(String alert_Frequency) {
		Alert_Frequency = alert_Frequency;
	}

	public String getAlert_Remediation() {
		return Alert_Remediation;
	}

	public void setAlert_Remediation(String alert_Remediation) {
		Alert_Remediation = alert_Remediation;
	}

	public String getAlert_Escallation() {
		return Alert_Escallation;
	}

	public void setAlert_Escallation(String alert_Escallation) {
		Alert_Escallation = alert_Escallation;
	}

	public String getEscallation_Time() {
		return Escallation_Time;
	}

	public void setEscallation_Time(String escallation_Time) {
		Escallation_Time = escallation_Time;
	}

	public String getEDI_Message() {
		return EDI_Message;
	}

	public void setEDI_Message(String eDI_Message) {
		EDI_Message = eDI_Message;
	}

	public String getEDI_Reason() {
		return EDI_Reason;
	}

	public void setEDI_Reason(String eDI_Reason) {
		EDI_Reason = eDI_Reason;
	}

	public String getEDI_Service() {
		return EDI_Service;
	}

	public void setEDI_Service(String eDI_Service) {
		EDI_Service = eDI_Service;
	}

	public String getWebService() {
		return WebService;
	}

	public void setWebService(String webService) {
		WebService = webService;
	}

	public String getJSON_Format() {
		return JSON_Format;
	}

	public void setJSON_Format(String jSON_Format) {
		JSON_Format = jSON_Format;
	}

	public String getJSON_Path() {
		return JSON_Path;
	}

	public void setJSON_Path(String jSON_Path) {
		JSON_Path = jSON_Path;
	}

	public String getPartner_service() {
		return Partner_service;
	}

	public void setPartner_service(String partner_service) {
		Partner_service = partner_service;
	}

	public String getBlockChain() {
		return BlockChain;
	}

	public void setBlockChain(String blockChain) {
		BlockChain = blockChain;
	}

	public String getBlockchain_key() {
		return Blockchain_key;
	}

	public void setBlockchain_key(String blockchain_key) {
		Blockchain_key = blockchain_key;
	}

	
}
