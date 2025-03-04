//package com.SCMXPert.sbmongodb.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Service;
//
//import com.SCMXPert.sbmongodb.document.DeviceDataStream;
//import com.SCMXPert.sbmongodb.document.Devicedatatemp;
//import com.SCMXPert.sbmongodb.document.Devices;
//import com.SCMXPert.sbmongodb.repository.DeviceDataStreamRepository;
//import com.SCMXPert.sbmongodb.repository.DevicesRepository;
//
//import javax.annotation.PostConstruct;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class DataStreamBatchService {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    
//	@Autowired
//	DeviceDataStreamRepository devicedatastreamrepo;
//	
//	@Autowired
//	private DevicesRepository devicerepo;
//
//    @PostConstruct
//    public void processBatchJob() {
//    	System.out.println("*********** Started looking for frequency of time stamps in device data. ***************");
//    	
//    //	List<Devices> devices = mongoTemplate.findAll(Devices.class);
////    	System.out.println("List devices");
////    	System.out.println(devices);
////    	
////    	List<String> deviceIds = new ArrayList<>();   	
////    	for(Devices device: devices) {    		
////    		device.getDeviceId();
////    		deviceIds.addAll(deviceIds);
////    	}
////    	System.out.println("List deviceIds");
////    	System.out.println(devices);
//    	   	
//    	
//		String sensorUtcLatest  = null;	
//		String temperatureLatest  = null;
//        String sensorUtcSecondLatest = null;
//        String temperatureSecondLatest  = null;
//        
//       // List<Devices> devices = mongoTemplate.findAll(Devices.class);
//        List<Devices> devices = devicerepo.findAll();
//        
//        System.out.println("List devices");
//        System.out.println(devices);
//        
//    	for(Devices dev: devices) {
//    		 System.out.println("device id");
//    		 System.out.println(dev.getDeviceId());
//    		 
//    		List<DeviceDataStream> latestDataStreams = devicedatastreamrepo.findByDevice_id(dev.getDeviceId());
//    		Collections.sort(latestDataStreams, (ds1, ds2) -> ds1.getSensorUTC().compareTo(ds2.getSensorUTC()));
//
//    		if (!latestDataStreams.isEmpty()) {
//                // Get current_terminal_time of the latest document
//                 sensorUtcLatest = latestDataStreams.get(0).getSensorUTC();
//
//                // Get Sensor_temperature of the latest document
//                 temperatureLatest = latestDataStreams.get(0).getFirst_Sensor_temperature();
//
//
//                if (latestDataStreams.size() > 1) {  
//                    // Get current_terminal_time of the second-to-latest document
//                	sensorUtcSecondLatest = latestDataStreams.get(1).getSensorUTC();
//                    // Get Sensor_temperature of the second-to-latest document
//                	temperatureSecondLatest = latestDataStreams.get(1).getFirst_Sensor_temperature();
//                }
//                
//				// Process the retrieved data as needed
//				System.out.println("Latest sensorUTC: " + sensorUtcLatest);
//				System.out.println("Latest Sensor_temperature: " + temperatureLatest);
//				System.out.println("Second latest sensorUTC: " + sensorUtcSecondLatest);
//				System.out.println("Second latest Sensor_temperature: " + temperatureSecondLatest);
//
//            }
//    		else {
//                System.out.println("No data found in the DeviceDataStream collection.");
//            }
//    		
//    		
//            Instant instant1 = Instant.parse(sensorUtcLatest);
//            Instant instant2 = Instant.parse(sensorUtcSecondLatest);
//            Duration difference = Duration.between(instant1, instant2);
//            
//            System.out.println("Time difference: " + difference);
//            System.out.println("Minutes: " + difference.toMinutes());
//            
//    		if(difference.toMinutes() > 10) {
//                DeviceDataStream newDataStream = new DeviceDataStream();
//                newDataStream.setBattery_Level(latestDataStreams.get(0).getBattery_Level());
//                newDataStream.setDevice_Id("000121115");
////                newDataStream.setDevice_Id(latestDataStreams.get(0).getDevice_Id());
//                newDataStream.setLocationType("");
//                newDataStream.setReportPeriod("");
//                newDataStream.setMaxHumidityThreshold("");
//                newDataStream.setReporting_zone(latestDataStreams.get(0).getReporting_zone());
//                newDataStream.setIccid("");
//                newDataStream.setModem_IMEI(latestDataStreams.get(0).getModem_IMEI());
//                newDataStream.setShakeThreshold("");
//                newDataStream.setMinTempThreshold("");
//                newDataStream.setSensorPhySampleCycle("");
//                newDataStream.setHumdity_2("");
//                newDataStream.setMileage("");
//                newDataStream.setAirpressure("");
//                newDataStream.setSensorReportCycle("");
//                newDataStream.setHumidity_1("");
//                newDataStream.setActive("");
//                newDataStream.setDevice_slno("");
//                newDataStream.setMessage_Type(latestDataStreams.get(0).getMessage_Type());
//                newDataStream.setAddress(latestDataStreams.get(0).getAddress());
//                newDataStream.setLongitude(latestDataStreams.get(0).getLongitude());
//                
//                newDataStream.setTiltAngle(latestDataStreams.get(0).getTiltAngle());
//                newDataStream.setInternal_temperature(latestDataStreams.get(0).getInternal_temperature());
//                newDataStream.setReporting_time(latestDataStreams.get(0).getReporting_time());
//                newDataStream.setMinHumidityThreshold(latestDataStreams.get(0).getMinHumidityThreshold());
//                newDataStream.setSpeed_in_mph(temperatureSecondLatest);
//                newDataStream.setCurrent_terminal_time(temperatureSecondLatest);
//                newDataStream.setMaxTempThreshold(temperatureSecondLatest);
//                newDataStream.setLatitude(latestDataStreams.get(0).getLatitude());
//                newDataStream.setDevice_oem(latestDataStreams.get(0).getDevice_oem());
//                newDataStream.setCurrent_terminal_date(temperatureSecondLatest);
//                newDataStream.setReporting_Date(temperatureSecondLatest);
//                newDataStream.setSensorUTC(sensorUtcSecondLatest);
//              
//                
//                System.out.println("newDataStream object");
//                System.out.println(newDataStream);
//               
//
//              
//                mongoTemplate.save(newDataStream);
//                System.out.println("MongoTemplate got updated for missed time frequency.");
//    		}
//    		
//    		
//
//    	}
//    	             
//   }
//
////    private void processRecord(DeviceDataStream dataStream) {
////        String[] parts = dataStream.getCurrent_terminal_time().split("\\.");
////        if (parts.length == 3) {
////            int hour = Integer.parseInt(parts[0]);
////            int minute = Integer.parseInt(parts[1]);
////            int second = Integer.parseInt(parts[2]);
////
////            LocalTime currentTime = LocalTime.of(hour, minute, second);
////
////            // Check if the time difference is greater than 10 minutes
////            LocalTime tenMinutesLater = currentTime.plusMinutes(10);
////            if (!currentTime.isAfter(tenMinutesLater)) {
////                // Create a new record with the adjusted time
////                String newTime = String.format("%02d.%02d.%02d", tenMinutesLater.getHour(), tenMinutesLater.getMinute(), tenMinutesLater.getSecond());
////                DeviceDataStream newDataStream = new DeviceDataStream();
////                newDataStream.setId(dataStream.getId());
////                newDataStream.setCurrent_terminal_time(newTime);
////                mongoTemplate.save(newDataStream);
////            }
////        }
////    }
//    
//    
//}
