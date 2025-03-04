package com.SCMXPert.sbmongodb.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Roles {

	@DBRef List<Role> Role;
	public List<Role> getRole() {
		return Role;
	}
	public void setRole(List<Role> role) {
		Role = role;
	}
	private String ref;

	private String id;
	public String getRef() {
		return ref;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
}
