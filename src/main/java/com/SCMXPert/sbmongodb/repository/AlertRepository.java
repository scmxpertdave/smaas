package com.SCMXPert.sbmongodb.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import com.SCMXPert.sbmongodb.document.AlertMaster;
import com.SCMXPert.sbmongodb.document.BusinessPartner;
import com.SCMXPert.sbmongodb.document.Customer;

public interface AlertRepository extends MongoRepository<AlertMaster, String> {
	
	
	@Query("{Customer_Id:'?0'}")
	List<AlertMaster> findByCustomer_Id(String Customer_Id);
	
	@Query("{Alert_Id:'?0'}")
	List<AlertMaster> findByAlert_id(String Alert_Id);
	
	@Query("{Customer_Id:'?0'},{Alert_Id:'?0'}")
	List<AlertMaster> findByCustomer_IdAlert_Id(String Customer_Id,String Alert_Id);

}
