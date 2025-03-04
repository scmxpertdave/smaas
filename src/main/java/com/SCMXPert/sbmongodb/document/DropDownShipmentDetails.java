package com.SCMXPert.sbmongodb.document;

import java.util.List;
import java.util.Set;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class DropDownShipmentDetails {

	private String[] TypeOfReferences;
	private List<Route> Route;
	private List<Goods> Goods;
	private List<String> Device_Id;
	private String[] Business_Partner_Id;
	private List<CustomBP> BussinesPartnersDetails;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String InternalShipmentId;
	private List<BPList> BusinessPartner;
	private String[] EvidenceFor;
	
	private List<String> plant;

	public List<String> getPlant() {
		return plant;
	}

	public void setPlant(List<String> plant) {
		this.plant = plant;
	}

	public void setInternalShipmentId(String internalShipmentId) {
		InternalShipmentId = internalShipmentId;
	}

	public String getInternalShipmentId() {
		return InternalShipmentId;
	}

	public void setBussinesPartnersDetails(List<CustomBP> bussinesPartnersDetails) {
		BussinesPartnersDetails = bussinesPartnersDetails;
	}

	public List<CustomBP> getBussinesPartnersDetails() {
		return BussinesPartnersDetails;
	}

	public String[] getTypeOfReferences() {
		return TypeOfReferences;
	}

	public void setTypeOfReferences(String[] typeOfReferences) {
		TypeOfReferences = typeOfReferences;
	}

	public List<Route> getRoute() {
		return Route;
	}

	public void setRoute(List<Route> route) {
		Route = route;
	}

	public List<Goods> getGoods() {
		return Goods;
	}

	public void setGoods(List<Goods> goods) {
		Goods = goods;
	}

	public List<String> getDevice_Id() {
		return Device_Id;
	}

	public void setDevice_Id(List<String> deviceIDs) {
		Device_Id = deviceIDs;
	}

	public void setBusiness_Partner_Id(String[] business_Partner_Id) {
		Business_Partner_Id = business_Partner_Id;
	}

	public String[] getBusiness_Partner_Id() {
		return Business_Partner_Id;
	}

	public List<BPList> getBusinessPartner() {
		return BusinessPartner;
	}

	public void setBusinessPartner(List<BPList> businessPartner) {
		BusinessPartner = businessPartner;
	}

	public String[] getEvidenceFor() {
		return EvidenceFor;
	}

	public void setEvidenceFor(String[] evidenceFor) {
		EvidenceFor = evidenceFor;
	}

}
