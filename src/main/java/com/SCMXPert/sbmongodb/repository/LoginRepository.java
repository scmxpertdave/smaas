package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.Login;

public interface LoginRepository extends MongoRepository<Login,Integer>  {
	@Query("{customer_id:?0}")
	List<Login> findBycustomer_id(int customer_id);
	@Query("{email:'?0'}")
	List<Login> findByemail(String email);
	@Query("{customer_id:?0}")
	Login findBycustomer_id1(int customer_id);


}
