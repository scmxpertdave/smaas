package com.SCMXPert.sbmongodb.sequence.dao;

import org.springframework.stereotype.Service;

/**
 * @author Uday
 **/

@Service
public interface EvenIdSequenceDao {

	long getNextSequenceId(String key);
}
