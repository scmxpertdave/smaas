package com.SCMXPert.sbmongodb.sequence.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.SCMXPert.sbmongodb.document.EventIdSeq;
import com.SCMXPert.sbmongodb.sequence.dao.EvenIdSequenceDao;

/**
 * @author Uday
 **/

@Service
public class EvenIdSequenceDaoImpl implements EvenIdSequenceDao {

	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public long getNextSequenceId(String key) {

		Query query = new Query(Criteria.where("_id").is(key));

		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		EventIdSeq seqId = mongoOperation.findAndModify(query, update, EventIdSeq.class);

		if (seqId == null) {
			System.out.println("unable to get sequence id for the key");

		}
		return seqId.getSeq();
	}

}
