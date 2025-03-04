package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="CountriesInfo")
public class Countries {
	@Field(value = "states")
	private Map<String,String[]> states;
	
	@Field(value = "name")
	private String name;

	public Map<String, String[]> getStates() {
		return states;
	}

	public void setStates(Map<String, String[]> states) {
		this.states = states;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @Override public String toString() { return "Countries [states=" + states +
	 * ", name=" + name + ", getStates()=" + getStates() + ", getName()=" +
	 * getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() +
	 * ", toString()=" + super.toString() + "]"; }
	 */

	
	
	
	

}
