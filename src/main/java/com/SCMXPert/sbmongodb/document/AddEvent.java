package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

//import jakarta.validation.constraints.Pattern;

/**
 * @author Uday
 **/
public class AddEvent {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  - ]+")
	private String Bussines_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventStatus;

	public String getBussines_Id() {
		return Bussines_Id;
	}

	public void setBussines_Id(String bussines_Id) {
		Bussines_Id = bussines_Id;
	}

	public String getEvent_Id() {
		return Event_Id;
	}

	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
	}

	public String getEventStatus() {
		return EventStatus;
	}

	public void setEventStatus(String eventStatus) {
		EventStatus = eventStatus;
	}

}
