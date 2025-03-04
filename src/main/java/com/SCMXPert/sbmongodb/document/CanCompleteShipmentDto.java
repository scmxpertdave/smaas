package com.SCMXPert.sbmongodb.document;

public class CanCompleteShipmentDto {

	private boolean canComplete;
	//private String Bpid;
	
	
	/*public void setBpid(String bpid) {
		Bpid = bpid;
	}
	
	public String getBpid() {
		return Bpid;
	}*/
	
	public boolean isCanComplete() {
		return canComplete;
	}
	public void setCanComplete(boolean canComplete) {
		this.canComplete = canComplete;
	}
	
	
	

}
