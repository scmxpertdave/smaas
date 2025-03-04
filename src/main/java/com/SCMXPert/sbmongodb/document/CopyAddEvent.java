package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.validation.constraints.Pattern;

@Document(collection = "CopyAddEvent")
public class CopyAddEvent {

	@Id
	private ObjectId id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Event_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String BpId;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String EventName;
	private String DateTime;

	public void setEvent_Id(String event_Id) {
		Event_Id = event_Id;
	}

	public String getEvent_Id() {
		return Event_Id;
	}

	public String getBpId() {
		return BpId;
	}

	public void setBpId(String bpId) {
		BpId = bpId;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

}
