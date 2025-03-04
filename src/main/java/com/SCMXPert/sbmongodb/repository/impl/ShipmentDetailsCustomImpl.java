package com.SCMXPert.sbmongodb.repository.impl;

import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.SCMXPert.sbmongodb.document.DeviceData;
import com.SCMXPert.sbmongodb.document.DeviceDataStream;
import com.SCMXPert.sbmongodb.document.ShipmentTransactions;
import com.SCMXPert.sbmongodb.document.Shipments;
import com.SCMXPert.sbmongodb.repository.ShipmentDetailsCustom;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class ShipmentDetailsCustomImpl implements ShipmentDetailsCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Shipments> getShipments(String Customer_id, String Created_By) {
		// TODO Auto-generated method stub
		List<Shipments> sp = new ArrayList<Shipments>();
		
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Customer_Id").is(Customer_id),Criteria.where("Created_By").is(Created_By));
		
		Query query = new Query(crt);
		
		sp = mongoTemplate.find(query, Shipments.class);

		return sp;
	}
	
	@Override
	public List<Shipments> getShipmentsList(String Customer_id) {
		// TODO Auto-generated method stub
		List<Shipments> sp = new ArrayList<Shipments>();
		
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Customer_Id").is(Customer_id));
		
		Query query = new Query(crt);
		
		sp = mongoTemplate.find(query, Shipments.class);

		return sp;
	}

	@Override
	public float event_status(String Shipment_Id) {
		// TODO Auto-generated method stub
		System.out.println("::::: Inside event_status method :::::");
		Query query = new Query();
		query.addCriteria(Criteria.where("Shipment_Id").is(Shipment_Id));
		
		long count = mongoTemplate.count(query, ShipmentTransactions.class);
		
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Shipment_Id").is(Shipment_Id),Criteria.where("Event_Status").regex("^C"));
		
		long completedCount = mongoTemplate.count(new Query(crt), ShipmentTransactions.class);
		
		
		float a = Float.valueOf(count);
		float b = Float.valueOf(completedCount);
		
		//System.out.println(""++""+"");
		
		float value = b/a * 100;
		
		return value;
	}

	@Override
	public List<DeviceDataStream> getDeviceDataStream(String Device_Id, String SensorUTC) {
		// TODO Auto-generated method stub
		
		List<DeviceDataStream> deviceDataStream = new ArrayList<DeviceDataStream>();
		
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").lte(Instant.now().toString()).gte(SensorUTC.trim()));
		
		deviceDataStream = mongoTemplate.find(new Query(crt), DeviceDataStream.class);
		
		//System.out.println("In Implementation class  "+ deviceDataStream.toString());
	
		
		
		return deviceDataStream;
	}

	
	@Override
	public List<String[]> getlatLong(String Device_Id, String SensorUTC) {
		// TODO Auto-generated method stub
		
		List<DeviceDataStream> deviceDataStream = new ArrayList<DeviceDataStream>();
		
		Criteria crt = new Criteria();
		crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").lte(Instant.now().toString()).gte(SensorUTC.trim()));
		
		deviceDataStream = mongoTemplate.find(new Query(crt), DeviceDataStream.class);
		
		List<String[]> latLong = new ArrayList<String[]>();
		
		for(DeviceDataStream dds : deviceDataStream){
			
			String latslongs[] = new String[5];
			latslongs[0] = dds.getLatitude(); 
			latslongs[1] = dds.getLongitude();
			latslongs[2] = dds.getAddress();
			latslongs[3] =dds.getSensorUTC();
			latslongs[4] =dds.getDevice_Id();
			
			
			//if(dds.getLatitude() != "" && dds.getLongitude() != "")
			if(dds.getLatitude() == null || dds.getLatitude().trim().equals("") && dds.getLongitude() == null || dds.getLongitude().trim().equals(""))
			{
				//System.out.println("\n latitude   " +dds.getLatitude()+" \n longitude" +dds.getLongitude());
				//latLong.add(latslongs);	
			}else if(dds.getLatitude() == "0.0" || dds.getLatitude().trim().equals("0.0") && dds.getLongitude() == "0.0" || dds.getLongitude().trim().equals("0.0")){
				//System.out.println("\n latitude   " +dds.getLatitude()+" \n longitude" +dds.getLongitude());
				
			}else{
				latLong.add(latslongs);
			}
			
		
			
		}
	
		
		
		return latLong;
	}
	
	
	
	 
	 
	@Override
	public List<String[]> getlatLang(String Device_Id, String SensorUTC) {
		// TODO Auto-generated method stub
		System.out.println("::::: Inside getlatLang method :::::");
		List<DeviceDataStream> deviceDataStream = new ArrayList<DeviceDataStream>();
		
		Criteria crt = new Criteria();
//		crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").lte(Instant.now().toString()).gte(SensorUTC.trim()));
		crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").gte(SensorUTC.trim()));
		
		deviceDataStream = mongoTemplate.find(new Query(crt), DeviceDataStream.class);
		
		List<String[]> latLang = new ArrayList<String[]>();
		
		for(DeviceDataStream dds : deviceDataStream){
			
			String latslangs[] = new String[2];
			latslangs[0] = dds.getLatitude(); 
			latslangs[1] = dds.getLongitude();
	
			
			
			//if(dds.getLatitude() != "" && dds.getLongitude() != "")
			if(dds.getLatitude() == null || dds.getLatitude().trim().equals("") && dds.getLongitude() == null || dds.getLongitude().trim().equals(""))
			{
				//System.out.println("\n latitude   " +dds.getLatitude()+" \n longitude" +dds.getLongitude());
				//latLong.add(latslongs);	
			}else if(dds.getLatitude() == "0.0" || dds.getLatitude().trim().equals("0.0") && dds.getLongitude() == "0.0" || dds.getLongitude().trim().equals("0.0")){
				//System.out.println("\n latitude   " +dds.getLatitude()+" \n longitude" +dds.getLongitude());
				
			}else{
				latLang.add(latslangs);
			}
			
		
			
		}
	
		
		
		return latLang;
	}
	
