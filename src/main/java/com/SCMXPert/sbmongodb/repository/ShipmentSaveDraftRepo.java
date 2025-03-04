package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.ShipmentDraftDto;

public interface ShipmentSaveDraftRepo extends MongoRepository<ShipmentDraftDto, String> {

	@Query("{Internal_Shipment_Id:'?0'}")
	ShipmentDraftDto findByInternal_Shipment_id(String Internal_Shipment_Id);
	
	@Query("{Customer_Id:'?0'}")
	List<ShipmentDraftDto> findByCustomer_Id(String Customer_Id);

}
