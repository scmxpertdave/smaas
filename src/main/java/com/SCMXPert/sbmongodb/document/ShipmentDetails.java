package com.SCMXPert.sbmongodb.document;

import java.util.List;
import javax.validation.constraints.Pattern;

//import jakarta.validation.constraints.Pattern;

public class ShipmentDetails {

	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
    private String Customer_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")	
	private String Created_By;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")	
	private String Shipment_Id;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")	
	private String Shipment_Num;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")	
	private String Route_From;
	@Pattern(message="Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")	
	private String Route_To;
	private String Created_Date;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Device_Id;
	private String Estimated_Delivery_Date;
	private String Delivered_Date;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Delivered_Status;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Shipment_Status;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private float Event_Status;
	private List<String[]> wayPoints;
	@Pattern(message = "Invalid Input", regexp = "^|.[a-zA-Z+(a-zA-Z0-9 -?)]*$")
	private String Goods_Desc;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Type_Of_Reference;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9 ,:-]*+")
	private String Departments;
	@Pattern(message = "Invalid Input", regexp = "|.[a-zA-Z0-9  _.,-]+")
	private String Temp;

	private String[] Comments;
	private String[] Event_Description;
	
//		private String PO_Number;
//		private String NDC_Number;
//		private String Invoice_Number;
//		private String Shipper_Number;
//		private String Serial_Number_of_goods;
//		
		
		public String getDepartments() {
			return Departments;
		}
		public void setDepartments(String departments) {
			Departments = departments;
		}
		public String getType_Of_Reference() {
			return Type_Of_Reference;
		}
		public void setType_Of_Reference(String type_Of_Reference) {
			Type_Of_Reference = type_Of_Reference;
		}
		public String getGoods_Desc() {
			return Goods_Desc;
		}
		public void setGoods_Desc(String goods_Desc) {
			Goods_Desc = goods_Desc;
		}
		public List<String[]> getWayPoints() {
			return wayPoints;
		}
		public void setWayPoints(List<String[]> wayPoints) {
			this.wayPoints = wayPoints;
		}
		public float getEvent_Status() {
			return Event_Status;
		}
		public void setEvent_Status(float event_Status) {
			Event_Status = event_Status;
		}
		public String getCustomer_Id() {
			return Customer_Id;
		}
		public void setCustomer_Id(String customer_Id) {
			Customer_Id = customer_Id;
		}
		public String getCreated_By() {
			return Created_By;
		}
		public void setCreated_By(String created_By) {
			Created_By = created_By;
		}
		public String getShipment_Id() {
			return Shipment_Id;
		}
		public void setShipment_Id(String shipment_Id) {
			Shipment_Id = shipment_Id;
		}
		public String getShipment_Num() {
			return Shipment_Num;
		}
		public void setShipment_Num(String shipment_Num) {
			Shipment_Num = shipment_Num;
		}
		public String getRoute_From() {
			return Route_From;
		}
		public void setRoute_From(String route_From) {
			Route_From = route_From;
		}
		public String getRoute_To() {
			return Route_To;
		}
		public void setRoute_To(String route_To) {
			Route_To = route_To;
		}
		public String getCreated_Date() {
			return Created_Date;
		}
		public void setCreated_Date(String created_Date) {
			Created_Date = created_Date;
		}
		public String getDevice_Id() {
			return Device_Id;
		}
		public void setDevice_Id(String device_Id) {
			Device_Id = device_Id;
		}
		public String getEstimated_Delivery_Date() {
			return Estimated_Delivery_Date;
		}
		public void setEstimated_Delivery_Date(String estimated_Delivery_Date) {
			Estimated_Delivery_Date = estimated_Delivery_Date;
		}
		public String getDelivered_Date() {
			return Delivered_Date;
		}
		public void setDelivered_Date(String delivered_Date) {
			Delivered_Date = delivered_Date;
		}
		public String getDelivered_Status() {
			return Delivered_Status;
		}
		public void setDelivered_Status(String delivered_Status) {
			Delivered_Status = delivered_Status;
		}
		public String getTemp() {
			return Temp;
		}
		public void setTemp(String temp) {
			Temp = temp;
		}
		public String getShipment_Status() {
			return Shipment_Status;
		}
		public void setShipment_Status(String shipment_Status) {
			Shipment_Status = shipment_Status;
		}
		public String[] getComments() {
			return Comments;
		}
		public void setComments(String[] comments) {
			Comments = comments;
		}
		public String[] getEvent_Description() {
			return Event_Description;
		}
		public void setEvent_Description(String[] event_Description) {
			Event_Description = event_Description;
		}

		
		
		

	

}
