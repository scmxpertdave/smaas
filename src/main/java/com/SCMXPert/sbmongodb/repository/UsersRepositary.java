package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.SCMXPert.sbmongodb.document.Users;

//@Repository
public interface UsersRepositary extends MongoRepository<Users, String> {

	@Query("{AdminCustomer_Id:'?0'}")
	Users findByCustomer_id(String AdminCustomer_Id);

}
