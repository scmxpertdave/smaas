package com.SCMXPert.sbmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.SCMXPert.sbmongodb.document.CustomBP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BusinessPartnerService {
	
	@Autowired
    private MongoTemplate mongoTemplate;

    public List<CustomBP> getBusinessPartnersDetails(List<String> bpIds) {
    	
    	 AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
        // Match stage to filter documents by BP_Id
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("BP_Id").in(bpIds)),
                // Project stage to shape the output as desired
                Aggregation.project()
                        .andExclude("_id")
                        .and("Events").as("events")
                        .and("BP_Id").as("BP_Id")
                        .and("Company_Name").as("Company_Name")
        ).withOptions(options);

        // Execute aggregation query
        AggregationResults<CustomBP> results = mongoTemplate.aggregate(aggregation, "BusinessPartner", CustomBP.class);

        // Extract results from AggregationResults
   //   List<CustomBP> businessPartnersDetails = results.getMappedResults();
        List<CustomBP> businessPartnersDetails = new ArrayList<>(results.getMappedResults());
        
//        System.out.println("Before sorting:");
//        businessPartnersDetails.forEach(bp -> System.out.println(bp.getBP_Id()));
        
        businessPartnersDetails.sort(Comparator.comparing(bp -> {
            String bpId = bp.getBP_Id();
            return Integer.parseInt(bpId.substring(1)); // Extract the numeric part and convert to integer
        }));
        
//        System.out.println("After sorting:");
//        businessPartnersDetails.forEach(bp -> System.out.println(bp.getBP_Id()));
             
        return businessPartnersDetails;
    }
}
