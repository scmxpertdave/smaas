/*
 * package com.SCMXPert.sbmongodb.repository;
 * 
 * import java.util.Date;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.domain.Sort; import
 * org.springframework.data.mongodb.core.MongoTemplate; import
 * org.springframework.data.mongodb.core.query.Criteria; import
 * org.springframework.data.mongodb.core.query.Query; import
 * org.springframework.data.mongodb.core.query.Update; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.SCMXPert.sbmongodb.document.Shipments; import
 * com.mongodb.client.result.UpdateResult;
 * 
 * @Repository public class ShipmentsRepositoryCustomImpl implements
 * ShipmentsRepositoryCustom {
 * 
 * @Autowired MongoTemplate mongoTemplate;
 * 
 * public String getMaxEmpId() { Query query = new Query(); query.with(new
 * Sort(Sort.Direction.DESC, "id")); query.limit(1); Shipments maxObject =
 * mongoTemplate.findOne(query, Shipments.class);
 * 
 * if (maxObject == null) { return 0L; }
 * 
 * return maxObject.getId(); }
 * 
 * @Override public long update(String BP_Type, String fullName, Date hireDate)
 * { Query query = new Query(Criteria.where("BP_Type").is(BP_Type)); Update
 * update = new Update(); update.set("Goods_Desc", fullName);
 * update.set("hireDate", hireDate);
 * 
 * UpdateResult result = this.mongoTemplate.updateFirst(query, update,
 * Shipments.class);
 * 
 * if (result != null) { return result.getModifiedCount(); }
 * 
 * return 0; }
 * 
 * }
 */