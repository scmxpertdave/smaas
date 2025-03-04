package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.SCMXPert.sbmongodb.document.ShipmentTransactions;

@Transactional
public interface ShipmentTransactionsRepository
		extends MongoRepository<ShipmentTransactions, String>, ShipmentDetailsCustom {

	@Query("{Customer_Id:'?0'}")
	List<ShipmentTransactions> findByCustomer_Id(String customer_Id);

	@Query("{Customer_Id:'?0'}")
	ShipmentTransactions findBySingleCustomer_Id(String customer_Id);

	@Query("{Shipment_Id:'?0'}")
	List<ShipmentTransactions> findByShipment_Id(String Shipment_Id);
	
	@Query("{Invoice_Number:'?0'}")
	List<ShipmentTransactions> findByInvoice_Number(String invoice_Number);
/*
	@Query("{Event_SNo:'?0'}")
	ShipmentTransactions findByEvent_Sno(String Event_SNo);*/
	
	@Query("{Partner_From:'?0'}")
	List<ShipmentTransactions> findByPartner_from(String Partner_From);
	
//	@Query("{Event_SNo:'?0'}")
//	ShipmentTransactions findByEvent_Sno(String Event_SNo);
	
	@Query("{Event_SNo:?0}")
	ShipmentTransactions findByEvent_Sno(long eventslno);
	
	@Query("{Shipment_Num:'?0'}")
	List<ShipmentTransactions> findByShipment_Num(String shipment_Num);
	
	@Query("{DocId:'?0'}")
	List<ShipmentTransactions> findByDocsId(String DocId);
}

/*
 * ShipmentTransactions findByshipmentId(String shipmentId);
 * 
 * List<ShipmentTransactions> findByshipmentIdLike(String shipmentId);
 * 
 * List<Employee> findByHireDateGreaterThan(Date hireDate);
 * 
 * // Supports native JSON query string
 * 
 * @Query("{ShipmentId:'?0'}") List<Devices> findCustomByShipmentId(String
 * RouteID);
 */