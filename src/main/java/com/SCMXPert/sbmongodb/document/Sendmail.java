package com.SCMXPert.sbmongodb.document;

public class Sendmail {

	
	
	private String[] to;
	private String shipment_id;
	private String Subject;
	private String Content;
	
	
	
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	
	public String[] getTo() {
		return to;
	}
	public String getShipment_id() {
		return shipment_id;
	}
	public void setShipment_id(String shipment_id) {
		this.shipment_id = shipment_id;
	}
}
