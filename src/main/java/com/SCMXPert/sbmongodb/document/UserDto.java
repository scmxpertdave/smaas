package com.SCMXPert.sbmongodb.document;

import java.util.Set;

public class UserDto {
	private String email;
	private String password;
	private String fullname;
	private boolean enabled;
	private String userid;
	private String BP_Id;
	private Set<Role> roles;
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getRole() {
		return role;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBP_Id() {
		return BP_Id;
	}

	public void setBP_Id(String bP_Id) {
		BP_Id = bP_Id;
	}

}
