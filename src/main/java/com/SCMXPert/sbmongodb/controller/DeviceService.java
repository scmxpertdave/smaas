package com.SCMXPert.sbmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class DeviceService {
	
	@Autowired
    private MongoTemplate mongoTemplate;

    public List<String> getAllAvailableDevices() {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("DeviceStatusReferred").is("Available")),
                project("Device_Id").andExclude("_id")
        );

        AggregationResults<DeviceIdOnly> results = mongoTemplate.aggregate(aggregation, "Devices", DeviceIdOnly.class);
        return results.getMappedResults().stream()
                .map(DeviceIdOnly::getDeviceId)
                .collect(Collectors.toList());
    }

    private static class DeviceIdOnly {
        private String Device_Id;

        public String getDeviceId() {
            return Device_Id;
        }

        public void setDeviceId(String deviceId) {
            this.Device_Id = deviceId;
        }
    }

}
