package com.SCMXPert.sbmongodb.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Pattern;

public class Goods {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Goods_Id;
	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Goods_Item;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	private String[] Control_Params;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String Goods_Status;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Description;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Temperature_From;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Temperature_To;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Humidity_From;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Humidity_To;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Shock_From;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Shock_To;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Tilt_From;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Tilt_To;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Smell_From;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Smell_To;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String CustGoodId;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TempUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[%]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String HumiUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  /]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String ShockUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TiltUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String SmellUnits;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String SensorSelect;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String PressureSelect;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String HazardousSelect;
	//@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9 ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String EvidenceSelect;
	
	public String getGoods_Status() {
		return Goods_Status;
	}
	public void setGoods_Status(String goods_Status) {
		Goods_Status = goods_Status;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getTemperature_From() {
		return Temperature_From;
	}
	public void setTemperature_From(String temperature_From) {
		Temperature_From = temperature_From;
	}
	public String getTemperature_To() {
		return Temperature_To;
	}
	public void setTemperature_To(String temperature_To) {
		Temperature_To = temperature_To;
	}
	public String getHumidity_From() {
		return Humidity_From;
	}
	public void setHumidity_From(String humidity_From) {
		Humidity_From = humidity_From;
	}
	public String getHumidity_To() {
		return Humidity_To;
	}
	public void setHumidity_To(String humidity_To) {
		Humidity_To = humidity_To;
	}
	public String getShock_From() {
		return Shock_From;
	}
	public void setShock_From(String shock_From) {
		Shock_From = shock_From;
	}
	public String getShock_To() {
		return Shock_To;
	}
	public void setShock_To(String shock_To) {
		Shock_To = shock_To;
	}
	public String getTilt_From() {
		return Tilt_From;
	}
	public void setTilt_From(String tilt_From) {
		Tilt_From = tilt_From;
	}
	public String getTilt_To() {
		return Tilt_To;
	}
	public void setTilt_To(String tilt_To) {
		Tilt_To = tilt_To;
	}
	public String getSmell_From() {
		return Smell_From;
	}
	public void setSmell_From(String smell_From) {
		Smell_From = smell_From;
	}
	public String getSmell_To() {
		return Smell_To;
	}
	public void setSmell_To(String smell_To) {
		Smell_To = smell_To;
	}
	public String getGoods_Id() {
		return Goods_Id;
	}
	public void setGoods_Id(String goods_Id) {
		Goods_Id = goods_Id;
	}
	public String getGoods_Item() {
		return Goods_Item;
	}
	public void setGoods_Item(String goods_Item) {
		Goods_Item = goods_Item;
	}
	public String[] getControl_Params() {
		return Control_Params;
	}
	public void setControl_Params(String[] control_Params) {
		Control_Params = control_Params;
	}
	public String getCustGoodId() {
		return CustGoodId;
	}
	public void setCustGoodId(String custGoodId) {
		CustGoodId = custGoodId;
	}
	public String getTempUnits() {
		return TempUnits;
	}
	public void setTempUnits(String tempUnits) {
		TempUnits = tempUnits;
	}
	public String getHumiUnits() {
		return HumiUnits;
	}
	public void setHumiUnits(String humiUnits) {
		HumiUnits = humiUnits;
	}
	public String getShockUnits() {
		return ShockUnits;
	}
	public void setShockUnits(String shockUnits) {
		ShockUnits = shockUnits;
	}
	public String getTiltUnits() {
		return TiltUnits;
	}
	public void setTiltUnits(String tiltUnits) {
		TiltUnits = tiltUnits;
	}
	public String getSmellUnits() {
		return SmellUnits;
	}
	public void setSmellUnits(String smellUnits) {
		SmellUnits = smellUnits;
	}
	public String getSensorSelect() {
		return SensorSelect;
	}
	public void setSensorSelect(String sensorSelect) {
		SensorSelect = sensorSelect;
	}
	public String getPressureSelect() {
		return PressureSelect;
	}
	public void setPressureSelect(String pressureSelect) {
		PressureSelect = pressureSelect;
	}
	public String getHazardousSelect() {
		return HazardousSelect;
	}
	public void setHazardousSelect(String hazardousSelect) {
		HazardousSelect = hazardousSelect;
	}
	public String getEvidenceSelect() {
		return EvidenceSelect;
	}
	public void setEvidenceSelect(String evidenceSelect) {
		EvidenceSelect = evidenceSelect;
	}
	
	
	
	
}
