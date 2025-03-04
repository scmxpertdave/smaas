package com.SCMXPert.sbmongodb.document;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class Phone {

	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String Type;
	@Pattern(message="Invalid Input", regexp = "[0-9 ]+")
	private String Number;

	public Phone() {
		// TODO Auto-generated constructor stub
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getNumber() {
		return Number;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getType() {
		return Type;
	}

}
