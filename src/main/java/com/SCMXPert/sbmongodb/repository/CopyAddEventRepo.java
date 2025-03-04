package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import com.SCMXPert.sbmongodb.document.CopyAddEvent;

//@Repository
public interface CopyAddEventRepo extends MongoRepository<CopyAddEvent, String> {

	@Query("{Event_Id:'?0'}")
	CopyAddEvent findByEvent_Id(String Event_Id);
}
