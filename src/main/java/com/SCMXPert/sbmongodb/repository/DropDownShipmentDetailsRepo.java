package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.DropDownShipmentDetails;

public interface DropDownShipmentDetailsRepo extends MongoRepository<Customer, String> {
	
	@Query("{Customer_Id:'?0'}")
	DropDownShipmentDetails findByCustomer_id(String Customer_Id);
}
