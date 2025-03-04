package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.Events;

public interface BusinessPartnerRepository extends MongoRepository<BusinessPartner, String> {

	@Query("{BP_Id:'?0'}")
	BusinessPartner findByBP_Id(String BP_Id);
	
	
	@Query("{Customer_Id:'?0'}")
	List<BusinessPartner> findByCustomer_Id(String Customer_id);
	
	/*
	 * @Query("{BP_Id:'?0'}") String findByCompany_Name(String BP_Id);
	 * 
	 * @Query("{BP_Id:'?0'}") List<Events> findByEvents(String BP_Id);
	 */

}

/*
 * BusinessPartner findByBPId(String BPId);
 * 
 * List<BusinessPartner> findByBPIdLike(String BPId);
 * 
 * List<Employee> findByHireDateGreaterThan(Date hireDate);
 * 
 * // Supports native JSON query string
 * 
 * @Query("{BPId:'?0'}") List<Devices> findCustomByBPId(String BPIds);
 */