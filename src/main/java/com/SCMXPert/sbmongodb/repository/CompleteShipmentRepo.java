package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.CopyAddEvent;
import com.SCMXPert.sbmongodb.document.CreateShipment;

public interface CompleteShipmentRepo extends MongoRepository<CreateShipment, String> {

	
	@Query("{Shipment_Number:'?0'}")
	CreateShipment findByShipment_number(String Shipment_Number);

	//void insert(CopyAddEvent addeventdraft);
}
