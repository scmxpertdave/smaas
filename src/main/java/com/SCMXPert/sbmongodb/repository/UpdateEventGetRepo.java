package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.document.UpdateEventGet;

public interface UpdateEventGetRepo extends MongoRepository<ShipmentTransactions, String> {

	@Query("{'Shipment_Id':?0,'Partner_From':?1}")
	List<ShipmentTransactions> findByShipment_IdAndPartner_From(String Shipment_Id, String Partner_From);
}
