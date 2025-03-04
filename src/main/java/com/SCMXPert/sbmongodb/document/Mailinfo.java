package com.SCMXPert.sbmongodb.document;

public class Mailinfo {
	
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
	
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String getShipment_id() {
		return shipment_id;
	}
	public void setShipment_id(String shipment_id) {
		this.shipment_id = shipment_id;
	}
	
//	private String[] to;
//	private String shipment_id;
//	private String subject;
//	private String content;
//	public String getSubject() {
//		return subject;
//	}
//	public void setSubject(String subject) {
//		subject = subject;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		content = content;
//	}
//	
//	public String[] getTo() {
//		return to;
//	}
//	public void setTo(String[] to) {
//		this.to = to;
//	}
//	public String getShipment_id() {
//		return shipment_id;
//	}
//	public void setShipment_id(String shipment_id) {
//		this.shipment_id = shipment_id;
//	}

}
