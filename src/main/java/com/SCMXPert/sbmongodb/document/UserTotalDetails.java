package com.SCMXPert.sbmongodb.document;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Users")
public class UserTotalDetails {
	@Id
	private String id;
	//@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	

  
	private String UserId;
	private String Customer_Id;
	private String UserName;
	//private String Email;
	private String Email;
	private String AdminEmail;
	private String Phone;
	
	private String Admin_Name;
	private String AdminCellPhoneNumber;
	private String Customer_Name;
		public String getUserid() {
		return UserId;
	}
	public void setUserid(String userid) {
		this.UserId = userid;
	}
	private List<Roles> roles;
	public List<Roles> getRoles() {
		return roles;
	}
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	public String getCustomer_Id() {
		return Customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}

	/*
	 * public String getEmail() { return Email; } public void setEmail(String email)
	 * { Email = email; }
	 */
	public String getPhone() {
		return Phone;
	}
	public String getEmail() {
		return Email;
	}
	public String getAdmin_Name() {
		return Admin_Name;
	}
	public void setAdmin_Name(String admin_Name) {
		Admin_Name = admin_Name;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAdminEmail() {
		return AdminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		AdminEmail = adminEmail;
	}
	public String getAdminCellPhoneNumber() {
		return AdminCellPhoneNumber;
	}
	public void setAdminCellPhoneNumber(String adminCellPhoneNumber) {
		AdminCellPhoneNumber = adminCellPhoneNumber;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}
	@Override
	public String toString() {
		return "Userdetails [Customer_Id=" + Customer_Id + ",UserName="+UserName+",Customer_Name="+Customer_Name+",AdminEmail="+AdminEmail+",Roles="+roles+"]";
	}
}
