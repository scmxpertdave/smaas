package com.SCMXPert.sbmongodb.document;

import java.util.List;

public class DeviceDropDown {
 
 private List<String> Device_Id;
 private String[] TypeOfReferences;
 
 private List<String> plant;

 public List<String> getDevice_Id() {
  return Device_Id;
 }

 public void setDevice_Id(List<String> device_Id) {
  Device_Id = device_Id;
 }

public String[] getTypeOfReferences() {
	return TypeOfReferences;
}

public void setTypeOfReferences(String[] typeOfReferences) {
	TypeOfReferences = typeOfReferences;
}

public List<String> getPlant() {
	return plant;
}

public void setPlant(List<String> plant) {
	this.plant = plant;
}

}