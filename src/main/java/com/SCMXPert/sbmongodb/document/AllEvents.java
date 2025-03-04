package com.SCMXPert.sbmongodb.document;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class AllEvents {

	@Pattern(message="Invalid Input", regexp ="|.[a-zA-Z0-9  _.,-]+")
	private String Event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Bp_Id;
	public String getBp_Id() {
		return Bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		Bp_Id = bp_Id;
	}
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Partner;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event;
	private String DateandTime;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Evidence;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Event_Statusa;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String PartnerId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Status;

	public String getEvent_Id() {
		return Event_Id;
	}

	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
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

	public String getDateandTime() {
		return DateandTime;
	}

	public void setDateandTime(String dateandTime) {
		DateandTime = dateandTime;
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

	public String getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(String partnerId) {
		PartnerId = partnerId;
	}

	public String getEvent_Status() {
		return Event_Status;
	}

	public void setEvent_Status(String event_Status) {
		Event_Status = event_Status;
	}

}
