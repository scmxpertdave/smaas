package com.SCMXPert.sbmongodb.document;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CountyStateList")
public class CountryStateList {

	@Id
	private ObjectId id;

	private List<Countries> Countries;

	public void setCountries(List<Countries> countries) {
		Countries = countries;
	}

	public List<Countries> getCountries() {
		return Countries;
	}

}
