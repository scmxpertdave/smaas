package com.SCMXPert.sbmongodb.document;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class Default_Business_Partners_And_Events_Dto {

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Bp_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Bp_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Name;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Photo_Evidence;

	public String getBp_Id() {
		return Bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		Bp_Id = bp_Id;
	}

	public String getBp_Name() {
		return Bp_Name;
	}

	public void setBp_Name(String bp_Name) {
		Bp_Name = bp_Name;
	}

	public String getEvent_Id() {
		return Event_Id;
	}

	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
	}

	public void setEvent_Name(String event_Name) {
		Event_Name = event_Name;
	}

	public String getEvent_Name() {
		return Event_Name;
	}

	public String getPhoto_Evidence() {
		return Photo_Evidence;
	}

	public void setPhoto_Evidence(String photo_Evidence) {
		Photo_Evidence = photo_Evidence;
	}

}
