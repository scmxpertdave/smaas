package com.SCMXPert.sbmongodb.document;

import java.util.List;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

/**
 * @author Uday
 **/

public class AddRoute {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String CustomerId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String BusinessId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String Route_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String ShipFrom;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String ShipTo;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String PrimaryMode;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String IncoTerms;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String StandradDuration;
	private String RouteStatus;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Description;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String CustRouteId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String FromCountry;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String FromState;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String ToCountry;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String ToState;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String Status;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,-]+")
	private String Documents;
	public String getFromCountry() {
		return FromCountry;
	}

	public void setFromCountry(String fromCountry) {
		FromCountry = fromCountry;
	}

	public String getFromState() {
		return FromState;
	}

	public void setFromState(String fromState) {
		FromState = fromState;
	}

	public String getToCountry() {
		return ToCountry;
	}

	public void setToCountry(String toCountry) {
		ToCountry = toCountry;
	}

	public String getToState() {
		return ToState;
	}

	public void setToState(String toState) {
		ToState = toState;
	}

	private List<Default_Business_Partners_And_Events_Dto> Default_Business_Partners_And_Events;

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setBusinessId(String businessId) {
		BusinessId = businessId;
	}

	public String getBusinessId() {
		return BusinessId;
	}

	public String getRoute_Id() {
		return Route_Id;
	}

	public void setRoute_Id(String route_Id) {
		Route_Id = route_Id;
	}

	public String getShipFrom() {
		return ShipFrom;
	}

	public void setShipFrom(String shipFrom) {
		ShipFrom = shipFrom;
	}

	public String getShipTo() {
		return ShipTo;
	}

	public void setShipTo(String shipTo) {
		ShipTo = shipTo;
	}

	public String getPrimaryMode() {
		return PrimaryMode;
	}

	public void setPrimaryMode(String primaryMode) {
		PrimaryMode = primaryMode;
	}

	public String getIncoTerms() {
		return IncoTerms;
	}

	public void setIncoTerms(String incoTerms) {
		IncoTerms = incoTerms;
	}

	public String getStandradDuration() {
		return StandradDuration;
	}

	public void setStandradDuration(String standradDuration) {
		StandradDuration = standradDuration;
	}

	public String getRouteStatus() {
		return RouteStatus;
	}

	public void setRouteStatus(String routeStatus) {
		RouteStatus = routeStatus;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public List<Default_Business_Partners_And_Events_Dto> getDefault_Business_Partners_And_Events() {
		return Default_Business_Partners_And_Events;
	}

	public String getCustRouteId() {
		return CustRouteId;
	}

	public void setCustRouteId(String custRouteId) {
		CustRouteId = custRouteId;
	}

	public void setDefault_Business_Partners_And_Events(
			List<Default_Business_Partners_And_Events_Dto> default_Business_Partners_And_Events) {
		Default_Business_Partners_And_Events = default_Business_Partners_And_Events;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDocuments() {
		return Documents;
	}

	public void setDocuments(String documents) {
		Documents = documents;
	}

}
