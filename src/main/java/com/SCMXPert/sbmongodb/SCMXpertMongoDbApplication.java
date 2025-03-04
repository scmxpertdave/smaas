package com.SCMXPert.sbmongodb;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


//@PropertySource({ "file:config/application.properties" }) 
@EnableResourceServer
@SpringBootApplication(exclude = ContextStackAutoConfiguration.class)
@EnableGlobalMethodSecurity(prePostEnabled =true )
@EnableScheduling   // added for Scheduling Fetching of Emails(PreAlert/FDMA).
//@EnableAsync
public class SCMXpertMongoDbApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(SCMXpertMongoDbApplication.class, args);
    }
/* 
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
 
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
 
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
 
        return mongoTemplate;
 
    }
     */
}