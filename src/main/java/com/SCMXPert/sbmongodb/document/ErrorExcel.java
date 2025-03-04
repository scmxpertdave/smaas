package com.SCMXPert.sbmongodb.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ErrorExcel")
public class ErrorExcel {
	
	@Id
	private ObjectId id;
	
	private String delivery_number;
	@Override
	public String toString() {
		return "ErrorExcel [id=" + id + ", delivery_number=" + delivery_number + ", invoice_number=" + invoice_number
				+ ", device_Id=" + device_Id + ", event_name=" + event_name + ", event_status=" + event_status + "]";
	}
	private String invoice_number;
	private String device_Id;
	private String event_name;
	private String event_status;
	public String getDelivery_number() {
		return delivery_number;
	}
	public void setDelivery_number(String delivery_number) {
		this.delivery_number = delivery_number;
	}
	public String getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	public String getDevice_Id() {
		return device_Id;
	}
	public void setDevice_Id(String device_Id) {
		this.device_Id = device_Id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_status() {
		return event_status;
	}
	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}

}
