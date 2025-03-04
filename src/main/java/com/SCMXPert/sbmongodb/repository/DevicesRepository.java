package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.Devices;

public interface DevicesRepository extends MongoRepository<Devices, String> {

	@Query("{BP_Id:'?0'}")
	Devices findByBP_Id(String bp_Id);
	
	@Query("{Device_Id:'?0'}")
	Devices findByDevice_Id(String device_Id);

}

/*
 * Devices findByDeviceId(String deviceId);
 * 
 * List<Devices> findByDeviceIdLike(String deviceId);
 * 
 * List<Employee> findByHireDateGreaterThan(Date hireDate);
 * 
 * // Supports native JSON query string
 * 
 * @Query("{Device_Id:'?0'}") List<Devices> findCustomByDeviceId(String
 * RouteID);
 */