//	@Override
//	public DeviceDataStream getDeviceDataStreamSingleDocumentDate(String Device_Id) {
//	// TODO Auto-generated method stub
//
//	DeviceDataStream dds = new DeviceDataStream();
//
//	Criteria crt = new Criteria();
//	crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").lte(Instant.now().toString()));
//
//	dds = mongoTemplate.findOne(new Query(crt), DeviceDataStream.class);
//
//	return dds;
//	}
	
	
	@Override
	public DeviceDataStream getDeviceDataStreamSingleDocumentDate(String Device_Id) {
	// TODO Auto-generated method stub

	DeviceDataStream dds = new DeviceDataStream();

	List<DeviceDataStream> Lastdocdds = new ArrayList<DeviceDataStream>();

	Criteria crt = new Criteria();
	crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").lte(Instant.now().toString()));
	Lastdocdds= mongoTemplate.find(new Query(crt), DeviceDataStream.class);
	DeviceDataStream data1=null;
	for(DeviceDataStream data:Lastdocdds) {

	data1=data;

	}
	return data1;
	}
	
	@Override
	public List<DeviceDataStream> getShipmentTransactionDeviceData(String Device_Id) {
	// TODO Auto-generated method stub

	List<DeviceDataStream> Aldds = new ArrayList<DeviceDataStream>();

	Criteria crt = new Criteria();
	crt.andOperator(Criteria.where("Device_Id").is(Device_Id),Criteria.where("SensorUTC").lte(Instant.now().toString()));

	Aldds = mongoTemplate.find(new Query(crt), DeviceDataStream.class);

	return Aldds;
	
}



public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
{
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
}
}
