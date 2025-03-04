package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.DeviceDataStream;

public interface DeviceDataStreamRepository extends MongoRepository<DeviceDataStream, String>,ShipmentDetailsCustom {

	@Query("{Modem_IMEI:'?0'}")
	DeviceDataStream findByModem_IMEI(String imeiNumber);
	
	@Query("{Device_Id:'?0'}")
	List<DeviceDataStream> findByDevice_id(String Device_Id);
		
	@Query("{Device_Id:'?0'},{Message_Type:'?1'}")
	List<DeviceDataStream> findByDevice_idandMessageType(String Device_Id,String messageType);
	
	//@Query("{Device_Id:'?0',Created_Date:{$gt'?1'}}")
	//List<DeviceDataStream> getDeviceDataStream(String Device_Id,String UTC);
	
	 @Query("{SensorUTC:'?0'}")
	 List<DeviceDataStream> findAllTimestampsByUtcBetween(String startDateTime, String endDateTime);

}
/*
 * 
 * 
 * DeviceDataStream findBysensorid(String sensorid);
 * 
 * List<DeviceDataStream> findBysensoridLike(String sensorid);
 * 
 * List<Employee> findByHireDateGreaterThan(Date hireDate);
 * 
 * // Supports native JSON query string
 * 
 * @Query("{sensorid:'?0'}") List<DeviceDataStream> findCustomBysensorid(String
 * RouteID);
 */