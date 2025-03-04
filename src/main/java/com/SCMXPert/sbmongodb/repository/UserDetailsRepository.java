package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.SCMXPert.sbmongodb.document.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

	@Query("{userid:'?0'}")
	UserDetails findByUserid(String userid);

	/*
	 * @Query("{BP_Id:'?0'}") String findByCompany_Name(String BP_Id);
	 * 
	 * @Query("{BP_Id:'?0'}") List<Events> findByEvents(String BP_Id);
	 */

}