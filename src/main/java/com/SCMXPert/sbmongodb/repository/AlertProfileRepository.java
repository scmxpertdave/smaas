package com.SCMXPert.sbmongodb.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.AlertMaster;
import com.SCMXPert.sbmongodb.document.AlertProfile;

public interface AlertProfileRepository extends MongoRepository<AlertProfile, String> {

	
	@Query("{Customer_Id:'?0'}")
	List<AlertProfile> findByCustomer_Id(String Customer_Id);
	
}
