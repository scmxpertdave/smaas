package com.SCMXPert.sbmongodb.document;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import jakarta.validation.constraints.Pattern;
import javax.validation.constraints.Pattern;

public class DefaultEvents {
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String event_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String bp_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String partner_Name;
	public String getEvent_Id() {
		return event_Id;
	}
	public void setEvent_Id(String event_Id) {
		this.event_Id = event_Id;
	}
	public String getEvent_Name() {
		return event_Name;
	}
	public void setEvent_Name(String event_Name) {
		this.event_Name = event_Name;
	}
	public String getBp_Id() {
		return bp_Id;
	}
	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}
	public String getPartner_Name() {
		return partner_Name;
	}
	public void setPartner_Name(String partner_Name) {
		this.partner_Name = partner_Name;
	}
	


}
