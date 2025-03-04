package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.DeviceTransferTracker;

public interface DeviceTransferTrackerRepo extends MongoRepository<DeviceTransferTracker, String> {

	@Query("{Device_Id:'?0'}")
	DeviceTransferTracker findByDevice_Id(String Device_Id);
}
