/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SCMXPert.sbmongodb.document.Role;
import com.SCMXPert.sbmongodb.document.Roles;


public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
	
	Role findByRole(String[] rolevalue);
}
