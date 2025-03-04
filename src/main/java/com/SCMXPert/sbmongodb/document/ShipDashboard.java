package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;

//import jakarta.validation.constraints.Pattern;
import javax.validation.constraints.Pattern;

public class ShipDashboard {
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String routeFrom ;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String routeTo;
	private String shipdateDate;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String goodsItem;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String deviceId;;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String typePreference;
	private String[] deparments ;
	private String shipDate;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String deliveredStatus;
	//@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String deliveredDate;
	private String[] comments ;
	private String[] device ;
	
	public String getRouteFrom() {
		return routeFrom;
	}
	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}
	public String getRouteTo() {
		return routeTo;
	}
	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}
	public String getShipdateDate() {
		return shipdateDate;
	}
	public void setShipdateDate(String shipdateDate) {
		this.shipdateDate = shipdateDate;
	}
	public String getGoodsItem() {
		return goodsItem;
	}
	public void setGoodsItem(String goodsItem) {
		this.goodsItem = goodsItem;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getTypePreference() {
		return typePreference;
	}
	public void setTypePreference(String typePreference) {
		this.typePreference = typePreference;
	}
	public String[] getDeparments() {
		return deparments;
	}
	public void setDeparments(String[] deparments) {
		this.deparments = deparments;
	}
	public String getShipDate() {
		return shipDate;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public String getDeliveredStatus() {
		return deliveredStatus;
	}
	public void setDeliveredStatus(String deliveredStatus) {
		this.deliveredStatus = deliveredStatus;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public String[] getComments() {
		return comments;
	}
	public void setComments(String[] comments) {
		this.comments = comments;
	}
	public String[] getDevice() {
		return device;
	}
	public void setDevice(String[] device) {
		this.device = device;
	}

	
	@Override
	public String toString() {
		return "ShipDashboard [Route.From=" + routeFrom + ", Route.To=" + routeTo + ", Ship_Date.Date=" + shipdateDate
				+ ", Goods_Item=" + goodsItem + ", Device_Id=" + deviceId + ", Type_of_Preference=" + typePreference
				+ ", Departments=" + Arrays.toString(deparments) + ", Ship_Date=" + shipDate + ", Delivered_Status="
				+ deliveredStatus + ", Delivered_Date=" + deliveredDate + ", Comments=" + Arrays.toString(comments)
				+ ", Device=" + Arrays.toString(device) + "]";
	}

	
	

}
