package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.Customer;
import com.SCMXPert.sbmongodb.document.DeviceDropDown;

public interface DeviceDropDownRepo extends MongoRepository<Customer, String> {
	
	@Query("{Customer_Id:'?0'}")
	DeviceDropDown findByCustomer_id(String Customer_Id);
}

//public class DeviceDropDownRepo {
//
//}