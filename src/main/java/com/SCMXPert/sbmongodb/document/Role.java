
package com.SCMXPert.sbmongodb.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "role")
public class Role {

	@Id
	private String id;
	//@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	@Indexed(unique = true, direction = IndexDirection.DESCENDING)

	private String role;
	private String[] rolePermissions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRolePermissions(String[] rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public String[] getRolePermissions() {
		return rolePermissions;
	}

}