package com.SCMXPert.sbmongodb.document;

//import jakarta.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class BPList {
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
private String BP_Id;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
private String BP_Name;
@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
private String BP_Company;
public String getBP_Id() {
	return BP_Id;
}
public void setBP_Id(String bP_Id) {
	BP_Id = bP_Id;
}
public String getBP_Name() {
	return BP_Name;
}
public void setBP_Name(String bP_Name) {
	BP_Name = bP_Name;
}
public String getBP_Company() {
	return BP_Company;
}
public void setBP_Company(String bP_Company) {
	BP_Company = bP_Company;
}
}
