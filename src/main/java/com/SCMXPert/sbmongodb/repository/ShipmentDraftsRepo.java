package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.ShipmentDraftPartialGet;
import com.SCMXPert.sbmongodb.document.ShipmentDrafts;

public interface ShipmentDraftsRepo extends MongoRepository<ShipmentDrafts, String> {

	@Query("{Internal_Shipment_Id:'?0'}")
	ShipmentDraftPartialGet findByInternal_Shipment_id(String Internal_Shipment_Id);
	
	@Query("{Shipment_Number:'?0'}")
	ShipmentDrafts findByShipment_number(String Shipment_Number);
}
