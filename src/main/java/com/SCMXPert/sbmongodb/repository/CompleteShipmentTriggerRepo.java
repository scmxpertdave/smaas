package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.CompleteShipmentTrigger;
import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.document.Shipments;

public interface CompleteShipmentTriggerRepo extends MongoRepository<CompleteShipmentTrigger, String>{
	
	
	@Query("{shipmentId:'?0'}")
	CompleteShipmentTrigger findByshipmentId(String shipmentId);
	
	@Query("{shipmentId:'?0'},{status:'?1'}")
	List <CompleteShipmentTrigger> findByshipmentIdandStatus(String shipmentId, String status);
	
	@Query("{status:'?0'}")
	List <CompleteShipmentTrigger> findByStatus(String status);
	

	//Refer Shipments Repo

	

}
