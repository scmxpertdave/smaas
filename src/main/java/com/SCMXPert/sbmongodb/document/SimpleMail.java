package com.SCMXPert.sbmongodb.document;

import javax.validation.constraints.Pattern;

//import javax.validation.constraints.Pattern;

public class SimpleMail {

	private String[] to;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Content;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Subject;
	
	
	/*
	 * private final String to;
	 * 
	 * public SimpleMail(String to) { this.to = to; }
	 * 
	 * public String getTo() { return this.to; }
	 */

    public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	/*
	 * public String getSubject() { return "ALERT"; }
	 * 
	 * public String getContent() { return
	 * "Hello client,\n This a simple email content !"; }
	 */
}
