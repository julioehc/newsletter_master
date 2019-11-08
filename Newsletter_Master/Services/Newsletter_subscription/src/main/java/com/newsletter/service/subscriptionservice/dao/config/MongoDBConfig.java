package com.newsletter.service.subscriptionservice.dao.config;

import com.mongodb.MongoClient;
import com.newsletter.service.subscriptionservice.dao.SubscriptionRepo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@EnableMongoRepositories (basePackageClasses = SubscriptionRepo.class)
@Configuration
public class MongoDBConfig {

}
