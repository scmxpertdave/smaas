package com.SCMXPert.sbmongodb.document;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import jakarta.validation.constraints.Pattern;
import javax.validation.constraints.Pattern;

public class AddGood {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9  -]+")
	private String CustomerId;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9  -]+")
	private String Name1;
	@Pattern(message="Invalid Input", regexp = "[a-zA-Z0-9  -]+")
	private String BusinessId; 
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  -]+")
	private String Goods_Id;
	@Pattern(regexp = "^[a-zA-Z+(a-zA-Z0-9-?) ]*$")
	private String Goods_Item; 
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ]+")
	private String GoodsTypeStatus;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TemperatureFrom;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TemperatureTo;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String HumidityFrom;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String HumidityTo;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String ShockFrom;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String ShockTo;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TiltFrom;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TiltTo;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String SmellFrom;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String SmellTo;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Description;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TempUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[%]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String HumiUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  /]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String ShockUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String TiltUnits;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z ]+")
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String SmellUnits;
	@Pattern(message="Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String CustGoodId;
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
	
	public String getGoods_Id() {
		return Goods_Id;
	}
	public void setGoods_Id(String goods_Id) {
		Goods_Id = goods_Id;
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getName1() {
		return Name1;
	}
	public void setName1(String name1) {
		Name1 = name1;
	}
	public String getBusinessId() {
		return BusinessId;
	}
	public void setBusinessId(String businessId) {
		BusinessId = businessId;
	}
	public String getGoods_Item() {
		return Goods_Item;
	}
	public void setGoods_Item(String goods_Item) {
		Goods_Item = goods_Item;
	}
	public String getGoodsTypeStatus() {
		return GoodsTypeStatus;
	}
	public void setGoodsTypeStatus(String goodsTypeStatus) {
		GoodsTypeStatus = goodsTypeStatus;
	}
	public String getTemperatureFrom() {
		return TemperatureFrom;
	}
	public void setTemperatureFrom(String temperatureFrom) {
		TemperatureFrom = temperatureFrom;
	}
	public String getTemperatureTo() {
		return TemperatureTo;
	}
	public void setTemperatureTo(String temperatureTo) {
		TemperatureTo = temperatureTo;
	}
	public String getHumidityFrom() {
		return HumidityFrom;
	}
	public void setHumidityFrom(String humidityFrom) {
		HumidityFrom = humidityFrom;
	}
	public String getHumidityTo() {
		return HumidityTo;
	}
	public void setHumidityTo(String humidityTo) {
		HumidityTo = humidityTo;
	}
	public String getShockFrom() {
		return ShockFrom;
	}
	public void setShockFrom(String shockFrom) {
		ShockFrom = shockFrom;
	}
	public String getShockTo() {
		return ShockTo;
	}
	public void setShockTo(String shockTo) {
		ShockTo = shockTo;
	}
	public String getTiltFrom() {
		return TiltFrom;
	}
	public void setTiltFrom(String tiltFrom) {
		TiltFrom = tiltFrom;
	}
	public String getTiltTo() {
		return TiltTo;
	}
	public void setTiltTo(String tiltTo) {
		TiltTo = tiltTo;
	}
	public String getSmellFrom() {
		return SmellFrom;
	}
	public void setSmellFrom(String smellFrom) {
		SmellFrom = smellFrom;
	}
	public String getSmellTo() {
		return SmellTo;
	}
	public void setSmellTo(String smellTo) {
		SmellTo = smellTo;
	}
	public String getCustGoodId() {
		return CustGoodId;
	}
	public void setCustGoodId(String custGoodId) {
		CustGoodId = custGoodId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
