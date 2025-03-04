/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.User;

public interface UserRepository extends MongoRepository<User, String> {

	//@Query("{email:'?0'}")
	User findByEmail(String email);
	
	@Query("{userid:'?0'}")
	User findByUserid(String userid);
	
	@Query("{resettoken:'?0'}")
	User findByresettoken(String resettoken);
	
	
	@Query("{UserBP_Id:'?0'}")
	User findByUserBp_Id(String UserBP_Id);

}
