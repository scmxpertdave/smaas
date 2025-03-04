package com.SCMXPert.sbmongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "com.SCMXPert.sbmongodb.repository" }, mongoTemplateRef = "mongoTemplate1")
public class MongoDbConfig {

	
	@Autowired
	private Environment env;
	
	@Value("${mongoAtlasURI}")
    private String mongoAtlasURI;

	@SuppressWarnings("deprecation")
	@Primary
	@Bean(name = "mongoTemplate1")
	public MongoTemplate mongoTemplate(@Qualifier("mongoFactory1") MongoDbFactory mongoFactory) throws Exception {
	
		MappingMongoConverter convertor = new MappingMongoConverter(mongodbFactory(), new MongoMappingContext());
		convertor.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(mongodbFactory(), convertor);
		return mongoTemplate;
	}

	@Primary
	@Bean(name = "mongoFactory1")
	public MongoDbFactory mongodbFactory() {
		return new SimpleMongoDbFactory(getMongoClient(), env.getRequiredProperty("spring.data.mongodb.database"));

	}

//	private MongoClient getMongoClient() {
//	
//		return new MongoClient(env.getRequiredProperty("m.mongo.host"),Integer.parseInt(env.getRequiredProperty("m.mongo.port")));
//
//	}
	
//	@SuppressWarnings("deprecation")
//    private MongoClient getMongoClient() {
//
//        String pass = "scmxpert@123SCM";        
//        char[] password =  pass.toCharArray();
//
//        MongoCredential mC = MongoCredential.createCredential("smaas", "admin",  password) ; 
//
//        List<MongoCredential> lmC = new ArrayList<MongoCredential>();
//        lmC.add(mC);
//
//        ServerAddress sA = new ServerAddress("localhost", 2666);
//
//        //return new MongoClient(env.getRequiredProperty("m.mongo.host"),Integer.parseInt(env.getRequiredProperty("m.mongo.port")));        
//        return new MongoClient(sA , lmC);
//
//        //return new MongoClient("mongodb://localhost:27017/SCM");
// 
//    
//
//}	
//	
//	
//
//}
	@SuppressWarnings("deprecation")
    private MongoClient getMongoClient() {
        //MongoClientURI uri = new MongoClientURI("mongodb+srv://m001-student:m001-mongodb-basics@cluster0.aejnp.mongodb.net/SCM?retryWrites=true&w=majority");
///		MongoClientURI uri = new MongoClientURI("mongodb+srv://smaas:scmxpert123SCM@scmxpert.5h1vx.mongodb.net/SCM?retryWrites=true&w=majority");
//		MongoClientURI uri = new MongoClientURI("mongodb+srv://scm-reddys:scmxpert123SMAAS@scmxpert-reddys.5h1vx.mongodb.net/SCM?retryWrites=true&w=majority");
        MongoClientURI uri = new MongoClientURI(mongoAtlasURI);
        //return new MongoClient(env.getRequiredProperty("m.mongo.host"),Integer.parseInt(env.getRequiredProperty("m.mongo.port")));        
        return new MongoClient(uri);
        //return new MongoClient("mongodb://localhost:27017/SCM");
}
}
