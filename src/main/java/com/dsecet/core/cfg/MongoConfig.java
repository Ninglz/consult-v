package com.dsecet.core.cfg;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author: lxl
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.dsecet.core.mongo.repository")
public class MongoConfig extends AbstractMongoConfiguration{

    @Value("${mongodb.dataSource.username}")
    private String username;

    @Value("${mongodb.dataSource.password}")
    private String password;

    @Value("${mongodb.dataSource.database}")
    private String databaseName;

    @Value("${mongodb.host}")
    private String mongoHost;

    @Value("${mongodb.port}")
    private String mongoPort;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        UserCredentials userCredentials = new UserCredentials(username, password);
        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(mongo(), databaseName, userCredentials);
        factory.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return factory;
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }


    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost, Integer.valueOf(mongoPort));
    }
}
