package com.SCMXPert.sbmongodb.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Pattern;

//import jakarta.validation.constraints.Pattern;


public class Events {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Event_Id;
    @Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Event_Status;
	
	//Getters and Setters
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

	
}
