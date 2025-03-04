package com.SCMXPert.sbmongodb.document;

import java.util.HashMap;
import java.util.List;

public class AlertDropdown {

	private List<String> alertId;

	private List<String> alertType;
	
	private List<String> goodsId;

	private List<String> routeId;

	public List<String> getEventId() {
		return eventId;
	}

	public List<String> getRouteId() {
		return routeId;
	}

	public void setRouteId(List<String> routeId) {
		this.routeId = routeId;
	}

	public void setEventId(List<String> eventId) {
		this.eventId = eventId;
	}

	private List<String> goodsType;
	private List<String> eventId;

	public List<String> getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(List<String> goodsType) {
		this.goodsType = goodsType;
	}

	public List<String> getAlertId() {
		return alertId;
	}

	public void setAlertId(List<String> alertId) {
		this.alertId = alertId;
	}

	public List<String> getAlertType() {
		return alertType;
	}

	public void setAlertType(List<String> alertType) {
		this.alertType = alertType;
	}

	

	public List<String> getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(List<String> goodsId) {
		this.goodsId = goodsId;
	}

}
