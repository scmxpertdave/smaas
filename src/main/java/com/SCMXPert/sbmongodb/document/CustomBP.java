package com.SCMXPert.sbmongodb.document;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Pattern;

public class CustomBP {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String BP_Id;
	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Company_Name;
	
	private List<@Valid Events> events;

	public String getBP_Id() {
		return BP_Id;
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

	public void setEvents(List<Events> events) {
		this.events = events;
	}
	
	public List<Events> getEvents() {
		return events;
	}

}
