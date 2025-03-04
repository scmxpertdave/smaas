package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.document.Shipments;

public interface ShipmentsRepository extends MongoRepository<Shipments, String>, ShipmentDetailsCustom {

	@Query("{Customer_Id:'?0'}")
	Shipments findByCustomer_Id(String Customer_Id);

	@Query("{Shipment_Id:'?0'}")
	Shipments findByShipment_Id(String Shipment_Id);

	@Query("{Device_Id:'?0'}")
	List<Shipments> findByDevice_Id(String Device_Id);
	
	@Query("{Shipment_Id:'?0'}")
	List<Shipments> getShipmentsList(String Shipment_Id);
		
	@Query("{BP_Id:'?0'}")
	List<Shipments> findByBP_Id(String BP_Id);
	
	@Query("{Customer_Id:'?0'}")
	List<Shipments> findByCustomerId(String Customer_Id);
	
	@Query("{Customer_Id:'?0','Shipment_Status':'?1'}")
	List<Shipments> findByCustomerIdShipments(String Customer_Id,String shipment_status);
	
	@Query("{Customer_Id:'?0'}")
	List<Shipments> findByCustomer_id(String Customer_Id);
	
	@Query("{Invoice_Number:'?0'}")
	List<Shipments> findByInvoice_Number(String invoice_Number);
	
	@Query("{Shipment_Id:'?0'}")
	List<Shipments> findByShipmentId(String Shipment_Id);
	
	@Query("{Shipment_Num:'?0'}")
	List<Shipments> findByShipment_Num(String shipment_Num);
	
	@Query("{ 'Shipment_Status' : { $ne: 'Delivered' } }")
    List<Shipments> findShipmentsNotDelivered();
	
//	@Query("{ 'Shipment_Status' : 'Delivered' }")
//    List<Shipments> findDeliveredShipments();
		
}

/*
 * Shipments findBycustomerId(String customerId);
 * 
 * List<Shipments> findBycustomerIdLike(String customerId);
 * 
 * List<Employee> findByHireDateGreaterThan(Date hireDate);
 * 
 * // Supports native JSON query string
 * 
 * @Query("{Customer_Id:'?0'}") List<Shipments> findCustomByRouteID(String
 * RouteID);
 */
