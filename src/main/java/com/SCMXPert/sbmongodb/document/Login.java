package com.SCMXPert.sbmongodb.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.ResponseBody;

@Document(collection = "Login")
public class Login {
	
	@Id
	private ObjectId id;
	private String email;
	private String  pwd;
	private int customer_id;
	private String user_type;	

	public Login(String email, String pwd, int customer_id, String user_type) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.customer_id = customer_id;
		this.user_type = user_type;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", pwd=" + pwd + ", customer_id=" + customer_id + ", user_type=" + user_type
				+ "]";
	}

	
}
