package com.SCMXPert.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SCMXPert.sbmongodb.document.CountryStateList;

public interface CountryStateListRepo extends MongoRepository<CountryStateList, String> {

}
