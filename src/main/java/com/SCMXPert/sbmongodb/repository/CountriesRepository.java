package com.SCMXPert.sbmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.SCMXPert.sbmongodb.document.Countries;
import com.SCMXPert.sbmongodb.document.CountryStateList;
import com.SCMXPert.sbmongodb.document.Devices;

public interface CountriesRepository extends MongoRepository<Countries, String> {
	@Query("{name:'?0'}")
	List<Countries> findByname(String name);
	
	//List<Countries> findAll(String name);
}
