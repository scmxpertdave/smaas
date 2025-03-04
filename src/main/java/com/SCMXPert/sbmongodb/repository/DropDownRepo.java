package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.DropDown;
import com.SCMXPert.sbmongodb.document.DropDownDto;

public interface DropDownRepo extends MongoRepository<DropDown, String> {
	
	@Query("{Dp_Id:'?0'}")
	DropDownDto findByDp_id(String Dp_Id);
}
