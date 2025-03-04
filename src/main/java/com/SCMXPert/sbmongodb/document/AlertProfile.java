package com.SCMXPert.sbmongodb.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import jakarta.validation.constraints.Pattern;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Pattern;
@Document(collection = "AlertProfile")
public class AlertProfile {
	
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Alert_Profile")
private String Alert_Profile;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Sequence")
private String Sequence;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Profile_Desc")
private String Profile_Desc;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Route_Id")
private String Route_Id;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Goods_Type")
private String Goods_Type;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Event_Name")
private String Event_Name;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Alert_Type")
private String Alert_Type;

//@Field(value = "Buff")
//private String Buff;

@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Geo_Fence")
private String Geo_Fence;

@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Alert_Id")
private String Alert_Id;

@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Customer_Id")
private String Customer_Id;

@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Customer_Name")
private String Customer_Name;

@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")@Field(value = "Partner_Name")
private String Partner_Name;

public String getAlert_Profile() {
	return Alert_Profile;
}

public void setAlert_Profile(String alert_Profile) {
	Alert_Profile = alert_Profile;
}

public String getSequence() {
	return Sequence;
}

public void setSequence(String sequence) {
	Sequence = sequence;
}

public String getProfile_Desc() {
	return Profile_Desc;
}

public void setProfile_Desc(String profile_Desc) {
	Profile_Desc = profile_Desc;
}

public String getRoute_Id() {
	return Route_Id;
}

public void setRoute_Id(String route_Id) {
	Route_Id = route_Id;
}

public String getGoods_Type() {
	return Goods_Type;
}

public void setGoods_Type(String goods_Type) {
	Goods_Type = goods_Type;
}



public String getEvent_Name() {
	return Event_Name;
}

public void setEvent_Name(String event_Name) {
	Event_Name = event_Name;
}

public String getAlert_Type() {
	return Alert_Type;
}

public void setAlert_Type(String alert_Type) {
	Alert_Type = alert_Type;
}

//public String getBuff() {
//	return Buff;
//}
//
//public void setBuff(String buff) {
//	Buff = buff;
//}

public String getGeo_Fence() {
	return Geo_Fence;
}

public void setGeo_Fence(String geo_Fence) {
	Geo_Fence = geo_Fence;
}

public String getAlert_Id() {
	return Alert_Id;
}

public void setAlert_Id(String alert_Id) {
	Alert_Id = alert_Id;
}

public String getCustomer_Id() {
	return Customer_Id;
}

public void setCustomer_Id(String customer_Id) {
	Customer_Id = customer_Id;
}

public String getCustomer_Name() {
	return Customer_Name;
}

public void setCustomer_Name(String customer_Name) {
	Customer_Name = customer_Name;
}

public String getPartner_Name() {
	return Partner_Name;
}

public void setPartner_Name(String partner_Name) {
	Partner_Name = partner_Name;
}


}
