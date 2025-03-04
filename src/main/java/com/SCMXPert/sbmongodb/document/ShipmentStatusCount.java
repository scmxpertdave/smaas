package com.SCMXPert.sbmongodb.document;

public class ShipmentStatusCount 
{
	private Long deliveredCount;
    private Long notDeliveredCount;
    
    public ShipmentStatusCount(Long deliveredCount, Long notDeliveredCount) {
    	this.deliveredCount = deliveredCount;
    	this.notDeliveredCount = notDeliveredCount;
    }
    
    public ShipmentStatusCount() {
    	
    }
    
	public Long getDeliveredCount() {
		return deliveredCount;
	}
	public void setDeliveredCount(long deliveredCount) {
		this.deliveredCount = deliveredCount;
	}
	public Long getNotDeliveredCount() {
		return notDeliveredCount;
	}
	public void setNotDeliveredCount(long notDeliveredCount) {
		this.notDeliveredCount = notDeliveredCount;
	}
}