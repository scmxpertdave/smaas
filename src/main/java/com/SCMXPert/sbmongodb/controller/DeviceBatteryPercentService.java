package com.SCMXPert.sbmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.Fields;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.SCMXPert.sbmongodb.document.DeviceDataStream;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;

@Service
public class DeviceBatteryPercentService {
	
	@Autowired
    private MongoTemplate mongoTemplate;
		
	public List<String> getLatestDeviceData(List<String> devicesList) {
		
//        List<String> deviceIds = Arrays.asList(devicesList.toString());   
		
            MatchOperation matchStage = match(Criteria.where("Device_Id").in(devicesList));
            SortOperation sortStage = sort(Sort.by(Sort.Direction.DESC, "SensorUTC"));
            GroupOperation groupStage = group("Device_Id")
                .first("$$ROOT").as("latestDocument");
            ProjectionOperation projectStage = project()
                .andExclude("_id")
                .and("latestDocument.Device_Id").as("Device_Id")
                .and("latestDocument.Battery_Level").as("Battery_Level");
            
            AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
            Aggregation aggregation = newAggregation(
                matchStage,
                sortStage,
                groupStage,
                projectStage
            ).withOptions(options);

            AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "DeviceDataStream", Document.class);
            List<Document> mappedResults = results.getMappedResults();

//            // Perform additional processing for Battery_Percentage
//            mappedResults = mappedResults.stream().map(doc -> {
//            	String batteryLevelStr = doc.getString("Battery_Level");
//                double batteryLevel = 0.0;
//                try {
//                    batteryLevel = Double.parseDouble(batteryLevelStr);
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//                double batteryPercentage = Math.round((batteryLevel / 4.2) * 100 * 100.0) / 100.0;
//                doc.put("Battery_Percentage", batteryPercentage);
//                return doc;
//            }).collect(Collectors.toList());
//
//            return mappedResults;
//           // return results.getMappedResults();
		                                
            List<String> deviceBatteryList = mappedResults.stream().map(doc -> {
                String deviceId = doc.getString("Device_Id");
                String batteryLevelStr = doc.getString("Battery_Level");
                double batteryLevel = 0.0;
                try {
                    batteryLevel = Double.parseDouble(batteryLevelStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                double batteryPercentage = Math.round((batteryLevel / 4.2) * 100 * 100.0) / 100.0;
                return String.format("%s - %.2f%%", deviceId, batteryPercentage);
            }).collect(Collectors.toList());

            return deviceBatteryList;                                                                                                 
	}
}
