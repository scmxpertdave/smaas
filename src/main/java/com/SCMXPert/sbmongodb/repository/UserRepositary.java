package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.SCMXPert.sbmongodb.document.Users;

//@Repository
public interface UserRepositary extends MongoRepository<Users, String> {

	@Query("{AdminCustomer_Id:'?0'}")
	Users findByCustomer_id(String AdminCustomer_Id);

	@Query("{UserBP_Id:'?0'}")
	Users findByBP_id(String UserBP_Id);
}
