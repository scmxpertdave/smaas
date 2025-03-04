package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.SCMXPert.sbmongodb.document.UserTotalDetails;

public interface UserTotalDetailsRepository extends MongoRepository<UserTotalDetails, String> {

	@Query("{UserId:'?0'}")
	UserTotalDetails findByBP_Id(String UserId);

	/*
	 * @Query("{BP_Id:'?0'}") String findByCompany_Name(String BP_Id);
	 * 
	 * @Query("{BP_Id:'?0'}") List<Events> findByEvents(String BP_Id);
	 */

}