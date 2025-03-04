package com.SCMXPert.sbmongodb.document;

public class IncrementedRouteid {
	private String Incrementedid;
	private boolean status;

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean b) {
		this.status = b;
	}

	public String getIncrementedid() {
		return Incrementedid;
	}

	public void setIncrementedid(String incrementedid) {
		Incrementedid = incrementedid;
	}

}
